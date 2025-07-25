package com.sultanlinjawi.library.models;

import com.sultanlinjawi.library.dto.BookDto;
import com.sultanlinjawi.library.dto.BookSearch.BookSearchResults.BookSearchHit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
@EqualsAndHashCode
public class Book {
    @Id private int id;

    @Column(nullable = false)
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

    @ManyToMany(mappedBy = "books")
    private final Set<Shelf> shelves = new HashSet<>();

    public static Book from(BookSearchHit hit) {
        // TODO: Remove this if no longer using it (using BookDto.from(hit) instead)
        var document = hit.document();
        String author = null;
        String cover = null;

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

    public static Book from(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPages(),
                bookDto.getRating(),
                bookDto.getRatingsCount(),
                bookDto.getCover(),
                bookDto.getDescription(),
                bookDto.getReleaseDate(),
                bookDto.getSlug(),
                bookDto.getSubtitle());
    }
}
