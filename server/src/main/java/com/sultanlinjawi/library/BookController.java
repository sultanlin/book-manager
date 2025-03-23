package com.sultanlinjawi.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import lombok.val;

import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class BookController {
        
    @GetMapping("/test")
    public Book getResults() {
        RestClient restClient = RestClient.create("https://api.hardcover.app/v1/graphql");

        HttpSyncGraphQlClient graphQlClient =
                HttpSyncGraphQlClient.builder(restClient)
                        .headers(
                                (headers) ->
                                        headers.add(
                                                "authorization", System.getenv("authorization")))
                        .build();

        String bookName = "lord of the rings";

        String document =
                """
query bookByName($bookName: String!, $qType: String!) {
    search(query: $bookName, query_type: $qType,
    per_page: 2, page: 1) {
        results
    }
}
""";

        JsonNode project = null;
        try {
            project =
                    graphQlClient
                            .document(document)
                            .variable("bookName", bookName)
                            .variable("qType", "Book")
                            .retrieveSync("search")
                            .toEntity(JsonNode.class);
            // System.out.println("Project is " + project);
        } catch (FieldAccessException ex) {
            ClientGraphQlResponse response = ex.getResponse();
            // ...
            ClientResponseField field = ex.getField();
            // return fallback value
            System.out.println("No book found?!?");
        }

        // TODO: Understand why this doesn't work
        // JsonNode result = project.get("results").get("hits").get("document").get("title");

        // JsonNode result = project.get("results").get("hits").findValue("document").get("title");
        JsonNode docu = project.get("results").get("hits").findValue("document");

        // return result.toString();

                String js = """
                { "results": { "title": "I'm here" }
                }
                """;
// "{\"results\":\"Bob\", \"found\":13}"
        try {
            ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // mapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
    // mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            // Book value = mapper.readValue(docu.toString(), Book.class);
            Book value = mapper.treeToValue(docu, Book.class);
            System.out.println("So far so good?");
            return value;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            return new Book();
        }
    }
}
