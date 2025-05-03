package com.sultanlinjawi.library.models;

import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
public class Book {
    @Id private int id;

    @Column(nullable = false)
    private String title;

    private String author;
    private int pages;
    // private double rating;
    private BigDecimal rating;
    private int ratings_count;
    private String cover;
    private String description;
    private LocalDate release_date;
    private String slug;
    private String subtitle;

    public static Book BookFromSearchResults(BookSearchHit hit) {
        var document = hit.document();
        var author = "";
        var cover = "";

        if (!(document.image().url() == null)) {
            cover = document.image().url().toString();
        }
        if (!document.author_names().isEmpty()) {
            author = document.author_names().get(0);
        }

        return new Book(
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
