package com.sultanlinjawi.library;

// @JsonIgnoreProperties(ignoreUnknown = true)
// record Book(
//         String title,
//         String[] author_names,
//         String description,
//         boolean has_audiobook,
//         // private int imageNum;
//         int pages,
//         String release_date, // TODO: Switch to date
//         int release_year,
//         String[] series_names,
//         String subtitle) {}

public class Book {
    private String title;

    private String[] author_names;
    private String description;
    private boolean has_audiobook;
    // private int imageNum;
    private int pages;

    private String release_date; // TODO: Switch to date
    private int release_year;
    private String[] series_names;
    private String subtitle;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book() {}

    public String[] getAuthor_names() {
        return author_names;
    }

    public void setAuthor_names(String[] author_names) {
        this.author_names = author_names;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHas_audiobook() {
        return has_audiobook;
    }

    public void setHas_audiobook(boolean has_audiobook) {
        this.has_audiobook = has_audiobook;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String[] getSeries_names() {
        return series_names;
    }

    public void setSeries_names(String[] series_names) {
        this.series_names = series_names;
    }
}
