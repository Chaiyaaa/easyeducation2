package app.easyeducation;

public class Affichage {

    public String nom;
    public String date;
    public String lien;
    public String module;
    public String niveau;

    public Affichage(String nom, String date, String lien, String module,String niveau) {
        this.nom = nom;
        this.date = date;
        this.lien = lien;
        this.module = module;
        this.niveau=niveau;
    }

    public Affichage() {
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
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
        return "Affichage{" +
                "nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", lien='" + lien + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}
