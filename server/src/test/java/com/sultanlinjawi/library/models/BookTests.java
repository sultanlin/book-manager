package com.sultanlinjawi.library.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit.BookSearchDocument.BookSearchImage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookTests {
    @Test
    @DisplayName("Successfully map BookSearchHit to Book pojo")
    public void AllFieldsBookFactory() {
        var expected = book();
        var actual = Book.from(bookSearchHit());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Successfully map BookSearchHit to Book pojo when image and URL fields are empty")
    public void ImageUrlAndAuthorMissingBookFactory() {
        var expected = bookWithMissingImageUrlAndAuthor();
        var actual = Book.from(bookSearchHitWithMissingImageUrlAndAuthor());
        assertEquals(expected, actual);
    }

    public BookSearchHit bookSearchHit() {
        return BookSearchHit.builder()
                .document(
                        BookSearchDocument.builder()
                                .title("test name")
                                .author_names(List.of("Tester 1", "John Doe"))
                                .slug("test-name")
                                .pages(100)
                                .rating(BigDecimal.valueOf(0.0))
                                .ratings_count(10)
                                .release_date(LocalDate.now())
                                .id(11321389)
                                .description("This is a test hit")
                                .image(
                                        BookSearchImage.builder()
                                                .url(URI.create("test.com"))
                                                .build())
                                .build())
                .build();
    }

    public Book book() {
        return Book.builder()
                .title("test name")
                .author("Tester 1")
                .slug("test-name")
                .pages(100)
                .rating(BigDecimal.valueOf(0.0))
                .ratings_count(10)
                .release_date(LocalDate.now())
                .id(11321389)
                .description("This is a test hit")
                .cover("test.com")
                .build();
    }

    public BookSearchHit bookSearchHitWithMissingImageUrlAndAuthor() {
        return BookSearchHit.builder()
                .document(
                        BookSearchDocument.builder()
                                .title("test name")
                                .author_names(List.of())
                                .slug("test-name")
                                .pages(100)
                                .rating(BigDecimal.valueOf(0.0))
                                .ratings_count(10)
                                .release_date(LocalDate.now())
                                .id(11321389)
                                .description("This is a test hit")
                                .image(new BookSearchImage(null))
                                .build())
                .build();
    }

    public Book bookWithMissingImageUrlAndAuthor() {
        return Book.builder()
                .title("test name")
                .author(null)
                .slug("test-name")
                .pages(100)
                .rating(BigDecimal.valueOf(0.0))
                .ratings_count(10)
                .release_date(LocalDate.now())
                .id(11321389)
                .description("This is a test hit")
                .cover(null)
                .build();
    }
}
