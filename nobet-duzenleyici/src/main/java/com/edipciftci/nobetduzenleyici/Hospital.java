package com.edipciftci.nobetduzenleyici;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Hospital {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Month> months = new ArrayList<>();
    private DBHandler db;
    private String name;
    private String shortName;
    private ArrayList<String> departments = new ArrayList<>();
    private Map<String, String> shortDeps = new HashMap<>();

    public Hospital(DBHandler db, String name){
        this.db = db;
        this.name = name;
        this.setShortName();
    }

    public String getName(){
        return  this.name;
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

    private void setShortName(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String fileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + "IDKeys.json";
            Map<String, Map<String, String>> idData = mapper.readValue(
                                                                new File(fileName),
                                                                new TypeReference<Map<String, Map<String, String>>>() {}
                                                                );
            this.shortName = idData.get("shortNames").get(this.name);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public String getShortName(){
        return this.shortName;
    }

    public void setDepartments(ArrayList<String> departments){
        this.departments = departments;
        this.setShortDeps();
    }
    
    public ArrayList<String> getDepartments(){
        return this.departments;
    }

    public void setShortDeps(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String fileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + "IDKeys.json";
            Map<String, Map<String, String>> idData = mapper.readValue(
                                                                new File(fileName),
                                                                new TypeReference<Map<String, Map<String, String>>>() {}
                                                                );
            for (String department : this.departments) {
                this.shortDeps.put(department, idData.get("shortDeps").get(department));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getShortDep(String department){
        return this.shortDeps.get(department);
    }

}
