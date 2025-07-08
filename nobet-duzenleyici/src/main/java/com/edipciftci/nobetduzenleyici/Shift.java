package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;
public class Shift {
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private Hospital hosp;
    private String department;
    private String shortDep;
    private Month month;
    private int dayNum;
    private String weekday, shiftArea; // Shift area = {Acil, Yoğun Bakım, Servis}
    private Doctor[] worstDoctors = new Doctor[3];
    private int[] needMap = new int[3];
    private int size;

    public Shift(Hospital hosp, Month month, int dayNum, String weekday, String shiftArea, String department, int uzmanNeed, int kidemliNeed, int asistanNeed){
        this.hosp = hosp;
        this.month = month;
        this.dayNum = dayNum;
        this.weekday = weekday;
        this.shiftArea = shiftArea;
        this.worstDoctors[0] = null;
        this.worstDoctors[1] = null;
        this.worstDoctors[2] = null;
        this.department = department;
        this.shortDep = hosp.getShortDep(department);

        this.needMap[0] = uzmanNeed;
        this.needMap[1] = kidemliNeed;
        this.needMap[2] = asistanNeed;

        this.size = uzmanNeed + kidemliNeed + asistanNeed;
    }

    public void setMonth(Month month){
        this.month = month;
    }
    
    public Month getMonth(){
        return this.month;
    }

    public void setDayNum(int dayNum){
        this.dayNum = dayNum;
    }
    
    public int getDayNum(){
        return this.dayNum;
    }

    public void setWeekday(String weekday){
        this.weekday = weekday;
    }
    
    public String getWeekday(){
        return this.weekday;
    }

    public void setShiftArea(String shiftArea){
        this.shiftArea = shiftArea;
    }
    
    public String getShiftArea(){
        return this.shiftArea;
    }

    public void setWorstDoctor(Doctor worstDoctor){
        switch (worstDoctor.getDoctorType()) {
            case "Uzman" -> this.worstDoctors[0] = worstDoctor;
            case "Kıdemli" -> this.worstDoctors[1] = worstDoctor;
            default -> this.worstDoctors[2] = worstDoctor;
        }
    }
    
    public Doctor getWorstDoctor(String drType){
        return switch (drType) {
            case "Uzman" -> this.worstDoctors[0];
            case "Kıdemli" -> this.worstDoctors[1];
            default -> this.worstDoctors[2];
        };
    }

    public void decideWorstDoctor(String drType){
        int i;
        Double currWorstPoint = 999.9;
        switch (drType) {
            case "Uzman" -> i = 0;
            case "Kıdemli" -> i = 1;
            default -> i = 2;
        }
        if (this.worstDoctors[i] == null){
            this.worstDoctors[i] = this.doctors.getFirst();
        }
        for (Doctor doctor : this.doctors) {
            if (!(doctor.getDoctorType().equals(drType))){continue;}
            if (doctor.getShiftPoint() < currWorstPoint){
                this.worstDoctors[i] = doctor;
                currWorstPoint = doctor.getShiftPoint();
            }
        }
    }

    public ArrayList<Doctor> getDoctors(){
        return this.doctors;
    }

    public Hospital getHospital(){
        return this.hosp;
    }

    public boolean isFull(String drType){
        int currFullness = 0;
        for (Doctor doctor : this.doctors) {
            if (doctor.getDoctorType().equals(drType)){
                currFullness++;
            }
        }
        return switch (drType) {
            case "Uzman" -> (this.needMap[0] == currFullness);
            case "Kıdemli" -> (this.needMap[1] == currFullness);
            default -> (this.needMap[2] == currFullness);
        };
    }

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor){
        this.doctors.remove(doctor);
    }

    public void removeWorstDoctor(String drType){
        int i;
        switch (drType) {
            case "Uzman" -> i = 0;
            case "Kıdemli" -> i = 1;
            default -> i = 2;
        }
        this.removeDoctor(this.worstDoctors[i]);
        this.decideWorstDoctor(drType);
    }

    public boolean isDoctorInShift(Doctor doctor){
        for (Doctor dr : this.doctors) {
            if (dr == doctor){
                return true;
            }
        }
        return false;
    }

    public String getDepartment(){
        return this.department;
    }

    public String getShortDep(){
        return this.shortDep;
    }

    public int getSize(){
        return this.size;
    }

    public int getNeed(String drType){
        return switch (drType){
            case "Uzman" -> (this.needMap[0]);
            case "Kıdemli" -> (this.needMap[1]);
            case "Asistan" -> (this.needMap[2]);
            default -> throw new IllegalArgumentException("Invalid doctor type: " + drType);
        };
    }

}
