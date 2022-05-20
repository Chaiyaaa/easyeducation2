package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class connexion extends AppCompatActivity {
    private TextView signup;
    private Button signin;
    private EditText mail,password;
    String passwordDb="";
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);


        InitWidget(); //link xml ids to variables

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscription = new Intent(getApplicationContext(), inscription.class);
                startActivity(inscription);
            }
        });
        
        
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();
            }
        });

}










    private void InitWidget() {
        signup=(TextView) findViewById(R.id.Signup);
        signin=findViewById(R.id.signin);
        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
    }

    private void isUser() {

        //getting Strings from Editexts

        String gmailEntered =mail.getText().toString();
        String passwordEnterd=password.getText().toString();



        mail.setError(null); //we get rid of the error msg if exist
        password.setError(null);


        //firebase reference
        DatabaseReference reference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/");
        reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snap: snapshot.getChildren())
                {
                    //loop all the ids in the child "users" from firebase

                    String emailDb=snap.child("email").getValue().toString(); //get the email from the database
                    if (emailDb.equals(gmailEntered)) //check for the email
                    {
                        //correct email, we check password
                        if (snap.child("password").getValue().toString().equals(passwordEnterd))
                        {
                            openMainActivity();
                        }
                        else
                        {
                            //correct  email but wrong password
                            password.setError("Wrong password");
                        }

                    }
                    else
                    {
                        //email not found
                        Toast.makeText(connexion.this,"false info",Toast.LENGTH_LONG).show(); //change error msg
                      // mail.setError("Wrong email");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void openMainActivity() {
        Intent intent=new Intent(connexion.this,FragmentsHolder.class);
        startActivity(intent);
        finish();
    }


}
