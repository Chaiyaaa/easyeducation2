package app.easyeducation;

public class TestAnswer {

    private String nomtest;
    private String datetest;
    private String moduletest;
    private String nom;
    private String prenom;
    private String lien;

    public TestAnswer(String nomtest, String datetest, String moduletest, String nom, String prenom, String lien) {
        this.nomtest = nomtest;
        this.datetest = datetest;
        this.moduletest = moduletest;
        this.nom = nom;
        this.prenom = prenom;
        this.lien = lien;
    }

    public TestAnswer() {
    }

    public String getNomtest() {
        return nomtest;
    }

    public void setNomtest(String nomtest) {
        this.nomtest = nomtest;
    }

    public String getDatetest() {
        return datetest;
    }

    public void setDatetest(String datetest) {
        this.datetest = datetest;
    }

    public String getModuletest() {
        return moduletest;
    }

    public void setModuletest(String moduletest) {
        this.moduletest = moduletest;
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
        return "TestAnswer{" +
                "nomtest='" + nomtest + '\'' +
                ", datetest='" + datetest + '\'' +
                ", moduletest='" + moduletest + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", lien='" + lien + '\'' +
                '}';
    }
}
