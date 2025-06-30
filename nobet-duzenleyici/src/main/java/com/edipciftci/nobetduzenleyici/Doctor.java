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
    private String doctorType;
    private int seniorityLvl;
    private Map<String, Integer> shiftDayMap = new HashMap<>();
    private ArrayList<Shift> shifts;
    private final DBHandler dbHandler;
    private int monthLoad = 0;
    private int sinceLastShift = 0;

    public Doctor(String name, String hospital, String department, String doctorType, String mail, String seniorityLvl, DBHandler dbHandler){
        this.name = name;
        this.ID = this.doctorIDCreate(hospital, department, name);
        this.department = department;
        this.doctorType = doctorType;
        this.mail = mail;
        this.seniorityLvl = Integer.parseInt(seniorityLvl);
        this.shifts = new ArrayList<>();
        this.createShiftDayMap();

        this.dbHandler = dbHandler;
        this.dbHandler.insertDoctorToSQL(this.ID, this.name, this.department, this.doctorType, hospital, this.mail, this.seniorityLvl);
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
        this.shiftDayMap.put("Pazartesi", 0);
        this.shiftDayMap.put("Salı", 0);
        this.shiftDayMap.put("Çarşamba", 0);
        this.shiftDayMap.put("Perşembe", 0);
        this.shiftDayMap.put("Cuma", 0);
        this.shiftDayMap.put("Cumartesi", 0);
        this.shiftDayMap.put("Pazar", 0);
    }

    public void setShiftDayMap(Map<String, Integer> shiftDayMap){
        this.shiftDayMap = shiftDayMap;
    }
    
    public Map<String, Integer> getShiftDayMap(){
        return this.shiftDayMap;
    }

    public float getDayWeight(String currDay){
        int sum = 0;
        for (int dayValue : this.shiftDayMap.values()) {
            sum += dayValue;
        }

        return (this.shiftDayMap.get(currDay) / sum);
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
        if (this.sinceLastShift <= 4){
            return 0.0;
        }
        return (double) (this.sinceLastShift / 4);
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

        double result;
        double monthLoadFactor = Math.pow(this.monthLoad, 2);
        double dayWeight = Math.pow((1-(this.getDayWeight(shift.getWeekday()))), 2);
        double seniorityAdjuster = Math.pow(this.getSeniorityAdjuster(), 2);
        double lastShiftAdjuster = Math.pow(this.getLastShiftAdjuster(), 0.5);
        
        result = monthLoadFactor * dayWeight * seniorityAdjuster * lastShiftAdjuster * ((Math.random() * 0.3) + 0.85);

        return result;
    }

}
