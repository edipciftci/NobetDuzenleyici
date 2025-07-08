package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayObject {

    private Shift genelShift;
    private Shift acilShift;
    private Shift yogunShift;
    private Shift servisShift;

    private int day;
    private ArrayList<Shift> shifts;

    private Map<String, ArrayList<Doctor>> doctorMap;

    public DayObject(int day){
        this.day = day;
        this.shifts = new ArrayList<>();
        doctorMap = new HashMap<>();
    }

    public void setGenelShift(Shift genelShift){
        this.genelShift = genelShift;
        this.shifts.add(genelShift);
    }
    
    public Shift getGenelShift(){
        return this.genelShift;
    }

    public void setAcilShift(Shift acilShift){
        this.acilShift = acilShift;
        this.shifts.add(acilShift);
    }
    
    public Shift getAcilShift(){
        return this.acilShift;
    }

    public void setYogunShift(Shift yogunShift){
        this.yogunShift = yogunShift;
        this.shifts.add(yogunShift);
    }
    
    public Shift getYogunShift(){
        return this.yogunShift;
    }

    public void setServisShift(Shift servisShift){
        this.servisShift = servisShift;
        this.shifts.add(servisShift);
    }
    
    public Shift getServisShift(){
        return this.servisShift;
    }

    public int getDay(){
        return this.day;
    }

    public void setShifts(ArrayList<Shift> shifts){
        this.shifts = shifts;
    }
    
    public ArrayList<Shift> getShifts(){
        return this.shifts;
    }

    public void addToDoctorMap(String key, Doctor dr){
        if (!doctorMap.containsKey(key)){
            doctorMap.put(key, new ArrayList<>());
        }
        doctorMap.get(key).add(dr);
    }
}
