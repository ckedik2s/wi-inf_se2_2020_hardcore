package org.HardCore.model.objects.dto;

import java.time.LocalDate;

public class Student extends User{
    private String anrede ="Herr";
    private String vorname ="Vorname eingeben";
    private String name = "Nachname eingeben";
    private String hochschule ="Hochschule eingeben";
    private Integer semester = 0;
    private LocalDate gebDatum = LocalDate.now();
    private String kenntnisse ="Kenntnisse eingeben";
    private String studiengang ="Studiengang eingeben";

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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
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
