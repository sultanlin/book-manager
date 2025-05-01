package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.SearchResults;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SearchService {

    private final HttpSyncGraphQlClient graphQlClient;
    private final String query =
            """
            query bookByName($name: String!, $type: String!) {
                    search(query: $name, query_type: $type, per_page: 25, page: 1) {
                            results
                    }
            } """;

    public SearchResults search(String name, String type) {
        return graphQlClient
                .document(this.query)
                .variable("name", name)
                .variable("type", type)
                .retrieveSync("search")
                .toEntity(SearchResults.class);
    }
}
