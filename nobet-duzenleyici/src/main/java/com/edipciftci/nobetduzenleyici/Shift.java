package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;

public class Shift {
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private Month month;
    private int dayNum, size;
    private String weekday, shiftArea; // Shift area = {Acil, Yoğun Bakım, Servis}
    private Doctor worstDoctor;

    public Shift(Month month, int dayNum, String weekday, String shiftArea){
        this.month = month;
        this.dayNum = dayNum;
        this.weekday = weekday;
        this.shiftArea = shiftArea;
        this.worstDoctor = null;
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

    public void setWorstDoctor(Doctor worstDoctor){
        this.worstDoctor = worstDoctor;
    }
    
    public Doctor getWorstDoctor(){
        return this.worstDoctor;
    }

    public void decideWorstDoctor(){
        if (this.worstDoctor == null){
            this.worstDoctor = this.doctors.getFirst();
        }
        for (Doctor doctor : this.doctors) {
            if (doctor.getShiftPoint() < this.worstDoctor.getShiftPoint()){
                this.worstDoctor = doctor;
            }
        }
    }

    public ArrayList<Doctor> getDoctors(){
        return this.doctors;
    }

    public boolean isFull(){
        return (this.doctors.size() == this.size);
    }

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor){
        this.doctors.remove(doctor);
    }

    public void removeWorstDoctor(){
        this.removeDoctor(this.worstDoctor);
        this.worstDoctor = this.doctors.getFirst();
        for (Doctor dr : this.doctors) {
            if (dr.getShiftPoint() < this.worstDoctor.getShiftPoint()) {
                this.worstDoctor = dr;
            }
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
