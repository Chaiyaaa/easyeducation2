package app.easyeducation;

public class DevoirAnswer {

    private String nomdevoir;
    private String datedvoir;
    private String moduledevoir;
    private String nom;
    private String prenom;
    private String lien;

    public DevoirAnswer(String nomdevoir, String datedvoir, String moduledevoir, String nom, String prenom, String lien) {
        this.nomdevoir = nomdevoir;
        this.datedvoir = datedvoir;
        this.moduledevoir = moduledevoir;
        this.nom = nom;
        this.prenom = prenom;
        this.lien = lien;
    }

    public DevoirAnswer() {
    }

    public String getNomdevoir() {
        return nomdevoir;
    }

    public void setNomdevoir(String nomdevoir) {
        this.nomdevoir = nomdevoir;
    }

    public String getDatedvoir() {
        return datedvoir;
    }

    public void setDatedvoir(String datedvoir) {
        this.datedvoir = datedvoir;
    }

    public String getModuledevoir() {
        return moduledevoir;
    }

    public void setModuledevoir(String moduledevoir) {
        this.moduledevoir = moduledevoir;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }


    @Override
    public String toString() {
        return "DevoirAnswer{" +
                "nomdevoir='" + nomdevoir + '\'' +
                ", datedvoir='" + datedvoir + '\'' +
                ", moduledevoir='" + moduledevoir + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", lien='" + lien + '\'' +
                '}';
    }
}
