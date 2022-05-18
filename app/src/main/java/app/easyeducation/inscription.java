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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
 private DatabaseReference root=db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        this.signin=(TextView) findViewById(R.id.signin);





        nom= findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email= findViewById(R.id.mail);
        password= findViewById(R.id.password);
        var_etudiant = findViewById(R.id.var_etudiant);
        signup=findViewById(R.id.signup);
        //if clicked variable==0 (add this to database as parameter in table both student and prof)
        //nice one <3

        
       var_prof=findViewById(R.id.var_prof);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connexion = new Intent(getApplicationContext(), connexion.class);
                startActivity(connexion);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if clicked variable==1
                Sname=nom.toString();
                Semail= email.toString();
                Spassword=password.toString();
                if (var_etudiant.isChecked()) type=1;
                else if (var_prof.isChecked()) type=2;

                int passion=0;
                if (var_etudiant.isChecked())
                {
                        passion=0;
                }else if (var_prof.isChecked())
                {
                    passion=1;
                }

if (nom.getText().toString().isEmpty())
{
                    nom.setError(""); //define your error msg
}
if (prenom.getText().toString().isEmpty())
{
                     prenom.setError("");
}
if (email.getText().toString().isEmpty())
{
    email.setError("");
}
if (password.getText().toString().isEmpty())
{
password.setError("");
}
                User newUser=new User(nom.getText().toString(),prenom.getText().toString(),password.getText().toString(),email.getText().toString(),passion);
                root.push().setValue(newUser);





            }
        });






    }


}
