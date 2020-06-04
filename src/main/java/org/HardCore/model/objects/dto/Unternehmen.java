package org.HardCore.model.objects.dto;

public class Unternehmen extends User{
    private String firmenname = "Test GmbH";
    private String ansprechpartner ="Paddy Cheese";
    private String strasse ="Teststrasse";
    private String plz = "53721";
    private String haus_nr = "1";
    private String zusatz = "b";
    private String ort = "Siegburg";
    private String branche ="Informatik";

    public Unternehmen(User user) {
        super(user);
    }


    public String getFirmenname() { return firmenname; }

    public void setFirmenname(String name) {
        this.firmenname = name;
    }

    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getHaus_nr() {
        return haus_nr;
    }

    public void setHaus_nr(String haus_nr) {
        this.haus_nr = haus_nr;
    }

    public String getZusatz() {
        return zusatz;
    }

    public void setZusatz(String zusatz) {
        this.zusatz = zusatz;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
