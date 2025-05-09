package com.sultanlinjawi.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record BookSearch(BookSearchResults results) {
    @Builder
    public record BookSearchResults(List<BookSearchHit> hits) {
        @Builder
        public record BookSearchHit(BookSearchDocument document) {
            @Builder
            public record BookSearchDocument(
                    List<String> author_names,
                    String description,
                    int id,
                    BookSearchImage image,
                    int pages,
                    BigDecimal rating,
                    int ratings_count,
                    LocalDate release_date,
                    String slug,
                    String subtitle,
                    String title) {
                @Builder
                public record BookSearchImage(URI url) {}
            }
        }
    }
}
