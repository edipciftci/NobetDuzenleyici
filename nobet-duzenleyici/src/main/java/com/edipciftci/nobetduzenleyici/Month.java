package com.edipciftci.nobetduzenleyici;

import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Month {

    private final Map<Integer,Shift> shiftMap = new HashMap<>();

    private String monthName;
    private String firstDay;
    
    public Month(String month){
        this.monthName = month;
        this.createShiftMap(this.getNumOfDays());
        this.firstDay = this.setFirstDay();
    }

    @SuppressWarnings("FinalPrivateMethod")
    private final void createShiftMap(int numOfDays){
        for (int i = 0; i < numOfDays; i++) {
            shiftMap.put(i+1, null);
        }
    }

    @SuppressWarnings("FinalPrivateMethod")
    private final Integer getNumOfDays(){
        return switch (this.monthName) {
            case "January", "March", "May", "July", "August", "October", "December" -> 31;
            case "February" -> 28;
            default -> 30;
        };
    }

    public void newShift(int day, Shift shift){
        this.shiftMap.put(day, shift);
    }

    private String setFirstDay(){
        java.time.Month m = java.time.Month.valueOf(monthName.toUpperCase(Locale.ENGLISH));
        ZoneId tz = ZoneId.of("Europe/Istanbul");
        int year = Year.now(tz).getValue();
        YearMonth ym = YearMonth.of(year, m);
        ZonedDateTime zdt = ym.atDay(1).atStartOfDay(tz);

        if ((year % 4 == 0) && (m == java.time.Month.FEBRUARY)){
            this.shiftMap.put(29, null);
        }

        return zdt.getDayOfWeek().name();
    }

    public String getFirstDay(){
        return this.firstDay;
    }

    public void prepareShifts(ArrayList<Doctor> doctors){
        
    }

}
