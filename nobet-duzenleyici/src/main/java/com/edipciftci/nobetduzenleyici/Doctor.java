package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Doctor {
    private final String name;
    private final String ID;
    private String mail;
    private String department;
    private int seniorityLvl;
    private boolean senior;
    private Map<String, Integer> shiftDayMap;
    private final DBHandler dbHandler;

    public Doctor(String name, String hospital, String department, String mail, String seniorityLvl, DBHandler dbHandler){
        this.name = name;
        this.ID = this.doctorIDCreate(hospital, department, name);
        this.department = department;
        this.mail = mail;
        this.seniorityLvl = Integer.parseInt(seniorityLvl);
        if (this.seniorityLvl <= 2005){
            this.senior = true;
        } else {
            this.senior = false;
        }
        this.dbHandler = dbHandler;
        
        this.dbHandler.insertDoctorToSQL(this.ID, this.name, this.department, hospital, this.mail, this.seniorityLvl);
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

    public void setSeniorityLevel(int seniorityLvl){
        this.seniorityLvl = seniorityLvl;
    }
    
    public int getSeniorityLevel(){
        return this.seniorityLvl;
    }

    public void setIfSenior(boolean senior){
        this.senior = senior;
    }
    
    public boolean getIfSenior(){
        return this.senior;
    }
    
    public void createShiftDayMap(){
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

    public void calculateProbability(Shift shift){
        // TODO: shifts this month var should be implemented
    }

}
