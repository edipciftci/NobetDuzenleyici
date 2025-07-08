package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        this.createDoctorTable();
        this.createShiftTable();
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
        int maxSize = 20;
        for (String department : hosp.getDepartments()) {
            try (Connection conn = DriverManager.getConnection(url)){
                if (conn != null){
                    String sql = "CREATE TABLE IF NOT EXISTS " +
                                hosp.getShortName() +
                                hosp.getShortDep(department) + "_" +
                                mnt.getMonthName() +
                                mnt.getYear() +
                                " (Day INTEGER," + 
                                "DOW TEXT, " +
                                "Area TEXT ";
                    for (int i = 1; i < 21; i++) {
                        sql += ",DR" + Integer.toString(i) + " TEXT";
                    }
                    sql += ");";
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void addShift(Month mnt, Shift shift){
        String url = "jdbc:sqlite:" + this.dbPath;
        try (Connection conn = DriverManager.getConnection(url)){
            String insertSql = "INSERT OR IGNORE INTO " + shift.getHospital().getShortName() + shift.getShortDep() + "_" +
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
                    pstmt.setString(j, dr.getName() + " (" + dr.getDoctorType() + ")");
                    j++;
                }
                pstmt.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addShifts(Month mnt, ArrayList<Shift> shifts, Hospital hosp, String department){
        String url = "jdbc:sqlite:" + this.dbPath;
        int shiftSize = shifts.stream()
            .mapToInt(Shift::getSize)
            .max()
            .orElse(20);

        String insertSql = "INSERT OR IGNORE INTO " + hosp.getShortName() + hosp.getShortDep(department) + "_" +
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
                                    pstmt.setString(j, dr.getName() + " (" + dr.getDoctorType() + ")");
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
        ArrayList<Doctor> doctors = new ArrayList<>();
        String url = "jdbc:sqlite:" + this.dbPath;

        String sqlString = "SELECT name, department, hospital, email, senior_level, type, shift_days FROM doctors";

        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString)){

                while (rs.next()){
                    String  name     = rs.getString("name");
                    String  dept     = rs.getString("department");
                    String  hosp     = rs.getString("hospital");
                    String  email    = rs.getString("email");
                    int     level    = rs.getInt("senior_level");
                    String  type     = rs.getString("type");
                    String[] shiftDayMap   = rs.getString("shift_days").split(",");

                    Doctor dr = new Doctor(name, hosp, dept, type, email, Integer.toString(level), this, false);
                    for (int i = 0; i < shiftDayMap.length; i++) {
                        dr.addToShiftDayMap(i, Integer.parseInt(shiftDayMap[i]));
                    }
                    doctors.add(dr);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }

        return doctors;
    }

    public void cleanSQL(){
        String url = "jdbc:sqlite:" + this.dbPath;
        String[] save = {
            "doctors", "Shift_Table"
        };
        Set<String> savedTables = new HashSet<>(Arrays.asList(save));
        List<String> toDrop = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false);

            // 1) Get all table names
            String fetchSql = 
                "SELECT name FROM sqlite_master " +
                "WHERE type = 'table' " +
                "  AND name NOT LIKE 'sqlite_%';";  // skip SQLite internal tables

            try (Statement stmt = conn.createStatement();
                ResultSet rs   = stmt.executeQuery(fetchSql)) {

                while (rs.next()) {
                    String table = rs.getString("name");
                    if (!(savedTables.contains(table))) {
                        toDrop.add(table);
                    }
                }
            }

            // 2) Drop each unwanted table
            try (Statement dropStmt = conn.createStatement()) {
                for (String table : toDrop) {
                    String sql = "DROP TABLE IF EXISTS " + table + ";";
                    dropStmt.executeUpdate(sql);
                    System.out.println("Dropped table: " + table);
                }
            }

            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanShiftTable(){
        String url = "jdbc:sqlite:" + this.dbPath;

        try (Connection conn = DriverManager.getConnection(url)) {
            // 2) Drop each unwanted table
            try (Statement dropStmt = conn.createStatement()) {

                String sql = "DROP TABLE IF EXISTS Shift_Table;";
                dropStmt.executeUpdate(sql);

            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.createShiftTable();
    }

    public void createShiftTable(){
        String url = "jdbc:sqlite:" + this.dbPath;
        
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                String sql = "CREATE TABLE IF NOT EXISTS " +
                            "Shift_Table (Day INTEGER, Month TEXT, Hospital TEXT, Department TEXT, Area TEXT, Uzman INTEGER, Kıdemli INTEGER, Asistan INTEGER);";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    public void addShiftSettings(int day, String month, String hosp, String department, String area, Map<String, Integer> drNumbers){
        String url = "jdbc:sqlite:" + this.dbPath;

        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                String insertSql = "INSERT OR IGNORE INTO Shift_Table " +
                                    "(Day, Month, Hospital, Department, Area, Uzman, Kıdemli, Asistan)" +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setInt(1, day);
                    pstmt.setString(2, month);
                    pstmt.setString(3, hosp);
                    pstmt.setString(4, department);
                    pstmt.setString(5, area);
                    pstmt.setInt(6, drNumbers.get("Uzman"));
                    pstmt.setInt(7, drNumbers.get("Kıdemli"));
                    pstmt.setInt(8, drNumbers.get("Asistan"));
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMultipleShiftSettings(String month, String hosp, Map<String, Map<String, Integer>> shifts){
        String department, area;
        int day;
        Map<String, Integer> drNumbers;
        String url = "jdbc:sqlite:" + this.dbPath;

        String insertSql = "INSERT OR IGNORE INTO Shift_Table " +
                                "(Day, Month, Hospital, Department, Area, Uzman, Kıdemli, Asistan)" +
                                "VALUES " +
                                "(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url)){
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                for (String shiftKey : shifts.keySet()) {
                    department = shiftKey.split("_")[0];
                    area = shiftKey.split("_")[1];
                    day = Integer.parseInt(shiftKey.split("_")[2]);

                    drNumbers = shifts.get(shiftKey);
                
                    pstmt.setInt(1, day);
                    pstmt.setString(2, month);
                    pstmt.setString(3, hosp);
                    pstmt.setString(4, department);
                    pstmt.setString(5, area);
                    pstmt.setInt(6, drNumbers.get("Uzman"));
                    pstmt.setInt(7, drNumbers.get("Kıdemli"));
                    pstmt.setInt(8, drNumbers.get("Asistan"));
                    pstmt.executeUpdate();
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getShiftsFromSQL(Hospital hosp, Month month) {
        String sql = "SELECT Day, Hospital, Department, Area, Uzman, Kıdemli, Asistan FROM Shift_Table WHERE Month = ?";
        String hospName  = hosp.getName();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, month.getMonthName());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (!(hospName.equals(rs.getString("Hospital")))){continue;}

                    int day          = rs.getInt("Day");
                    String dept      = rs.getString("Department");
                    String area      = rs.getString("Area");
                    int uzmanNeed = rs.getInt("Uzman");
                    int kidemliNeed = rs.getInt("Kıdemli");
                    int asistanNeed = rs.getInt("Asistan");

                    Shift shift = new Shift(
                                            hosp,
                                            month,
                                            day,
                                            month.getDayAsWeekday(day),
                                            area,
                                            dept,
                                            uzmanNeed,
                                            kidemliNeed,
                                            asistanNeed
                                        );

                    month.addToShiftMap(day, shift);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Got the shifts of " + month.getMonthName() + " for " + hospName);
    }

}
