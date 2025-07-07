package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Doctor {
    private final String name;
    private final String ID;
    private String mail;
    private String department;
    private String shortDep;
    private String doctorType;
    private final String hospital;
    private int seniorityLvl;
    private Map<String, Integer> shiftDayMap = new HashMap<>();
    private final ArrayList<Shift> shifts;
    private final DBHandler dbHandler;
    private int monthLoad = 0;
    private int sinceLastShift = 21;
    private Double currentShiftPoint;
    private int max;

    public Doctor(String name, String hospital, String department, String doctorType, String mail, String seniorityLvl, DBHandler dbHandler, boolean addToSQL){
        this.name = name;
        this.ID = this.doctorIDCreate(hospital, department, name);
        this.department = department;
        this.doctorType = doctorType;
        this.mail = mail;
        this.hospital = hospital;
        this.seniorityLvl = Integer.parseInt(seniorityLvl);
        this.shifts = new ArrayList<>();
        this.createShiftDayMap();

        this.dbHandler = dbHandler;
        if (addToSQL){
            this.dbHandler.insertDoctorToSQL(this.ID, this.name, this.department, this.doctorType, hospital, this.mail, this.seniorityLvl);
        }

        switch (doctorType){
            case "Uzman" -> this.max = 2;
            case "Kıdemli" -> this.max = 4;
            default -> this.max = 6;
        }
    }

    public String getName(){
        return this.name;
    }

    public String getHospital(){
        return this.hospital;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public String getMail(){
        return this.mail;
    }

    public void setDepartment(String department){
        this.department = department;
    }
    
    public String getDepartment(){
        return this.department;
    }

    public String getShortDep(){
        return this.shortDep;
    }

    public void setDoctorType(String doctorType){
        this.doctorType = doctorType;
    }
    
    public String getDoctorType(){
        return this.doctorType;
    }

    public void setSeniorityLevel(int seniorityLvl){
        this.seniorityLvl = seniorityLvl;
    }
    
    public int getSeniorityLevel(){
        return this.seniorityLvl;
    }

    @SuppressWarnings("FinalPrivateMethod")
    private final void createShiftDayMap(){
        this.shiftDayMap.put("MONDAY", 0);
        this.shiftDayMap.put("TUESDAY", 0);
        this.shiftDayMap.put("WEDNESDAY", 0);
        this.shiftDayMap.put("THURSDAY", 0);
        this.shiftDayMap.put("FRIDAY", 0);
        this.shiftDayMap.put("SATURDAY", 0);
        this.shiftDayMap.put("SUNDAY", 0);
    }

    public void addToShiftDayMap(int dayOfWeek, int dayValue){
        switch (dayOfWeek) {
            case 0 -> this.shiftDayMap.put("MONDAY", dayValue);
            case 1 -> this.shiftDayMap.put("TUESDAY", dayValue);
            case 2 -> this.shiftDayMap.put("WEDNESDAY", dayValue);
            case 3 -> this.shiftDayMap.put("THURSDAY", dayValue);
            case 4 -> this.shiftDayMap.put("FRIDAY", dayValue);
            case 5 -> this.shiftDayMap.put("SATURDAY", dayValue);
            case 6 -> this.shiftDayMap.put("SUNDAY", dayValue);
            default -> throw new AssertionError();
        }
    }

    public void setShiftDayMap(Map<String, Integer> shiftDayMap){
        this.shiftDayMap = shiftDayMap;
    }
    
    public Map<String, Integer> getShiftDayMap(){
        return this.shiftDayMap;
    }

    public void setShiftPoint(Shift shift){
        this.currentShiftPoint = this.calculateProbability(shift);
    }
    
    public Double getShiftPoint(){
        return this.currentShiftPoint;
    }

    public float getDayWeight(String currDay){
        int sum = 0;
        float res;
        for (int dayValue : this.shiftDayMap.values()) {
            sum += dayValue;
        }
        if (sum == 0){return (float) 0.01;}
        res = ((float)(this.shiftDayMap.get(currDay)) / (float)sum);
        if (res > 0.2) {return (float) 0.99;}
        return res;
    }

    private String doctorIDCreate(String hospital, String department, String name){

        String hospitalID = "999";
        String departmentID = "999";

        try{
            ObjectMapper mapper = new ObjectMapper();
            String fileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + "IDKeys.json";
            Map<String, Map<String, String>> idData = mapper.readValue(
                                                                new File(fileName),
                                                                new TypeReference<Map<String, Map<String, String>>>() {}
                                                                );
            hospitalID = idData.get("hospitals").get(hospital);
            departmentID = idData.get("departments").get(department);
            this.shortDep = idData.get("shortDeps").get(department);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        String dr = name.trim().replaceAll("\\s+", "").toLowerCase();
        dr = dr.replace("ç", "c")
                .replace("ğ", "g")
                .replace("ı", "i")
                .replace("ö", "o")
                .replace("ş", "s")
                .replace("ü", "u");
        
        int nameHash = Math.abs(dr.hashCode());
        
        return hospitalID + "." + departmentID + "." + String.valueOf(nameHash);
    }

    public String getDoctorID(){
        return this.ID;
    }

    public Double getSeniorityAdjuster(){
        int year = Year.now().getValue();
        Double exp = (double) (1 - ((year - this.seniorityLvl) / year));
        return exp;
    }

    public Double getLastShiftAdjuster(){
        if (this.sinceLastShift <= 3){
            return 0.0;
        }
        return (double) (this.sinceLastShift / 3);
    }

    public void increaseSinceLastShift(){
        this.sinceLastShift++;
    }

    public void newShift(Shift shift){
        this.monthLoad++;
        this.shifts.add(shift);

        this.shiftDayMap.put(shift.getWeekday(), this.shiftDayMap.get(shift.getWeekday()) + 1);

        this.sinceLastShift = 0;
    }

    public Double calculateProbability(Shift shift){

        // if ((this.shifts != null) || (shift.getMonth() != this.shifts.getLast().getMonth())){
        //     this.monthLoad = 0;
        // }

        if (this.max == this.monthLoad){
            return 0.0;
        }

        double result;
        double monthLoadFactor = Math.pow(1-(this.monthLoad / shift.getDayNum()), 2);
        double dayWeight = Math.pow((1-(this.getDayWeight(shift.getWeekday()))), 2);
        double seniorityAdjuster = Math.pow(this.getSeniorityAdjuster(), 2);
        double lastShiftAdjuster = Math.pow(this.getLastShiftAdjuster(), 2);
        
        result = monthLoadFactor * dayWeight * seniorityAdjuster * lastShiftAdjuster * ((Math.random() * 0.6) + 0.7);

        return result;
    }

}
