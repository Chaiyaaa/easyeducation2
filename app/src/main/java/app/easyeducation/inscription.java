package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class inscription extends AppCompatActivity {
 private TextView signin;
 private EditText nom;
 private EditText prenom;
 private EditText email;
 private EditText password;
 private RadioButton var_prof;
 private RadioButton var_etudiant;
 private int variable;
 private Button signup;
    String Sname;
    String Semail;
    String Spassword;
    int type;


    private FirebaseDatabase db=FirebaseDatabase.getInstance();
 private DatabaseReference root=db.getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        initWidget();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                this is a test

                Cour cour=new Cour("algo1","23:22","gsojghsipuoghjw","algo");
                DatabaseReference cours = db.getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Cours");
                cours.push().setValue(cour);*/
                Intent connexion = new Intent(getApplicationContext(), connexion.class);
                startActivity(connexion);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if clicked variable==1
                if (var_etudiant.isChecked()) type=1;
                else if (var_prof.isChecked()) type=2;


        //checking if Editetxts are empty

                if (nom.getText().toString().isEmpty())
                {
                     nom.setError(""); //define your error msg
                }
                else if (prenom.getText().toString().isEmpty())
                {
                    prenom.setError("");
                }
                else if (email.getText().toString().isEmpty())
                {
                    email.setError("");
                }
                else if (password.getText().toString().isEmpty())
                {
                password.setError("");
                }
                else
                {
                    CreateNewAccount();
                }

            }
        });


    }






    private void initWidget() {
        signin=(TextView) findViewById(R.id.signin);
        nom= findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email= findViewById(R.id.mail);
        password= findViewById(R.id.password);
        var_etudiant = findViewById(R.id.var_etudiant);
        signup=findViewById(R.id.signup);
        var_prof=findViewById(R.id.var_prof);
    }


    private void CreateNewAccount() {
        //new object user
        User newUser=new User(nom.getText().toString(),prenom.getText().toString(),password.getText().toString(),email.getText().toString(),type);
        //push data saved to firebase
        root.push().setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();
                Intent connexion = new Intent(getApplicationContext(), connexion.class);
                startActivity(connexion);
            }
        });
    }


}
