package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;

public class Hospital {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Month> months = new ArrayList<>();
    private DBHandler db;

    public Hospital(DBHandler db){
        this.db = db;
    }

    public void newMonth(String month){
        Month newMonth = new Month(month, this.db);
        this.months.add(newMonth);
    }

    public ArrayList<Month> getMonths(){
        return this.months;
    }

    public void setDoctors(ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }

    public ArrayList<Doctor> getDoctors(){
        return this.doctors;
    }

}
