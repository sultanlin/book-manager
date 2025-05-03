package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.BookSearch;
import com.sultanlinjawi.library.models.Book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Book> search(String name, String type) {
        var booksSearched =
                graphQlClient
                        .document(this.query)
                        .variable("name", name)
                        .variable("type", type)
                        .retrieveSync("search")
                        .toEntity(BookSearch.class);

        return searchResultsToBooksList(booksSearched);
    }

    private List<Book> searchResultsToBooksList(BookSearch booksSearched) {
        return booksSearched.results().hits().stream().map(bookHit -> Book.from(bookHit)).toList();
    }
}
