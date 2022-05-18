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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sname=username.toString();
                Semail= email.toString();
                Spassword=password.toString();
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

                        } else {

                        }
                    }
                });

    }
}
