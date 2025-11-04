package com.sultanlinjawi.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.sultanlinjawi.library.book.BookDto;
import com.sultanlinjawi.library.search.BookSearch;
import com.sultanlinjawi.library.search.BookSearch.BookSearchResults;
import com.sultanlinjawi.library.search.BookSearch.BookSearchResults.BookSearchHit;
import com.sultanlinjawi.library.search.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument;
import com.sultanlinjawi.library.search.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument.BookSearchImage;
import com.sultanlinjawi.library.search.SearchService;

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
    @Mock private HttpSyncGraphQlClient graphQlClient;

    @InjectMocks private SearchService service;

    @Test
    @DisplayName("Searching book, response returns a list of books")
    public void GraphqlClientResponseReturnsListOfBooks() {
        setupGraphqlClientMock(getBookSearchA());

        var expected = getBookDtoA();
        var actual = service.search("spring+start+here", "Book");

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(
            "Searching book, no errors converting BookSearch to Book dto if fields are null/empty")
    public void NullFieldsDoesNotReturnError() {
        setupGraphqlClientMock(getBookSearchB());

        var expected = getBookDtoB();
        var actual = service.search("spring+start+here", "Book");

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Searching book, empty BookSearch gets converted to empty list of books")
    public void EmptyBookSearchReturnsEmptyList() {
        setupGraphqlClientMock(new BookSearch(new BookSearchResults(List.of())));

        var expected = List.of();
        var actual = service.search("spring+start+here", "Book");

        Assertions.assertThat(actual).isNotNull();
        assertEquals(expected, actual);
    }

    private void setupGraphqlClientMock(BookSearch bookSearch) {
        var requestMock = Mockito.mock(GraphQlClient.RequestSpec.class);
        var retrieveMock = Mockito.mock(GraphQlClient.RetrieveSyncSpec.class);

        when(graphQlClient.document(Mockito.anyString())).thenReturn(requestMock);
        when(requestMock.variable(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(requestMock);
        when(requestMock.variable(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(requestMock);
        when(requestMock.retrieveSync("search")).thenReturn(retrieveMock);
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

    public List<BookDto> getBookDtoA() {
        return List.of(
                BookDto.builder()
                        .id(1049905)
                        .title("Spring Start Here: Learn what you need and learn it well")
                        .author("Laurentiu Spilca")
                        .description(null)
                        .pages(0)
                        .rating(BigDecimal.valueOf(0.0))
                        .ratingsCount(0)
                        .cover(
                                "https://assets.hardcover.app/external_data/60220541/b7c22e914c405fe502e6baaf1efda3120d3b97db.jpeg")
                        .releaseDate(null)
                        .slug("spring-start-here")
                        .subtitle("Learn what you need and learn it well")
                        .build());
    }

    public BookSearch getBookSearchB() {
        return BookSearch.builder()
                .results(
                        BookSearchResults.builder()
                                .hits(
                                        List.of(
                                                BookSearchHit.builder()
                                                        .document(
                                                                BookSearchDocument.builder()
                                                                        .id(0)
                                                                        .description(null)
                                                                        .pages(0)
                                                                        .rating(null)
                                                                        .ratings_count(0)
                                                                        .release_date(null)
                                                                        .slug(null)
                                                                        .subtitle(null)
                                                                        .title(null)
                                                                        .author_names(List.of())
                                                                        .image(
                                                                                new BookSearchImage(
                                                                                        null))
                                                                        .build())
                                                        .build()))
                                .build())
                .build();
    }

    public List<BookDto> getBookDtoB() {
        return List.of(
                BookDto.builder()
                        .id(0)
                        .title(null)
                        .author(null)
                        .description(null)
                        .pages(0)
                        .rating(null)
                        .ratingsCount(0)
                        .cover(null)
                        .releaseDate(null)
                        .slug(null)
                        .subtitle(null)
                        .build());
    }
}
