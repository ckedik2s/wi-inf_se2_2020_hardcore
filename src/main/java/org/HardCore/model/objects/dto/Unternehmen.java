package org.HardCore.model.objects.dto;

public class Unternehmen extends User{
    private String firmenname = "Firmenname eingeben";
    private String ansprechpartner ="Ansprechpartner eingeben";
    private String strasse ="Straße eingeben";
    private Integer plz = 0;
    private Integer haus_nr = 0;
    private String zusatz = " ";
    private String ort = "Ort eingeben";
    private String branche ="Branche eingeben";

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

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public Integer getHaus_nr() {
        return haus_nr;
    }

    public void setHaus_nr(Integer haus_nr) {
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
