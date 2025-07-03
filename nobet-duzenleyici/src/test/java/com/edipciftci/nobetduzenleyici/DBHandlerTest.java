package com.edipciftci.nobetduzenleyici;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DBHandlerTest {
    @Test
    public void testCreateDBAndAddDoctor() {
        DBHandler db = new DBHandler();
        db.createDB();
        Doctor doctor = new Doctor("Irmak Durgun",
                                "Bilkent Şehir Hastanesi",
                                "Nöroloji",
                                "Asistan",
                                "irmakdurgun@gmail.com",
                                "2023",
                                db,
                                true);
        assertNotNull(doctor);
    }
}
