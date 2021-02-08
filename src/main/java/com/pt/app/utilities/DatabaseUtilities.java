package com.pt.app.utilities;

import java.sql.*;

public class DatabaseUtilities {
    private static final String URL = "jdbc:sqlite:";


    public static void createNewDatabase(String fileName) {
        String url = URL + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection(String fileName) throws SQLException {
        String url = URL + fileName;
        return DriverManager.getConnection(url);
    }

    public static void executeStatement(String fileName, String sql) {
        String url = URL + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String constructCreateTableString(String tableName, String... columns) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]);
            if (i != columns.length - 1) stringBuilder.append(", ");
            else stringBuilder.append(");");
        }
        return stringBuilder.toString();
    }

    public static boolean tableExsists(String fileName, String tableName) {
        try (Connection conn = getConnection(fileName)) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet results = metaData.getTables(null, null, tableName, null);
            return results.next();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }

}
