package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayDevoirAnswers extends AppCompatActivity {
    DatabaseReference database;
    ListView listofAnswers;
    List<DevoirAnswer> answers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_devoir_answers);


        listofAnswers =findViewById(R.id.listofdevoirsanswers);

        setListAdapter();
        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("DevoirAnswers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap : snapshot.getChildren())
                {
                    DevoirAnswer answer=snap.getValue(DevoirAnswer.class);
                    answers.add(answer);
                    setListAdapter();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listofAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),CheckDevoirAnswer.class);
                intent.putExtra("ElevenameD",answers.get(i).getNom());
                intent.putExtra("ElevePrenomD",answers.get(i).getPrenom());
                intent.putExtra("TestNameD",answers.get(i).getNomdevoir());
                intent.putExtra("LinkD",answers.get(i).getLien());
                startActivity(intent);
            }
        });

    }

    private void setListAdapter() {
        DevoirAnswersAdapter adapter=new DevoirAnswersAdapter(getApplicationContext(),answers);
        listofAnswers.setAdapter(adapter);
    }
}