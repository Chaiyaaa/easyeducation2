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

}
