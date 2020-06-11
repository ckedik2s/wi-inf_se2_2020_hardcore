package org.HardCore.model.objects.entities;


import java.sql.Date;

public class Stellenanzeige {
    private int id;
    private int id_anzeige;
    private String beschreibung;
    private String art;
    private String name;
    private Date zeitraum;
    private String branche;
    private String studiengang;

    public int getId_anzeige() {
        return id_anzeige;
    }

    public void setId_anzeige(int id_anzeige) {
        this.id_anzeige = id_anzeige;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public Date getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(Date zeitraum) {
        this.zeitraum = zeitraum;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }
}
