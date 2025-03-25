package com.sultanlinjawi.library.service;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SearchService {
    public JsonNode searchBook(String bookName) {
        String document =
                """
query bookByName($bookName: String!) {
    search(query: $bookName, query_type: "Book",
    per_page: 1, page: 1) {
        results
    }
}
""";

        RestClient restClient = RestClient.create("https://api.hardcover.app/v1/graphql");

        HttpSyncGraphQlClient graphQlClient =
                HttpSyncGraphQlClient.builder(restClient)
                        .headers(
                                (headers) ->
                                        headers.add(
                                                "authorization", System.getenv("authorization")))
                        .build();

        JsonNode project = null;
        // TODO: Check out proper graphql query practices for error handling
        try {
            project =
                    graphQlClient
                            .document(document)
                            .variable("bookName", bookName)
                            .retrieveSync("search")
                            .toEntity(JsonNode.class);
        } catch (FieldAccessException ex) {
            ClientGraphQlResponse response = ex.getResponse();
            System.out.println(response);
            // ...
            ClientResponseField field = ex.getField();
            System.out.println(field);
            // return fallback value
            System.out.println(ex);
        }
        return project;
    }
}
