package org.HardCore.model.objects.dto;

import java.time.LocalDate;
import java.util.Date;

public class Student extends User{
    private String anrede ="Herr";
    private String vorname ="vorname";
    private String name = "name";
    private String hochschule ="hochschule";
    private String semester = "semester";
    private LocalDate gebDatum = LocalDate.now();
    private String kenntnisse ="Kenntnisse";
    private String studiengang ="Studiengang";

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getHochschule() {
        return hochschule;
    }

    public void setHochschule(String hochschule) {
        this.hochschule = hochschule;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public LocalDate getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(LocalDate gebDatum) {
        this.gebDatum = gebDatum;
    }

    public String getKenntnisse() {
        return kenntnisse;
    }

    public void setKenntnisse(String kenntnisse) {
        this.kenntnisse = kenntnisse;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }
}
