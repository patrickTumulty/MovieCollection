package com.pt.app;

public class Movie implements Comparable<Movie> {
    private final String title;
    private final Genre genre;
    private final Format format;


    public Movie(String title, Genre genre, Format format) {
        this.title = title;
        this.genre = genre;
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public Format getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return String.format("%-20s | Genre : %-10s | Format : %-10s", title, format.name(), genre.name());
    }

    @Override
    public int compareTo(Movie movie) {
        return title.compareTo(movie.getTitle());
    }
}
