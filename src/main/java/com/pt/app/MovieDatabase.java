package com.pt.app;

import com.pt.app.utilities.DatabaseUtilities;
import com.pt.app.utilities.FileUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private final String dbFileName;

    public MovieDatabase(String dbFileName) {
        this.dbFileName = dbFileName;
        initialize();
    }

    public void insertNewMovieIntoMoviesTable(Movie movie) {
        String sql = "INSERT INTO movies (title, genre, format) VALUES(?,?,?)";
        try (Connection conn = DatabaseUtilities.getConnection(dbFileName)) {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre().name());
            preparedStatement.setString(3, movie.getFormat().name());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Movie> getAllMoviesFromDatabase() {
        String sql = "SELECT id, title, genre, format FROM movies";
        List<Movie> movieList = new ArrayList<>();
        try (Connection conn = DatabaseUtilities.getConnection(dbFileName)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            parseDBQueryToMovieList(movieList, resultSet);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return movieList;
    }

    private void parseDBQueryToMovieList(List<Movie> movieList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            Genre genre = Genre.valueOf(resultSet.getString("genre"));
            Format format = Format.valueOf(resultSet.getString("format"));
            movieList.add(new Movie(title, genre, format));
        }
    }

    private void initialize() {
        if (!FileUtilities.fileExists(this.dbFileName)) {
            DatabaseUtilities.createNewDatabase(this.dbFileName);
        }
        if (!DatabaseUtilities.tableExsists(this.dbFileName, "movies")) {
            createMoviesTableInDatabase();
        }
    }

    private void createMoviesTableInDatabase() {
        String col1 = "id integer PRIMARY KEY";
        String col2 = "title text NOT NULL";
        String col3 = "genre text";
        String col4 = "format text";
        String createTableStatement = DatabaseUtilities.constructCreateTableString("movies", col1, col2, col3, col4);
        DatabaseUtilities.executeStatement(dbFileName, createTableStatement);
    }
}
