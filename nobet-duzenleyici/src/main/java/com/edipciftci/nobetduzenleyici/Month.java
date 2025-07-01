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

    private final Map<Integer,ArrayList<Shift>> shiftMap = new HashMap<>();

    private final String monthName;
    private final String firstDay;
    private DBHandler db;
    
    public Month(String month, DBHandler db){
        this.monthName = month;
        this.createShiftMap(this.getNumOfDays());
        this.firstDay = this.setFirstDay();
        this.db = db;
    }

    public String getMonthName(){
        return this.monthName;
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
        this.shiftMap.get(day).add(shift);
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

    public int getDayAsInt(String day){
        return switch (day.toLowerCase()) {
            case "monday" -> 1;
            case "tuesday" -> 2;
            case "wednesday" -> 3;
            case "thursday" -> 4;
            case "friday" -> 5;
            case "saturday" -> 6;
            default -> 7;
        };
    }

    public String getDayAsString(int day){
        return switch (day) {
            case 1 -> "MONDAY";
            case 2 -> "TUESDAY";
            case 3 -> "WEDNESDAY";
            case 4 -> "THURSDAY";
            case 5 -> "FRIDAY";
            case 6 -> "SATURDAY";
            default -> "SUNDAY";
        };
    }

    public void prepareShifts(ArrayList<Doctor> doctors){
        this.db.createMonthDB(this);
        int currDay = this.getDayAsInt(this.firstDay);
        for (int i = 1; i < this.shiftMap.size()+1; i++) {
            ArrayList<Shift> shiftsOfDay = new ArrayList<>();
            Shift shift = new Shift(this, i, this.getDayAsString(currDay), "Genel");
            shift.setSize(10);
            for (Doctor doctor : doctors) {
                doctor.setShiftPoint(shift);
                if (shift.isFull()){
                    if (doctor.getShiftPoint() > shift.getWorstDoctor().getShiftPoint()){
                        shift.removeWorstDoctor();
                        shift.addDoctor(doctor);
                    }
                } else {
                    shift.addDoctor(doctor);
                    shift.decideWorstDoctor();
                }
                doctor.increaseSinceLastShift();
            }
            for (Doctor doctor : shift.getDoctors()) {
                doctor.newShift(shift);
            }
            shiftsOfDay.add(shift);
            this.shiftMap.put(i, shiftsOfDay);
            this.db.addShift(this, shift);
            currDay = ((currDay) % 7) + 1;
        }
    }

}
