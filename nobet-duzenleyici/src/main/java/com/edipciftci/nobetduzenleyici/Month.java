package com.edipciftci.nobetduzenleyici;

import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Month {

    private final Map<Integer,DayObject> shiftMap = new HashMap<>();

    private final String monthName;
    private final String firstDay;
    private DBHandler db;
    private int year;
    
    public Month(String month, DBHandler db){
        this.monthName = month;
        this.createShiftMap(this.getNumOfDays());
        this.firstDay = this.setFirstDay();
        this.db = db;
    }

    public String getMonthName(){
        return this.monthName;
    }

    public int getYear(){
        return this.year;
    }

    @SuppressWarnings("FinalPrivateMethod")
    private final void createShiftMap(int numOfDays){
        DayObject dayObj;
        for (int i = 0; i < numOfDays; i++) {
            dayObj = new DayObject(i);
            shiftMap.put(i+1, dayObj);
        }
    }

    public void addToShiftMap(int day, Shift shift){
        switch (shift.getShiftArea()){
            case "Genel" -> this.shiftMap.get(day).setGenelShift(shift);
            case "Acil" -> this.shiftMap.get(day).setAcilShift(shift);
            case "Yoğun Bakım" -> this.shiftMap.get(day).setYogunShift(shift);
            case "Servis" -> this.shiftMap.get(day).setServisShift(shift);
        }
    }

    @SuppressWarnings("FinalPrivateMethod")
    public final Integer getNumOfDays(){
        return switch (this.monthName) {
            case "January", "March", "May", "July", "August", "October", "December" -> 31;
            case "February" -> 28;
            default -> 30;
        };
    }

    private String setFirstDay(){
        java.time.Month m = java.time.Month.valueOf(monthName.toUpperCase(Locale.ENGLISH));
        ZoneId tz = ZoneId.of("Europe/Istanbul");
        int year = Year.now(tz).getValue();
        this.year = year;
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

    public String getDayAsWeekday(int dayNum){
        int day = ((dayNum + this.getDayAsInt(firstDay) - 1) % 7);
        return this.getDayAsString(day);
    }

    public void prepareShifts(ArrayList<Doctor> doctors, Hospital hosp){
        this.db.createMonthDB(this, hosp);

        String[] drTypes = {"Uzman", "Kıdemli", "Asistan"};
        ArrayList<Shift> shifts = new ArrayList<>();

        for (String department : hosp.getDepartments()) {
            this.shiftMap.entrySet().stream()
                    .forEach(
                        day -> {
                            int date = day.getKey();
                            DayObject dayObj = day.getValue();
                            for (Shift currShift : dayObj.getShifts()) {
                                if (!currShift.getDepartment().equals(department)){continue;}
                                for (String drType : drTypes) {
                                    if (currShift.getNeed(drType) == 0){continue;}
                                    ArrayList<Doctor> currDocs = (ArrayList<Doctor>) doctors.stream()
                                                                    .filter(dr -> dr.getDoctorType().equals(drType))
                                                                    .peek(dr -> dr.setShiftPoint(currShift))
                                                                    .sorted(Comparator.comparingDouble(Doctor::getShiftPoint).reversed())
                                                                    .limit(currShift.getNeed(drType))
                                                                    .sorted(Comparator.comparingDouble(Doctor::getShiftPoint))
                                                                    .collect(Collectors.toList());
                                    currDocs.stream().forEach(
                                        dr -> {
                                                String key = date + "_" + currShift.getShiftArea() + "_" + drType;
                                                dayObj.addToDoctorMap(key, dr);
                                                dr.newShift(currShift);
                                            }
                                    ); 
                                }
                                shifts.add(currShift);
                            }
                        }
                    );
            this.db.addShifts(this, shifts, hosp, department);
        }
    }

}
