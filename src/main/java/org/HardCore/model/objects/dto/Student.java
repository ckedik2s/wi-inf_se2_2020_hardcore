package org.HardCore.model.objects.dto;

import java.time.LocalDate;
import java.util.Date;

public class Student extends User{
    private String vorname = "Student";
    private String anrede = "Frau";
    private LocalDate gebDatum;

    public Student(User user) {
        super(user);
    }


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public LocalDate getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(LocalDate gebDatum) {
        this.gebDatum = gebDatum;
    }
}
