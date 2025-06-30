package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;

public class Hospital {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Month> months = new ArrayList<>();

    public Hospital(){

    }

    public void newMonth(String month){
        Month newMonth = new Month(month);
        this.months.add(newMonth);
    }

}
