package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
    private static final String DB_FILENAME = "hospital.db";
    private String dbPath;

    public void createDB(){
        String appDataPath = System.getProperty("user.dir");
        this.dbPath = appDataPath + File.separator + "db" + File.separator + DB_FILENAME;

        // Check if database file exists
        File dbFile = new File(this.dbPath);
        try {
            if (dbFile.createNewFile()) {
                System.out.println("File created: " + dbFile.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + dbFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        createDoctorTable();
    }

    public void createDoctorTable(){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                System.out.println("Connected to SQLite.");

                String sql = "CREATE TABLE IF NOT EXISTS doctors (" +
                            "id INTEGER PRIMARY KEY, " +
                            "doctorID TEXT NOT NULL UNIQUE," +
                            "name TEXT NOT NULL," +
                            "department TEXT," +
                            "hospital TEXT," +
                            "email TEXT," +
                            "senior_level INTEGER," +
                            "shift_days TEXT" +
                            ");";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);

                System.out.println("Table ready.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertDoctorToSQL(String doctorID, String name, String department, String hospital, String mail, int senior_level){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            String insertSql = "INSERT OR IGNORE INTO doctors (doctorID, name, department, hospital, email, senior_level, shift_days) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, doctorID);
                pstmt.setString(2, name);
                pstmt.setString(3, department);
                pstmt.setString(4, hospital);
                pstmt.setString(5, mail);
                pstmt.setInt(6, senior_level);
                pstmt.setString(7, "0,0,0,0,0,0,0");
                pstmt.executeUpdate();

                System.out.println("Inserted Doctor: " + name);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
