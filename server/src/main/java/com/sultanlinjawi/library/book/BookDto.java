package com.sultanlinjawi.library.book;

import com.sultanlinjawi.library.search.BookSearch.BookSearchResults.BookSearchHit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private int id;
    private String title;
    private String author;
    private int pages;
    private BigDecimal rating;
    private int ratingsCount;
    private String cover;
    private String description;
    private LocalDate releaseDate;
    private String slug;
    private String subtitle;

    public static BookDto from(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getRating(),
                book.getRatingsCount(),
                book.getCover(),
                book.getDescription(),
                book.getReleaseDate(),
                book.getSlug(),
                book.getSubtitle());
    }

    public static BookDto from(BookSearchHit hit) {
        var document = hit.document();
        String author = null;
        String cover = null;

        if (!(document.image().url() == null)) {
            cover = document.image().url().toString();
        }
        if (!document.author_names().isEmpty()) {
            author = document.author_names().get(0);
        }

        return new BookDto(
                document.id(),
                document.title(),
                author,
                document.pages(),
                document.rating(),
                document.ratings_count(),
                cover,
                document.description(),
                document.release_date(),
                document.slug(),
                document.subtitle());
    }
}
