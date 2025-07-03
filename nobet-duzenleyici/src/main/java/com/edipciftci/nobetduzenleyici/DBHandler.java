package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                            "type STRING," +
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

    public void insertDoctorToSQL(String doctorID, String name, String department, String doctorType, String hospital, String mail, int senior_level){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            String insertSql = "INSERT OR IGNORE INTO doctors (doctorID, name, department, hospital, email, senior_level, type, shift_days) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, doctorID);
                pstmt.setString(2, name);
                pstmt.setString(3, department);
                pstmt.setString(4, hospital);
                pstmt.setString(5, mail);
                pstmt.setInt(6, senior_level);
                pstmt.setString(7, doctorType);
                pstmt.setString(8, "0,0,0,0,0,0,0");
                pstmt.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertDoctorsToSQL(ArrayList<Doctor> doctors){
        String url = "jdbc:sqlite:" + this.dbPath;
        String insertSql = "INSERT OR IGNORE INTO doctors (doctorID, name, department,"+
                            " hospital, email, senior_level, type, shift_days) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url)){
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)){

                doctors.stream()
                        .forEach(
                            doctor -> {
                                try{
                                    pstmt.setString(1, doctor.getDoctorID());
                                    pstmt.setString(2, doctor.getName());
                                    pstmt.setString(3, doctor.getDepartment());
                                    pstmt.setString(4, doctor.getHospital());
                                    pstmt.setString(5, doctor.getMail());
                                    pstmt.setInt(6, doctor.getSeniorityLevel());
                                    pstmt.setString(7, doctor.getDoctorType());
                                    pstmt.setString(8, "0,0,0,0,0,0,0");
                                    pstmt.executeUpdate();
                                } catch (SQLException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                        );
            }
            conn.commit();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createMonthDB(Month mnt, Hospital hosp){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                System.out.println("Connected to SQLite.");

                String sql = "CREATE TABLE IF NOT EXISTS " +
                            hosp.getShortName() + "_" +
                            mnt.getMonthName() +
                            mnt.getYear() +
                            " (Day INTEGER," + 
                            "DOW TEXT, " +
                            "Area TEXT ";
                for (int i = 1; i < 16; i++) {
                    sql += ",DR" + Integer.toString(i) + " TEXT";
                }
                sql += ");";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);

                System.out.println(mnt.getMonthName() + " table is ready.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addShift(Month mnt, Shift shift){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            String insertSql = "INSERT OR IGNORE INTO " + shift.geHospital().getShortName() + "_" +
                            mnt.getMonthName() +
                            mnt.getYear() +
                            " (Day, DOW, Area, ";

            for (int i = 1; i < shift.getSize(); i++) {
                insertSql += "DR" + Integer.toString(i) + ", ";
            }
            insertSql += "DR" + Integer.toString(shift.getSize()) + ") VALUES (?, ?, ?, ";
            for (int i = 1; i < shift.getSize(); i++) {
                insertSql += "?, ";
            }
            insertSql += "?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                int j = 4;
                pstmt.setInt(1, shift.getDayNum());
                pstmt.setString(2, shift.getWeekday());
                pstmt.setString(3, shift.getShiftArea());
                for (Doctor dr : shift.getDoctors()) {
                    pstmt.setString(j, dr.getName());
                    j++;
                }
                pstmt.executeUpdate();
            }

            System.out.println("Day " + Integer.toString(shift.getDayNum()) + " of " + mnt.getMonthName() + " ----- Shifts are ready.");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addShifts(Month mnt, ArrayList<Shift> shifts, Hospital hosp){
        String url = "jdbc:sqlite:" + this.dbPath;
        int shiftSize = 15;
        String insertSql = "INSERT OR IGNORE INTO " + hosp.getShortName() + "_" +
                            mnt.getMonthName() +
                            mnt.getYear() +
                            " (Day, DOW, Area, ";

            for (int i = 1; i < shiftSize; i++) {
                insertSql += "DR" + Integer.toString(i) + ", ";
            }
            insertSql += "DR" + Integer.toString(shiftSize) + ") VALUES (?, ?, ?, ";
            for (int i = 1; i < shiftSize; i++) {
                insertSql += "?, ";
            }
            insertSql += "?)";

        try (Connection conn = DriverManager.getConnection(url)){
            
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

                shifts.stream()
                    .forEach(
                        shift -> {
                            try {
                                int j = 4;
                                pstmt.setInt(1, shift.getDayNum());
                                pstmt.setString(2, shift.getWeekday());
                                pstmt.setString(3, shift.getShiftArea());
                                for (Doctor dr : shift.getDoctors()) {
                                    pstmt.setString(j, dr.getName());
                                    j++;
                                }
                                pstmt.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    );
            }
            conn.commit();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Doctor> getDoctorsFromSQL(){
        ArrayList<Doctor> doctors = null;

        return doctors;
    }

}
