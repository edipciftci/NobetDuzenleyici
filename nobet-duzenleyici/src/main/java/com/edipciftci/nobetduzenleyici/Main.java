package com.edipciftci.nobetduzenleyici;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] hospitalNames = {
                                    "Bilkent Şehir Hastanesi",
                                    "Ankara Eğitim ve Araştırma Hastanesi",
                                    "Ankara Etlik Şehir Hastanesi",
                                    "Güven Hastanesi"
                                };
        
        ArrayList<String> departments = new ArrayList<>();
        departments.add("Nöroloji");
        departments.add("Kardiyoloji");
        departments.add("Kulak Burun Boğaz");
        departments.add("Radyoloji");
        departments.add("Ortopedi");

        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Hospital> hospitals = new ArrayList<>();

        DBHandler db = new DBHandler();

        db.createDB();

        ArrayList<Map<String, String>> doctorList = createDoctors();

        doctors = addDoctors(doctorList, db, doctors);

        for (String hospital : hospitalNames) {
                Hospital hosp = new Hospital(db, hospital);
                hosp.setDepartments(departments);
                hosp.setDoctors((ArrayList<Doctor>) doctors.stream().filter(dr -> dr.getHospital().equals(hospital)).collect(Collectors.toList()));
                hospitals.add(hosp);
            }

        System.out.println("Here");
        
        for (Hospital hosp : hospitals){
            hosp.newMonth("July");
            hosp.newMonth("August");
            for (Month mnt : hosp.getMonths()) {
                long start = System.nanoTime();
                mnt.prepareShifts(hosp.getDoctors(), hosp);
                long end = System.nanoTime();
                long durNS = end - start;
                double durMS = durNS / 1_000_000.0;
                System.out.println("It took " + durMS + " ms to prepare " + mnt.getMonthName());
            }
        }

    }

    public static ArrayList<Doctor> addDoctors(ArrayList<Map<String, String>> doctorList, DBHandler db, ArrayList<Doctor> doctors){
        System.out.println("Doctor creation has started.");
        long start = System.nanoTime();
        long end, durNS;
        double durMS;
        for (Map<String,String> doctor : doctorList) {
            Doctor dr = new Doctor(doctor.get("name"),
                                    doctor.get("hospital"),
                                    doctor.get("department"),
                                    doctor.get("doctorType"),
                                    doctor.get("mail"),
                                    doctor.get("seniorityLevel"),
                                    db,
                                    false);
            doctors.add(dr);
        }
        end = System.nanoTime();
        durNS = end - start;
        durMS = durNS / 1_000_000.0;
        System.out.println("It took " + durMS + " ms to create " + doctorList.size() + " doctors.");

        start = System.nanoTime();
        db.insertDoctorsToSQL(doctors);
        end = System.nanoTime();
        durNS = end - start;
        durMS = durNS / 1_000_000.0;
        System.out.println("It took " + durMS + " ms to insert " + doctorList.size() + " doctors.");

        return doctors;
    }

    public static ArrayList<Map<String, String>> createDoctors() throws IOException{
        
        ArrayList<Map<String, String>> doctorList = new ArrayList<>();

        Map<String, String> d1 = new HashMap<>();
        d1.put("name", "Irmak Durgun");
        d1.put("hospital", "Bilkent Şehir Hastanesi");
        d1.put("doctorType", "Asistan");
        d1.put("department", "Nöroloji");
        d1.put("mail", "irmakdurgun@gmail.com");
        d1.put("seniorityLevel", "2023");
        doctorList.add(d1);

        Map<String, String> d2 = new HashMap<>();
        d2.put("name", "Edip Çiftçi");
        d2.put("hospital", "Bilkent Şehir Hastanesi");
        d2.put("doctorType", "Asistan");
        d2.put("department", "Kardiyoloji");
        d2.put("mail", "edipciftci@gmail.com");
        d2.put("seniorityLevel", "2024");
        doctorList.add(d2);

        Map<String, String> d3 = new HashMap<>();
        d3.put("name", "Şevval Şen");
        d3.put("hospital", "Bilkent Şehir Hastanesi");
        d3.put("doctorType", "Asistan");
        d3.put("department", "Kardiyoloji");
        d3.put("mail", "sevvalsen@gmail.com");
        d3.put("seniorityLevel", "2024");
        doctorList.add(d3);

        Map<String, String> d4 = new HashMap<>();
        d4.put("name", "İrem Gürcan");
        d4.put("hospital", "Ankara Eğitim ve Araştırma Hastanesi");
        d4.put("doctorType", "Asistan");
        d4.put("department", "Radyoloji");
        d4.put("mail", "iremgurcan@gmail.com");
        d4.put("seniorityLevel", "2020");
        doctorList.add(d4);

        Map<String, String> d5 = new HashMap<>();
        d5.put("name", "Alp Yazıcıoğlu");
        d5.put("hospital", "Ankara Etlik Şehir Hastanesi");
        d5.put("doctorType", "Asistan");
        d5.put("department", "Kulak Burun Boğaz");
        d5.put("mail", "alpyazicioglu@gmail.com");
        d5.put("seniorityLevel", "2022");
        doctorList.add(d5);

        Map<String, String> d6 = new HashMap<>();
        d6.put("name", "Çağatay Bozkurt");
        d6.put("hospital", "Güven Hastanesi");
        d6.put("doctorType", "Kıdemli");
        d6.put("department", "Ortopedi");
        d6.put("mail", "cagataybozkurt@gmail.com");
        d6.put("seniorityLevel", "2019");
        doctorList.add(d6);

        Map<String, String> d7 = new HashMap<>();
        d7.put("name", "Ediz Çiftçi");
        d7.put("hospital", "Güven Hastanesi");
        d7.put("doctorType", "Uzman");
        d7.put("department", "Ortopedi");
        d7.put("mail", "edizciftci@gmail.com");
        d7.put("seniorityLevel", "2010");
        doctorList.add(d7);

        List<String> male = Files.readAllLines(Paths.get("names", "male_name_tally"), StandardCharsets.UTF_8).stream()
            .filter(line -> Integer.parseInt(line.split("\\s+")[1].trim()) >= 5000)
            .map(line -> line.split("\\s+")[0].trim())
            .map(name -> {
                String lower = name.toLowerCase(Locale.ROOT);
                return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
                })
            .filter(name -> name.length() > 2)
            .collect(Collectors.toList());
        List<String> female = Files.readAllLines(Paths.get("names", "female_name_tally"), StandardCharsets.UTF_8).stream()
            .filter(line -> Integer.parseInt(line.split("\\s+")[1].trim()) >= 5000)
            .map(line -> line.split("\\s+")[0].trim())
            .map(name -> {
                String lower = name.toLowerCase(Locale.ROOT);
                return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
                })
            .filter(name -> name.length() > 2)
            .collect(Collectors.toList());
        List<String> surnames = Files.readAllLines(Paths.get("names", "lastname_tally.csv"), StandardCharsets.UTF_8).stream()
            .filter(line -> Integer.parseInt(line.split("\\s+")[1].trim()) >= 10000)
            .map(line -> line.split(" ")[0].trim())
            .map(name -> {
                String lower = name.toLowerCase(Locale.ROOT);
                return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
                })
            .filter(name -> name.length() > 2)
            .collect(Collectors.toList());

        List<String> firstNames = new ArrayList<>();
        firstNames.addAll(male);
        firstNames.addAll(female);

        // Prepare a pool of unique full names
        Random rand = new Random(42);
        Set<String> usedNames = doctorList.stream()
            .map(m -> m.get("name"))
            .collect(Collectors.toCollection(HashSet::new));
        List<String> namePool = new ArrayList<>();
        int totalNeeded = 4 * 5 * 80; // hospitals * departments * max per group
        while (namePool.size() < totalNeeded) {
            String full = firstNames.get(rand.nextInt(firstNames.size()))
                         + " "
                         + surnames .get(rand.nextInt(surnames.size()));
            if (usedNames.add(full)) {
                namePool.add(full);
            }
        }
        Iterator<String> nameIter = namePool.iterator();

        // 3) Generate per hospital/department
        List<String> hospitals = Arrays.asList(
            "Bilkent Şehir Hastanesi",
            "Ankara Eğitim ve Araştırma Hastanesi",
            "Ankara Etlik Şehir Hastanesi",
            "Güven Hastanesi"
        );
        List<String> departments = Arrays.asList(
            "Nöroloji", "Kardiyoloji", "Radyoloji", "Kulak Burun Boğaz", "Ortopedi"
        );

        for (String hospital : hospitals) {
            for (String dept : departments) {
                // calculate amounts
                int total   = 50 + rand.nextInt(31); // 50-80
                int uMin    = (int)Math.ceil(total * 0.10);
                int uMax    = (int)Math.floor(total * 0.15);
                int needU   = uMin  + rand.nextInt(uMax - uMin + 1);
                int kMin    = (int)Math.ceil(total * 0.25);
                int kMax    = (int)Math.floor(total * 0.30);
                int needK   = kMin  + rand.nextInt(kMax - kMin + 1);
                int needA   = total - needU - needK;

                // subtract static counts
                long haveU = doctorList.stream()
                    .filter(m -> m.get("hospital").equals(hospital)
                              && m.get("department").equals(dept)
                              && m.get("doctorType").equals("Uzman"))
                    .count();
                long haveK = doctorList.stream()
                    .filter(m -> m.get("hospital").equals(hospital)
                              && m.get("department").equals(dept)
                              && m.get("doctorType").equals("Kıdemli"))
                    .count();
                long haveA = doctorList.stream()
                    .filter(m -> m.get("hospital").equals(hospital)
                              && m.get("department").equals(dept)
                              && m.get("doctorType").equals("Asistan"))
                    .count();

                int toGenU = Math.max(0, needU - (int)haveU);
                int toGenK = Math.max(0, needK - (int)haveK);
                int toGenA = Math.max(0, needA - (int)haveA);

                // helper to create map
                for (int i = 0; i < toGenU; i++) {
                    String name = nameIter.next();
                    int year   = 1990 + rand.nextInt(2005 - 1990 + 1);
                    Map<String,String> m = new HashMap<>();
                    m.put("name", name);
                    m.put("hospital", hospital);
                    m.put("department", dept);
                    m.put("doctorType", "Uzman");
                    m.put("mail", name.toLowerCase().replaceAll("[^a-z]","") + "@gmail.com");
                    m.put("seniorityLevel", String.valueOf(year));
                    doctorList.add(m);
                }
                for (int i = 0; i < toGenK; i++) {
                    String name = nameIter.next();
                    int year   = 1997 + rand.nextInt(2018 - 1997 + 1);
                    Map<String,String> m = new HashMap<>();
                    m.put("name", name);
                    m.put("hospital", hospital);
                    m.put("department", dept);
                    m.put("doctorType", "Kıdemli");
                    m.put("mail", name.toLowerCase().replaceAll("[^a-z]","") + year + "@example.com");
                    m.put("seniorityLevel", String.valueOf(year));
                    doctorList.add(m);
                }
                for (int i = 0; i < toGenA; i++) {
                    String name = nameIter.next();
                    int year   = 2008 + rand.nextInt(2025 - 2008 + 1);
                    Map<String,String> m = new HashMap<>();
                    m.put("name", name);
                    m.put("hospital", hospital);
                    m.put("department", dept);
                    m.put("doctorType", "Asistan");
                    m.put("mail", name.toLowerCase().replaceAll("[^a-z]","") + year + "@example.com");
                    m.put("seniorityLevel", String.valueOf(year));
                    doctorList.add(m);
                }
            }
        }

        return doctorList;
    }

}