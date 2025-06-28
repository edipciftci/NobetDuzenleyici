package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;

public class Shift {
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private Month month;
    private int dayNum, size;
    private String weekday, shiftArea; // Shift area = {Acil, Yoğun Bakım, Servis}
    private boolean full;

    public Shift(Month month, int dayNum, String weekday, String shiftArea){
        this.month = month;
        this.dayNum = dayNum;
        this.weekday = weekday;
        this.full = false;
        this.shiftArea = shiftArea;
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

    public void setSize(int size){
        this.size = size;
    }
    
    public int getSize(){
        return this.size;
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

    public void setFull(boolean full){
        this.full = full;
    }
    
    public boolean getFull(){
        return this.full;
    }

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
        if (this.size == this.doctors.size()){
            this.full = true;
        }
    }

    public boolean isDoctorInShift(Doctor doctor){
        for (Doctor dr : this.doctors) {
            if (dr == doctor){
                return true;
            }
        }
        return false;
    }

}
