package com.pt.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovieCollectionApp {

    public static void main(String[] args) {
        MovieDatabase movieDatabase = new MovieDatabase("movie_database.db");
        List<Movie> movieList = movieDatabase.getAllMoviesFromDatabase();
        Collections.sort(movieList);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }


    }

    private static void scratchCode() {
//        Movie movie1 = new Movie("Bladerunner", Genre.SCI_FI, Format.BLUE_RAY);
//        Movie movie2 = new Movie("Saving Private Ryan", Genre.DRAMA, Format.DVD);
//        Movie movie3 = new Movie("Elf", Genre.COMEDY, Format.DVD);
//        List<Movie> movieList = new ArrayList<>(Arrays.asList(movie3, movie1, movie2));
//        for (Movie movie : movieList) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        Collections.sort(movieList);
//        for (Movie movie : movieList) {
//            System.out.println(movie);
//        }
    }

}
