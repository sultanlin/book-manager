package com.sultanlinjawi.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookSearch(BookSearchResults results) {
    public record BookSearchResults(ArrayList<BookSearchHit> hits) {
        public record BookSearchHit(BookSearchDocument document) {
            public record BookSearchDocument(
                    ArrayList<String> author_names,
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
                public record BookSearchImage(URL url) {}
            }
        }
    }
}
