package com.sultanlinjawi.library.services;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SearchService {

    private final HttpSyncGraphQlClient graphQlClient;
    private final String query;

    public SearchService() {
        var restClient = RestClient.create("https://api.hardcover.app/v1/graphql");
        this.graphQlClient =
                HttpSyncGraphQlClient.builder(restClient)
                        .headers(
                                (headers) ->
                                        headers.add(
                                                "authorization", System.getenv("authorization")))
                        .build();
        this.query =
                """
                query bookByName($name: String!, $type: String!) {
                        search(query: $name, query_type: $type, per_page: 25, page: 1) {
                                results
                        }
                } """;
    }

    public JsonNode search(String name, String type) {
        // TODO: Check out proper graphql query practices for error handling
        try {
            JsonNode project =
                    graphQlClient
                            .document(this.query)
                            .variable("name", name)
                            .variable("type", type)
                            .retrieveSync("search")
                            .toEntity(JsonNode.class);
            return project;
        } catch (FieldAccessException ex) {
            ClientGraphQlResponse response = ex.getResponse();
            System.out.println(response);
            // ...
            ClientResponseField field = ex.getField();
            System.out.println(field);
            // return fallback value
            System.out.println(ex);
            return null;
        }
    }
}
