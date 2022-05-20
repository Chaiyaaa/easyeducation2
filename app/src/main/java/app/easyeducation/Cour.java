package app.easyeducation;

public class Cour {

    public String nom;
    public String date;
    public String lien;

    public Cour(String nom, String date, String lien) {
        this.nom = nom;
        this.date = date;
        this.lien = lien;
    }

    public Cour() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @Override
    public String toString() {
        return "Cour{" +
                "nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", lien='" + lien + '\'' +
                '}';
    }
}
