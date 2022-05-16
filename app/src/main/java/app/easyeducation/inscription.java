package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class inscription extends AppCompatActivity {
 private TextView signin;
 private EditText username;
 private EditText email;
 private EditText password;
 private RadioButton var_prof;
 private RadioButton var_etudiant;
 private int variable;
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
        //if clicked variable==0 (add this to database as parameter in table both student and prof)
        this.var_prof=findViewById(R.id.var_prof);
        //if clicked variable==1
    }
}
