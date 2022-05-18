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
import com.google.firebase.database.FirebaseDatabase;

public class inscription extends AppCompatActivity {
 private TextView signin;
 private EditText username;
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

 private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        this.signin=(TextView) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connexion = new Intent(getApplicationContext(), connexion.class);
                startActivity(connexion);

            }
        });
        this.username= findViewById(R.id.username);
        this.email= findViewById(R.id.mail);
        this.password= findViewById(R.id.password);
        this.var_etudiant = findViewById(R.id.var_etudiant);
        this.signup=findViewById(R.id.signup);
        //if clicked variable==0 (add this to database as parameter in table both student and prof)
        //nice one <3

        
        this.var_prof=findViewById(R.id.var_prof);
        //if clicked variable==1
        Sname=username.toString();
        Semail= email.toString();
        Spassword=password.toString();
        if (var_etudiant.isChecked()) type=1;
        else if (var_prof.isChecked()) type=2;


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int passion=0;
                if (var_etudiant.isChecked())
                {
                        passion=0;
                }else if (var_prof.isChecked())
                {
                    passion=1;
                }

                if (Sname.isEmpty() || Semail.isEmpty() || Spassword.isEmpty() ) //add passsion here pls dont forget
                {
                    username.setError("");
                    email.setError("");//add your error msg
                    password.setError("");

                }
            }
        });

        //init for firebase
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() !=null)
        {
            finish();
            return;
        }



        mAuth.createUserWithEmailAndPassword(Semail, Spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                User newUser=new User(Sname,Spassword,Semail,type);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    //show main activity

                                    Toast.makeText(inscription.this,"Successfully added ...",Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {

                            Toast.makeText(inscription.this,"connexion failed ...",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
