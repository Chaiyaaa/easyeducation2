package app.easyeducation;

public class User {


    public String nom;
    public String prenom;
    public String password;
    public String email;
    public int type;

    public User(String nom,String prenom,String password,String email,int type)
    {


        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
        this.email=email;
        this.type=type;
    }


    //i always make one empty for errors
    User()
    {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
