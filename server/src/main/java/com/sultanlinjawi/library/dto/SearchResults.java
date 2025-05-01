package com.sultanlinjawi.library.dto;

import java.util.ArrayList;

public record SearchResults(Results results) {
    // TODO: Ignore properties
    public record Results(
            ArrayList<Object> facet_counts,
            int found,
            ArrayList<Hit> hits,
            int out_of,
            int page,
            RequestParams request_params,
            boolean search_cutoff,
            int search_time_ms) {
        public record Hit(
                Document document,
                Highlight highlight,
                ArrayList<Highlight> highlights,
                long text_match,
                TextMatchInfo text_match_info) {

            public record TextMatchInfo(
                    String best_field_score,
                    int best_field_weight,
                    int fields_matched,
                    String score,
                    int tokens_matched) {}

            public record Document(
                    int activities_count,
                    ArrayList<String> alternative_titles,
                    ArrayList<String> author_names,
                    boolean compilation,
                    ArrayList<String> content_warnings,
                    ArrayList<String> contribution_types,
                    ArrayList<Contribution> contributions,
                    String cover_color,
                    String description,
                    FeaturedSeries featured_series,
                    int featured_series_position,
                    ArrayList<String> genres,
                    boolean has_audiobook,
                    boolean has_ebook,
                    String id,
                    Image image,
                    ArrayList<String> isbns,
                    int lists_count,
                    ArrayList<String> moods,
                    int pages,
                    int prompts_count,
                    double rating,
                    int ratings_count,
                    String release_date,
                    int release_year,
                    int reviews_count,
                    ArrayList<String> series_names,
                    String slug,
                    String subtitle,
                    ArrayList<String> tags,
                    String title,
                    int users_count,
                    int users_read_count) {

                public record Contribution(Author author, String contribution) {

                    public record Author(int id, Image image, String name, String slug) {}
                }

                public record FeaturedSeries(
                        Object collection,
                        String details,
                        boolean featured,
                        int id,
                        int position,
                        Series series,
                        boolean unreleased) {

                    public record Series(
                            int books_count,
                            int id,
                            String name,
                            int primary_books_count,
                            String slug) {}
                }

                public record Image(
                        String color,
                        String color_name,
                        int height,
                        int id,
                        String url,
                        int width) {}
            }

            public record Highlight(
                    ArrayList<AlternativeTitle> alternative_titles,
                    ArrayList<SeriesName> series_names,
                    Title title) {

                public record AlternativeTitle(ArrayList<String> matched_tokens, String snippet) {}

                public record Title(ArrayList<String> matched_tokens, String snippet) {}

                public record SeriesName(ArrayList<String> matched_tokens, String snippet) {}
            }
        }

        public record RequestParams(String collection_name, int per_page, String q) {}
    }
}
