package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiplayTestAnswers extends AppCompatActivity {
    DatabaseReference database;
    ListView listofAnswers;
    List<TestAnswer> answers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplay_test_answers);

        listofAnswers =findViewById(R.id.listoftestanswers);

        setListAdapter();
        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("TestAnswers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap : snapshot.getChildren())
                {
                    TestAnswer answer=snap.getValue(TestAnswer.class);
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
                Intent intent=new Intent(getApplicationContext(),CheckTestAnswer.class);
                intent.putExtra("Elevename",answers.get(i).getNom());
                intent.putExtra("ElevePrenom",answers.get(i).getPrenom());
                intent.putExtra("TestName",answers.get(i).getNomtest());
                intent.putExtra("Link",answers.get(i).getLien());
                startActivity(intent);
            }
        });


    }

    private void setListAdapter() {
        TestAnswersAdapter adapter=new TestAnswersAdapter(getApplicationContext(),answers);
        listofAnswers.setAdapter(adapter);
    }
}