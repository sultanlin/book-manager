package com.sultanlinjawi.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.sultanlinjawi.library.dto.BookSearch;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument.BookSearchImage;
import com.sultanlinjawi.library.models.Book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpSyncGraphQlClient;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTests {
    // TODO: Consider testing FieldAccessException

    @Mock private HttpSyncGraphQlClient graphQlClient;

    @InjectMocks private SearchService service;

    @Test
    @DisplayName("When searching for book, the response correctly returns a list of books")
    public void GraphqlClientResponseReturnsListOfBooks() {
        InitGraphqlClientMock(getBookSearchA());

        var expected = getBooksA();
        var actual = service.search("spring+start+here", "Book");

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    private void InitGraphqlClientMock(BookSearch bookSearch) {
        var requestMock = Mockito.mock(GraphQlClient.RequestSpec.class);
        var requestMockWithVariable = Mockito.mock(GraphQlClient.RequestSpec.class);
        var requestMockWithBothVariables = Mockito.mock(GraphQlClient.RequestSpec.class);
        var retrieveMock = Mockito.mock(GraphQlClient.RetrieveSyncSpec.class);

        when(graphQlClient.document(Mockito.anyString())).thenReturn(requestMock);
        when(requestMock.variable(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(requestMockWithVariable);
        when(requestMockWithVariable.variable(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(requestMockWithBothVariables);
        when(requestMockWithBothVariables.retrieveSync("search")).thenReturn(retrieveMock);
        when(retrieveMock.toEntity(BookSearch.class)).thenReturn(bookSearch);
    }

    public BookSearch getBookSearchA() {
        return BookSearch.builder()
                .results(
                        BookSearchResults.builder()
                                .hits(
                                        List.of(
                                                BookSearchHit.builder()
                                                        .document(
                                                                BookSearchDocument.builder()
                                                                        .id(1049905)
                                                                        .description(null)
                                                                        .pages(0)
                                                                        .rating(
                                                                                BigDecimal.valueOf(
                                                                                        0.0))
                                                                        .ratings_count(0)
                                                                        .release_date(null)
                                                                        .slug("spring-start-here")
                                                                        .subtitle(
                                                                                "Learn what you"
                                                                                    + " need and"
                                                                                    + " learn it"
                                                                                    + " well")
                                                                        .title(
                                                                                "Spring Start Here:"
                                                                                    + " Learn what"
                                                                                    + " you need"
                                                                                    + " and learn"
                                                                                    + " it well")
                                                                        .author_names(
                                                                                List.of(
                                                                                        "Laurentiu"
                                                                                            + " Spilca"))
                                                                        .image(
                                                                                BookSearchImage
                                                                                        .builder()
                                                                                        .url(
                                                                                                URI
                                                                                                        .create(
                                                                                                                "https://assets.hardcover.app/external_data/60220541/b7c22e914c405fe502e6baaf1efda3120d3b97db.jpeg"))
                                                                                        .build())
                                                                        .build())
                                                        .build()))
                                .build())
                .build();
    }

    public List<Book> getBooksA() {
        return List.of(
                Book.builder()
                        .id(1049905)
                        .title("Spring Start Here: Learn what you need and learn it well")
                        .author("Laurentiu Spilca")
                        .description(null)
                        .pages(0)
                        .rating(BigDecimal.valueOf(0.0))
                        .ratings_count(0)
                        .cover(
                                "https://assets.hardcover.app/external_data/60220541/b7c22e914c405fe502e6baaf1efda3120d3b97db.jpeg")
                        .release_date(null)
                        .slug("spring-start-here")
                        .subtitle("Learn what you need and learn it well")
                        .build());
    }
}
