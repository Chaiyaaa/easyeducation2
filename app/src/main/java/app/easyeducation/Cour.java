package app.easyeducation;

public class Cour {

    public String nom;
    public String date;
    public String lien;
    public String module;

    public Cour(String nom, String date, String lien,String module) {
        this.nom = nom;
        this.date = date;
        this.lien = lien;
        this.module= module;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Cour{" +
                "nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", lien='" + lien + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}
