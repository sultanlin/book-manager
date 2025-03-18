package com.sultanlinjawi.library;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.ClientResponseField;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

        // RestClient restClient = RestClient.create("https://api.hardcover.app/v1/graphql");

        RestClient client =
                RestClient.builder().baseUrl("https://api.hardcover.app/v1/graphql").build();
        HttpSyncGraphQlClient graphQlClient =
                HttpSyncGraphQlClient.builder(client)
                        .headers(
                                (headers) ->
                                        headers.add(
                                                "authorization", System.getenv("authorization")))
                        .build();

        String bookName = "dune";
        JsonNode book =
                graphQlClient
                        .documentName("bookByName")
                        .variable("bookName", bookName)
                        .retrieveSync("search")
                        .toEntity(JsonNode.class);

        System.out.println(book);

        String document =
                """
 {
    search(query: "%s", query_type: "Book",
    per_page: 10, page: 1) {
        results
    }
}
"""
                        .formatted(bookName);

        try {
            JsonNode project =
                    graphQlClient
                            .document(document)
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
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
