package com.edipciftci.nobetduzenleyici;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        ArrayList<Doctor> doctors = new ArrayList<>();
        String hospitalName = "Bilkent Şehir Hastanesi";

        DBHandler db = new DBHandler();

        db.createDB();

        ArrayList<Map<String, String>> doctorList = new ArrayList<>();

        Map<String, String> doctor1 = new HashMap<>();
        doctor1.put("name", "Irmak Durgun");
        doctor1.put("department", "Nöroloji");
        doctor1.put("mail", "irmakdurgun@gmail.com");
        doctor1.put("seniorityLevel", "2023");
        doctorList.add(doctor1);

        Map<String, String> doctor2 = new HashMap<>();
        doctor2.put("name", "Edip Çiftçi");
        doctor2.put("department", "Kardiyoloji");
        doctor2.put("mail", "edipciftci@gmail.com");
        doctor2.put("seniorityLevel", "2024");
        doctorList.add(doctor2);

        Map<String, String> doctor3 = new HashMap<>();
        doctor3.put("name", "Şevval Şen");
        doctor3.put("department", "Kardiyoloji");
        doctor3.put("mail", "sevvalsen@gmail.com");
        doctor3.put("seniorityLevel", "2024");
        doctorList.add(doctor3);

        Map<String, String> doctor4 = new HashMap<>();
        doctor4.put("name", "İrem Gürcan");
        doctor4.put("department", "Radyoloji");
        doctor4.put("mail", "iremgurcan@gmail.com");
        doctor4.put("seniorityLevel", "2020");
        doctorList.add(doctor4);

        Map<String, String> doctor5 = new HashMap<>();
        doctor5.put("name", "Alp Yazıcıoğlu");
        doctor5.put("department", "Kulak Burun Boğaz");
        doctor5.put("mail", "alpyazicioglu@gmail.com");
        doctor5.put("seniorityLevel", "2022");
        doctorList.add(doctor5);

        Map<String, String> doctor6 = new HashMap<>();
        doctor6.put("name", "Çağatay Bozkurt");
        doctor6.put("department", "Ortopedi");
        doctor6.put("mail", "cagataybozkurt@gmail.com");
        doctor6.put("seniorityLevel", "2019");
        doctorList.add(doctor6);

        Map<String, String> doctor7 = new HashMap<>();
        doctor7.put("name", "Ediz Çiftçi");
        doctor7.put("department", "Ortopedi");
        doctor7.put("mail", "edizciftci@gmail.com");
        doctor7.put("seniorityLevel", "2010");
        doctorList.add(doctor7);

        Map<String, String> doctor8 = new HashMap<>();
        doctor8.put("name", "Ayşe Demir");
        doctor8.put("department", "Nöroloji");
        doctor8.put("mail", "aysedemir@gmail.com");
        doctor8.put("seniorityLevel", "2017");
        doctorList.add(doctor8);

        Map<String, String> doctor9 = new HashMap<>();
        doctor9.put("name", "Mehmet Yılmaz");
        doctor9.put("department", "Kardiyoloji");
        doctor9.put("mail", "mehmetyilmaz@gmail.com");
        doctor9.put("seniorityLevel", "2018");
        doctorList.add(doctor9);

        Map<String, String> doctor10 = new HashMap<>();
        doctor10.put("name", "Fatma Kaya");
        doctor10.put("department", "Radyoloji");
        doctor10.put("mail", "fatmakaya@gmail.com");
        doctor10.put("seniorityLevel", "2015");
        doctorList.add(doctor10);

        Map<String, String> doctor11 = new HashMap<>();
        doctor11.put("name", "Ahmet Şahin");
        doctor11.put("department", "Kulak Burun Boğaz");
        doctor11.put("mail", "ahmetsahin@gmail.com");
        doctor11.put("seniorityLevel", "2016");
        doctorList.add(doctor11);

        Map<String, String> doctor12 = new HashMap<>();
        doctor12.put("name", "Elif Koç");
        doctor12.put("department", "Ortopedi");
        doctor12.put("mail", "elifkoc@gmail.com");
        doctor12.put("seniorityLevel", "2019");
        doctorList.add(doctor12);

        Map<String, String> doctor13 = new HashMap<>();
        doctor13.put("name", "Burak Özkan");
        doctor13.put("department", "Nöroloji");
        doctor13.put("mail", "burakozkan@gmail.com");
        doctor13.put("seniorityLevel", "2020");
        doctorList.add(doctor13);

        Map<String, String> doctor14 = new HashMap<>();
        doctor14.put("name", "Zeynep Aydın");
        doctor14.put("department", "Kardiyoloji");
        doctor14.put("mail", "zeynepaydin@gmail.com");
        doctor14.put("seniorityLevel", "2021");
        doctorList.add(doctor14);

        Map<String, String> doctor15 = new HashMap<>();
        doctor15.put("name", "Emre Polat");
        doctor15.put("department", "Radyoloji");
        doctor15.put("mail", "emrepolat@gmail.com");
        doctor15.put("seniorityLevel", "2014");
        doctorList.add(doctor15);

        Map<String, String> doctor16 = new HashMap<>();
        doctor16.put("name", "Derya Uçar");
        doctor16.put("department", "Kulak Burun Boğaz");
        doctor16.put("mail", "deryaucar@gmail.com");
        doctor16.put("seniorityLevel", "2013");
        doctorList.add(doctor16);

        Map<String, String> doctor17 = new HashMap<>();
        doctor17.put("name", "Onur Güneş");
        doctor17.put("department", "Ortopedi");
        doctor17.put("mail", "onurgunes@gmail.com");
        doctor17.put("seniorityLevel", "2012");
        doctorList.add(doctor17);

        Map<String, String> doctor18 = new HashMap<>();
        doctor18.put("name", "Gizem Yıldız");
        doctor18.put("department", "Nöroloji");
        doctor18.put("mail", "gizemyildiz@gmail.com");
        doctor18.put("seniorityLevel", "2011");
        doctorList.add(doctor18);

        Map<String, String> doctor19 = new HashMap<>();
        doctor19.put("name", "Murat Aksoy");
        doctor19.put("department", "Kardiyoloji");
        doctor19.put("mail", "murataksoy@gmail.com");
        doctor19.put("seniorityLevel", "2010");
        doctorList.add(doctor19);

        Map<String, String> doctor20 = new HashMap<>();
        doctor20.put("name", "Selin Arslan");
        doctor20.put("department", "Radyoloji");
        doctor20.put("mail", "selinarslan@gmail.com");
        doctor20.put("seniorityLevel", "2009");
        doctorList.add(doctor20);

        Map<String, String> doctor21 = new HashMap<>();
        doctor21.put("name", "Barış Kılıç");
        doctor21.put("department", "Kulak Burun Boğaz");
        doctor21.put("mail", "bariskilic@gmail.com");
        doctor21.put("seniorityLevel", "2008");
        doctorList.add(doctor21);

        Map<String, String> doctor22 = new HashMap<>();
        doctor22.put("name", "Seda Yavuz");
        doctor22.put("department", "Ortopedi");
        doctor22.put("mail", "sedayavuz@gmail.com");
        doctor22.put("seniorityLevel", "2007");
        doctorList.add(doctor22);

        Map<String, String> doctor23 = new HashMap<>();
        doctor23.put("name", "Kerem Çelik");
        doctor23.put("department", "Nöroloji");
        doctor23.put("mail", "keremcelik@gmail.com");
        doctor23.put("seniorityLevel", "2006");
        doctorList.add(doctor23);

        Map<String, String> doctor24 = new HashMap<>();
        doctor24.put("name", "Buse Erdem");
        doctor24.put("department", "Kardiyoloji");
        doctor24.put("mail", "buseerdem@gmail.com");
        doctor24.put("seniorityLevel", "2005");
        doctorList.add(doctor24);

        Map<String, String> doctor25 = new HashMap<>();
        doctor25.put("name", "Tolga Yüce");
        doctor25.put("department", "Radyoloji");
        doctor25.put("mail", "tolgayuce@gmail.com");
        doctor25.put("seniorityLevel", "2004");
        doctorList.add(doctor25);

        Map<String, String> doctor26 = new HashMap<>();
        doctor26.put("name", "Melis Akın");
        doctor26.put("department", "Kulak Burun Boğaz");
        doctor26.put("mail", "melisakin@gmail.com");
        doctor26.put("seniorityLevel", "2003");
        doctorList.add(doctor26);

        Map<String, String> doctor27 = new HashMap<>();
        doctor27.put("name", "Ege Tan");
        doctor27.put("department", "Ortopedi");
        doctor27.put("mail", "egetan@gmail.com");
        doctor27.put("seniorityLevel", "2002");
        doctorList.add(doctor27);

        Map<String, String> doctor28 = new HashMap<>();
        doctor28.put("name", "Sinem Korkmaz");
        doctor28.put("department", "Nöroloji");
        doctor28.put("mail", "sinemkorkmaz@gmail.com");
        doctor28.put("seniorityLevel", "1999");
        doctorList.add(doctor28);

        Map<String, String> doctor29 = new HashMap<>();
        doctor29.put("name", "Canan Yıldırım");
        doctor29.put("department", "Kardiyoloji");
        doctor29.put("mail", "cananyildirim@gmail.com");
        doctor29.put("seniorityLevel", "1998");
        doctorList.add(doctor29);

        Map<String, String> doctor30 = new HashMap<>();
        doctor30.put("name", "Serkan Özdemir");
        doctor30.put("department", "Radyoloji");
        doctor30.put("mail", "serkanozdemir@gmail.com");
        doctor30.put("seniorityLevel", "1997");
        doctorList.add(doctor30);

        Map<String, String> doctor31 = new HashMap<>();
        doctor31.put("name", "Yasemin Şimşek");
        doctor31.put("department", "Kulak Burun Boğaz");
        doctor31.put("mail", "yaseminsimsek@gmail.com");
        doctor31.put("seniorityLevel", "1996");
        doctorList.add(doctor31);

        Map<String, String> doctor32 = new HashMap<>();
        doctor32.put("name", "Deniz Karaca");
        doctor32.put("department", "Ortopedi");
        doctor32.put("mail", "denizkaraca@gmail.com");
        doctor32.put("seniorityLevel", "2022");
        doctorList.add(doctor32);

        Map<String, String> doctor33 = new HashMap<>();
        doctor33.put("name", "İlker Yıldız");
        doctor33.put("department", "Nöroloji");
        doctor33.put("mail", "ilkeryildiz@gmail.com");
        doctor33.put("seniorityLevel", "2023");
        doctorList.add(doctor33);

        Map<String, String> doctor34 = new HashMap<>();
        doctor34.put("name", "Pelin Güler");
        doctor34.put("department", "Kardiyoloji");
        doctor34.put("mail", "pelinguler@gmail.com");
        doctor34.put("seniorityLevel", "2025");
        doctorList.add(doctor34);

        Map<String, String> doctor35 = new HashMap<>();
        doctor35.put("name", "Mert Yıldırım");
        doctor35.put("department", "Radyoloji");
        doctor35.put("mail", "mertyildirim1@gmail.com");
        doctor35.put("seniorityLevel", "2018");
        doctorList.add(doctor35);

        Map<String, String> doctor36 = new HashMap<>();
        doctor36.put("name", "Sibel Demir");
        doctor36.put("department", "Kulak Burun Boğaz");
        doctor36.put("mail", "sibeldemir1@gmail.com");
        doctor36.put("seniorityLevel", "2017");
        doctorList.add(doctor36);

        Map<String, String> doctor37 = new HashMap<>();
        doctor37.put("name", "Cem Aksoy");
        doctor37.put("department", "Ortopedi");
        doctor37.put("mail", "cemaksoy1@gmail.com");
        doctor37.put("seniorityLevel", "2016");
        doctorList.add(doctor37);

        Map<String, String> doctor38 = new HashMap<>();
        doctor38.put("name", "Ece Yılmaz");
        doctor38.put("department", "Nöroloji");
        doctor38.put("mail", "eceyilmaz1@gmail.com");
        doctor38.put("seniorityLevel", "2015");
        doctorList.add(doctor38);

        Map<String, String> doctor39 = new HashMap<>();
        doctor39.put("name", "Berkay Şahin");
        doctor39.put("department", "Kardiyoloji");
        doctor39.put("mail", "berkaysahin1@gmail.com");
        doctor39.put("seniorityLevel", "2014");
        doctorList.add(doctor39);

        Map<String, String> doctor40 = new HashMap<>();
        doctor40.put("name", "Gülşah Polat");
        doctor40.put("department", "Radyoloji");
        doctor40.put("mail", "gulsahpolat1@gmail.com");
        doctor40.put("seniorityLevel", "2013");
        doctorList.add(doctor40);

        Map<String, String> doctor41 = new HashMap<>();
        doctor41.put("name", "Onur Yüce");
        doctor41.put("department", "Kulak Burun Boğaz");
        doctor41.put("mail", "onuryuce1@gmail.com");
        doctor41.put("seniorityLevel", "2012");
        doctorList.add(doctor41);

        Map<String, String> doctor42 = new HashMap<>();
        doctor42.put("name", "Meltem Korkmaz");
        doctor42.put("department", "Ortopedi");
        doctor42.put("mail", "meltemkorkmaz1@gmail.com");
        doctor42.put("seniorityLevel", "2011");
        doctorList.add(doctor42);

        Map<String, String> doctor43 = new HashMap<>();
        doctor43.put("name", "Serhat Tan");
        doctor43.put("department", "Nöroloji");
        doctor43.put("mail", "serhattan1@gmail.com");
        doctor43.put("seniorityLevel", "2010");
        doctorList.add(doctor43);

        Map<String, String> doctor44 = new HashMap<>();
        doctor44.put("name", "Büşra Erdem");
        doctor44.put("department", "Kardiyoloji");
        doctor44.put("mail", "busraerdem1@gmail.com");
        doctor44.put("seniorityLevel", "2009");
        doctorList.add(doctor44);

        Map<String, String> doctor45 = new HashMap<>();
        doctor45.put("name", "Kaan Özkan");
        doctor45.put("department", "Radyoloji");
        doctor45.put("mail", "kaanozkan1@gmail.com");
        doctor45.put("seniorityLevel", "2008");
        doctorList.add(doctor45);

        Map<String, String> doctor46 = new HashMap<>();
        doctor46.put("name", "Sena Güler");
        doctor46.put("department", "Kulak Burun Boğaz");
        doctor46.put("mail", "senaguler1@gmail.com");
        doctor46.put("seniorityLevel", "2007");
        doctorList.add(doctor46);

        Map<String, String> doctor47 = new HashMap<>();
        doctor47.put("name", "Tuna Arslan");
        doctor47.put("department", "Ortopedi");
        doctor47.put("mail", "tunarslan1@gmail.com");
        doctor47.put("seniorityLevel", "2006");
        doctorList.add(doctor47);

        Map<String, String> doctor48 = new HashMap<>();
        doctor48.put("name", "Bora Koç");
        doctor48.put("department", "Nöroloji");
        doctor48.put("mail", "borakoc1@gmail.com");
        doctor48.put("seniorityLevel", "2005");
        doctorList.add(doctor48);

        Map<String, String> doctor49 = new HashMap<>();
        doctor49.put("name", "Duygu Yıldız");
        doctor49.put("department", "Kardiyoloji");
        doctor49.put("mail", "duyguyildiz1@gmail.com");
        doctor49.put("seniorityLevel", "2004");
        doctorList.add(doctor49);

        Map<String, String> doctor50 = new HashMap<>();
        doctor50.put("name", "Baran Akın");
        doctor50.put("department", "Radyoloji");
        doctor50.put("mail", "baranakin1@gmail.com");
        doctor50.put("seniorityLevel", "2003");
        doctorList.add(doctor50);

        Map<String, String> doctor51 = new HashMap<>();
        doctor51.put("name", "Sude Tan");
        doctor51.put("department", "Kulak Burun Boğaz");
        doctor51.put("mail", "sudetan1@gmail.com");
        doctor51.put("seniorityLevel", "2002");
        doctorList.add(doctor51);

        Map<String, String> doctor52 = new HashMap<>();
        doctor52.put("name", "Eren Şimşek");
        doctor52.put("department", "Ortopedi");
        doctor52.put("mail", "erensimsek1@gmail.com");
        doctor52.put("seniorityLevel", "1999");
        doctorList.add(doctor52);

        Map<String, String> doctor53 = new HashMap<>();
        doctor53.put("name", "İpek Karaca");
        doctor53.put("department", "Nöroloji");
        doctor53.put("mail", "ipekkaraca1@gmail.com");
        doctor53.put("seniorityLevel", "1998");
        doctorList.add(doctor53);

        Map<String, String> doctor54 = new HashMap<>();
        doctor54.put("name", "Yusuf Özdemir");
        doctor54.put("department", "Kardiyoloji");
        doctor54.put("mail", "yusufozdemir1@gmail.com");
        doctor54.put("seniorityLevel", "1997");
        doctorList.add(doctor54);

        Map<String, String> doctor55 = new HashMap<>();
        doctor55.put("name", "Aylin Şimşek");
        doctor55.put("department", "Radyoloji");
        doctor55.put("mail", "aylinsimsek1@gmail.com");
        doctor55.put("seniorityLevel", "1996");
        doctorList.add(doctor55);

        Map<String, String> doctor56 = new HashMap<>();
        doctor56.put("name", "Murat Yıldız");
        doctor56.put("department", "Kulak Burun Boğaz");
        doctor56.put("mail", "muratyildiz1@gmail.com");
        doctor56.put("seniorityLevel", "2025");
        doctorList.add(doctor56);

        Map<String, String> doctor57 = new HashMap<>();
        doctor57.put("name", "Selin Demir");
        doctor57.put("department", "Ortopedi");
        doctor57.put("mail", "selindemir1@gmail.com");
        doctor57.put("seniorityLevel", "2024");
        doctorList.add(doctor57);

        Map<String, String> doctor58 = new HashMap<>();
        doctor58.put("name", "Kerem Aksoy");
        doctor58.put("department", "Nöroloji");
        doctor58.put("mail", "keremaksoy1@gmail.com");
        doctor58.put("seniorityLevel", "2023");
        doctorList.add(doctor58);

        Map<String, String> doctor59 = new HashMap<>();
        doctor59.put("name", "Buse Yılmaz");
        doctor59.put("department", "Kardiyoloji");
        doctor59.put("mail", "buseyilmaz1@gmail.com");
        doctor59.put("seniorityLevel", "2022");
        doctorList.add(doctor59);

        Map<String, String> doctor60 = new HashMap<>();
        doctor60.put("name", "Tolga Şahin");
        doctor60.put("department", "Radyoloji");
        doctor60.put("mail", "tolgasahin1@gmail.com");
        doctor60.put("seniorityLevel", "2021");
        doctorList.add(doctor60);

        Map<String, String> doctor61 = new HashMap<>();
        doctor61.put("name", "Derya Polat");
        doctor61.put("department", "Kulak Burun Boğaz");
        doctor61.put("mail", "deryapolat1@gmail.com");
        doctor61.put("seniorityLevel", "2020");
        doctorList.add(doctor61);

        Map<String, String> doctor62 = new HashMap<>();
        doctor62.put("name", "Emre Yüce");
        doctor62.put("department", "Ortopedi");
        doctor62.put("mail", "emreyuce1@gmail.com");
        doctor62.put("seniorityLevel", "2019");
        doctorList.add(doctor62);

        Map<String, String> doctor63 = new HashMap<>();
        doctor63.put("name", "Gizem Korkmaz");
        doctor63.put("department", "Nöroloji");
        doctor63.put("mail", "gizemkorkmaz1@gmail.com");
        doctor63.put("seniorityLevel", "2018");
        doctorList.add(doctor63);

        Map<String, String> doctor64 = new HashMap<>();
        doctor64.put("name", "Burak Tan");
        doctor64.put("department", "Kardiyoloji");
        doctor64.put("mail", "buraktan1@gmail.com");
        doctor64.put("seniorityLevel", "2017");
        doctorList.add(doctor64);

        Map<String, String> doctor65 = new HashMap<>();
        doctor65.put("name", "Elif Erdem");
        doctor65.put("department", "Radyoloji");
        doctor65.put("mail", "eliferdem1@gmail.com");
        doctor65.put("seniorityLevel", "2016");
        doctorList.add(doctor65);

        Map<String, String> doctor66 = new HashMap<>();
        doctor66.put("name", "Barış Özkan");
        doctor66.put("department", "Kulak Burun Boğaz");
        doctor66.put("mail", "barisozkan1@gmail.com");
        doctor66.put("seniorityLevel", "2015");
        doctorList.add(doctor66);

        Map<String, String> doctor67 = new HashMap<>();
        doctor67.put("name", "Seda Polat");
        doctor67.put("department", "Ortopedi");
        doctor67.put("mail", "sedapolat1@gmail.com");
        doctor67.put("seniorityLevel", "2014");
        doctorList.add(doctor67);

        Map<String, String> doctor68 = new HashMap<>();
        doctor68.put("name", "Ahmet Yıldız");
        doctor68.put("department", "Nöroloji");
        doctor68.put("mail", "ahmetyildiz1@gmail.com");
        doctor68.put("seniorityLevel", "2013");
        doctorList.add(doctor68);

        Map<String, String> doctor69 = new HashMap<>();
        doctor69.put("name", "Pelin Demir");
        doctor69.put("department", "Kardiyoloji");
        doctor69.put("mail", "pelindemir1@gmail.com");
        doctor69.put("seniorityLevel", "2012");
        doctorList.add(doctor69);

        Map<String, String> doctor70 = new HashMap<>();
        doctor70.put("name", "Mete Aksoy");
        doctor70.put("department", "Radyoloji");
        doctor70.put("mail", "meteaksoy1@gmail.com");
        doctor70.put("seniorityLevel", "2011");
        doctorList.add(doctor70);

        Map<String, String> doctor71 = new HashMap<>();
        doctor71.put("name", "Sinem Yüce");
        doctor71.put("department", "Kulak Burun Boğaz");
        doctor71.put("mail", "sinemyuce1@gmail.com");
        doctor71.put("seniorityLevel", "2010");
        doctorList.add(doctor71);

        Map<String, String> doctor72 = new HashMap<>();
        doctor72.put("name", "Canan Şimşek");
        doctor72.put("department", "Ortopedi");
        doctor72.put("mail", "canansimsek1@gmail.com");
        doctor72.put("seniorityLevel", "2009");
        doctorList.add(doctor72);

        Map<String, String> doctor73 = new HashMap<>();
        doctor73.put("name", "Serkan Karaca");
        doctor73.put("department", "Nöroloji");
        doctor73.put("mail", "serkankaraca1@gmail.com");
        doctor73.put("seniorityLevel", "2008");
        doctorList.add(doctor73);

        Map<String, String> doctor74 = new HashMap<>();
        doctor74.put("name", "Yasemin Özdemir");
        doctor74.put("department", "Kardiyoloji");
        doctor74.put("mail", "yaseminozdemir1@gmail.com");
        doctor74.put("seniorityLevel", "2007");
        doctorList.add(doctor74);

        Map<String, String> doctor75 = new HashMap<>();
        doctor75.put("name", "Deniz Şahin");
        doctor75.put("department", "Radyoloji");
        doctor75.put("mail", "denizsahin1@gmail.com");
        doctor75.put("seniorityLevel", "2006");
        doctorList.add(doctor75);

        Map<String, String> doctor76 = new HashMap<>();
        doctor76.put("name", "İlker Akın");
        doctor76.put("department", "Kulak Burun Boğaz");
        doctor76.put("mail", "ilkerakin1@gmail.com");
        doctor76.put("seniorityLevel", "2005");
        doctorList.add(doctor76);

        Map<String, String> doctor77 = new HashMap<>();
        doctor77.put("name", "Pelin Tan");
        doctor77.put("department", "Ortopedi");
        doctor77.put("mail", "pelintan1@gmail.com");
        doctor77.put("seniorityLevel", "2004");
        doctorList.add(doctor77);

        Map<String, String> doctor78 = new HashMap<>();
        doctor78.put("name", "Emre Şimşek");
        doctor78.put("department", "Nöroloji");
        doctor78.put("mail", "emresimsek1@gmail.com");
        doctor78.put("seniorityLevel", "2003");
        doctorList.add(doctor78);

        Map<String, String> doctor79 = new HashMap<>();
        doctor79.put("name", "Derya Karaca");
        doctor79.put("department", "Kardiyoloji");
        doctor79.put("mail", "deryakaraca1@gmail.com");
        doctor79.put("seniorityLevel", "2002");
        doctorList.add(doctor79);

        Map<String, String> doctor80 = new HashMap<>();
        doctor80.put("name", "Onur Özkan");
        doctor80.put("department", "Radyoloji");
        doctor80.put("mail", "onurozkan1@gmail.com");
        doctor80.put("seniorityLevel", "1999");
        doctorList.add(doctor80);

        Map<String, String> doctor81 = new HashMap<>();
        doctor81.put("name", "Gülşah Yıldız");
        doctor81.put("department", "Kulak Burun Boğaz");
        doctor81.put("mail", "gulsahyildiz1@gmail.com");
        doctor81.put("seniorityLevel", "1998");
        doctorList.add(doctor81);

        Map<String, String> doctor82 = new HashMap<>();
        doctor82.put("name", "Berkay Demir");
        doctor82.put("department", "Ortopedi");
        doctor82.put("mail", "berkaydemir1@gmail.com");
        doctor82.put("seniorityLevel", "1997");
        doctorList.add(doctor82);

        Map<String, String> doctor83 = new HashMap<>();
        doctor83.put("name", "Sibel Aksoy");
        doctor83.put("department", "Nöroloji");
        doctor83.put("mail", "sibelaksoy1@gmail.com");
        doctor83.put("seniorityLevel", "1996");
        doctorList.add(doctor83);

        Map<String, String> doctor84 = new HashMap<>();
        doctor84.put("name", "Mert Polat");
        doctor84.put("department", "Kardiyoloji");
        doctor84.put("mail", "mertpolat1@gmail.com");
        doctor84.put("seniorityLevel", "2025");
        doctorList.add(doctor84);

        Map<String, String> doctor85 = new HashMap<>();
        doctor85.put("name", "Sena Yüce");
        doctor85.put("department", "Radyoloji");
        doctor85.put("mail", "senayuce1@gmail.com");
        doctor85.put("seniorityLevel", "2024");
        doctorList.add(doctor85);

        Map<String, String> doctor86 = new HashMap<>();
        doctor86.put("name", "Cem Özdemir");
        doctor86.put("department", "Kulak Burun Boğaz");
        doctor86.put("mail", "cemozdemir1@gmail.com");
        doctor86.put("seniorityLevel", "2023");
        doctorList.add(doctor86);

        Map<String, String> doctor87 = new HashMap<>();
        doctor87.put("name", "Ece Şahin");
        doctor87.put("department", "Ortopedi");
        doctor87.put("mail", "ecesahin1@gmail.com");
        doctor87.put("seniorityLevel", "2022");
        doctorList.add(doctor87);

        Map<String, String> doctor88 = new HashMap<>();
        doctor88.put("name", "Bora Demir");
        doctor88.put("department", "Nöroloji");
        doctor88.put("mail", "borademir1@gmail.com");
        doctor88.put("seniorityLevel", "2021");
        doctorList.add(doctor88);

        Map<String, String> doctor89 = new HashMap<>();
        doctor89.put("name", "Gülşah Aksoy");
        doctor89.put("department", "Kardiyoloji");
        doctor89.put("mail", "gulsahaksoy1@gmail.com");
        doctor89.put("seniorityLevel", "2020");
        doctorList.add(doctor89);

        Map<String, String> doctor90 = new HashMap<>();
        doctor90.put("name", "Onur Polat");
        doctor90.put("department", "Radyoloji");
        doctor90.put("mail", "onurpolat1@gmail.com");
        doctor90.put("seniorityLevel", "2019");
        doctorList.add(doctor90);

        Map<String, String> doctor91 = new HashMap<>();
        doctor91.put("name", "Meltem Yıldız");
        doctor91.put("department", "Kulak Burun Boğaz");
        doctor91.put("mail", "meltemyildiz1@gmail.com");
        doctor91.put("seniorityLevel", "2018");
        doctorList.add(doctor91);

        Map<String, String> doctor92 = new HashMap<>();
        doctor92.put("name", "Serhat Demir");
        doctor92.put("department", "Ortopedi");
        doctor92.put("mail", "serhatdemir1@gmail.com");
        doctor92.put("seniorityLevel", "2017");
        doctorList.add(doctor92);

        Map<String, String> doctor93 = new HashMap<>();
        doctor93.put("name", "Büşra Aksoy");
        doctor93.put("department", "Nöroloji");
        doctor93.put("mail", "busraaksoy1@gmail.com");
        doctor93.put("seniorityLevel", "2016");
        doctorList.add(doctor93);

        Map<String, String> doctor94 = new HashMap<>();
        doctor94.put("name", "Kaan Yüce");
        doctor94.put("department", "Kardiyoloji");
        doctor94.put("mail", "kaanyuce1@gmail.com");
        doctor94.put("seniorityLevel", "2015");
        doctorList.add(doctor94);

        Map<String, String> doctor95 = new HashMap<>();
        doctor95.put("name", "Sena Tan");
        doctor95.put("department", "Radyoloji");
        doctor95.put("mail", "senatan1@gmail.com");
        doctor95.put("seniorityLevel", "2014");
        doctorList.add(doctor95);

        Map<String, String> doctor96 = new HashMap<>();
        doctor96.put("name", "Tuna Şimşek");
        doctor96.put("department", "Kulak Burun Boğaz");
        doctor96.put("mail", "tunasimsek1@gmail.com");
        doctor96.put("seniorityLevel", "2013");
        doctorList.add(doctor96);

        Map<String, String> doctor97 = new HashMap<>();
        doctor97.put("name", "Bora Yıldız");
        doctor97.put("department", "Ortopedi");
        doctor97.put("mail", "borayildiz1@gmail.com");
        doctor97.put("seniorityLevel", "2012");
        doctorList.add(doctor97);

        Map<String, String> doctor98 = new HashMap<>();
        doctor98.put("name", "Duygu Demir");
        doctor98.put("department", "Nöroloji");
        doctor98.put("mail", "duygudemir1@gmail.com");
        doctor98.put("seniorityLevel", "2011");
        doctorList.add(doctor98);

        Map<String, String> doctor99 = new HashMap<>();
        doctor99.put("name", "Baran Aksoy");
        doctor99.put("department", "Kardiyoloji");
        doctor99.put("mail", "baranaksoy1@gmail.com");
        doctor99.put("seniorityLevel", "2010");
        doctorList.add(doctor99);

        Map<String, String> doctor100 = new HashMap<>();
        doctor100.put("name", "Sude Polat");
        doctor100.put("department", "Radyoloji");
        doctor100.put("mail", "sudepolat1@gmail.com");
        doctor100.put("seniorityLevel", "2009");
        doctorList.add(doctor100);

        Map<String, String> doctor101 = new HashMap<>();
        doctor101.put("name", "Eren Yüce");
        doctor101.put("department", "Kulak Burun Boğaz");
        doctor101.put("mail", "erenyuce1@gmail.com");
        doctor101.put("seniorityLevel", "2008");
        doctorList.add(doctor101);

        Map<String, String> doctor102 = new HashMap<>();
        doctor102.put("name", "İpek Demir");
        doctor102.put("department", "Ortopedi");
        doctor102.put("mail", "ipekdemir1@gmail.com");
        doctor102.put("seniorityLevel", "2007");
        doctorList.add(doctor102);

        Map<String, String> doctor103 = new HashMap<>();
        doctor103.put("name", "Yusuf Aksoy");
        doctor103.put("department", "Nöroloji");
        doctor103.put("mail", "yusufaksoy1@gmail.com");
        doctor103.put("seniorityLevel", "2006");
        doctorList.add(doctor103);

        Map<String, String> doctor104 = new HashMap<>();
        doctor104.put("name", "Aylin Yıldız");
        doctor104.put("department", "Kardiyoloji");
        doctor104.put("mail", "aylinyildiz1@gmail.com");
        doctor104.put("seniorityLevel", "2005");
        doctorList.add(doctor104);

        Map<String, String> doctor105 = new HashMap<>();
        doctor105.put("name", "Murat Demir");
        doctor105.put("department", "Radyoloji");
        doctor105.put("mail", "muratdemir1@gmail.com");
        doctor105.put("seniorityLevel", "2004");
        doctorList.add(doctor105);

        Map<String, String> doctor106 = new HashMap<>();
        doctor106.put("name", "Selin Aksoy");
        doctor106.put("department", "Kulak Burun Boğaz");
        doctor106.put("mail", "selinaksoy1@gmail.com");
        doctor106.put("seniorityLevel", "2003");
        doctorList.add(doctor106);

        Map<String, String> doctor107 = new HashMap<>();
        doctor107.put("name", "Kerem Yüce");
        doctor107.put("department", "Ortopedi");
        doctor107.put("mail", "keremyuce1@gmail.com");
        doctor107.put("seniorityLevel", "2002");
        doctorList.add(doctor107);

        Map<String, String> doctor108 = new HashMap<>();
        doctor108.put("name", "Buse Şimşek");
        doctor108.put("department", "Nöroloji");
        doctor108.put("mail", "busesimsek1@gmail.com");
        doctor108.put("seniorityLevel", "1999");
        doctorList.add(doctor108);

        Map<String, String> doctor109 = new HashMap<>();
        doctor109.put("name", "Tolga Demir");
        doctor109.put("department", "Kardiyoloji");
        doctor109.put("mail", "tolgademir1@gmail.com");
        doctor109.put("seniorityLevel", "1998");
        doctorList.add(doctor109);

        Map<String, String> doctor110 = new HashMap<>();
        doctor110.put("name", "Derya Aksoy");
        doctor110.put("department", "Radyoloji");
        doctor110.put("mail", "deryaaksoy1@gmail.com");
        doctor110.put("seniorityLevel", "1997");
        doctorList.add(doctor110);

        Map<String, String> doctor111 = new HashMap<>();
        doctor111.put("name", "Emre Yıldız");
        doctor111.put("department", "Kulak Burun Boğaz");
        doctor111.put("mail", "emreyildiz1@gmail.com");
        doctor111.put("seniorityLevel", "1996");
        doctorList.add(doctor111);

        for (Map<String,String> doctor : doctorList) {
            Doctor dr = new Doctor(doctor.get("name"),
                                    hospitalName,
                                    doctor.get("department"),
                                    doctor.get("mail"),
                                    doctor.get("seniorityLevel"),
                                    db);
            doctors.add(dr);
        }

    }

}