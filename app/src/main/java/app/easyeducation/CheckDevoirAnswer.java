package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckDevoirAnswer extends AppCompatActivity {
    TextView devoir,elevenom;
    Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_devoir_answer);


        initWidget();

        Bundle extras=getIntent().getExtras();
        String elevename =extras.getString("ElevenameD");
        String prename =extras.getString("ElevePrenomD");
        String TestName =extras.getString("TestNameD");
        String Link =extras.getString("LinkD");


        devoir.setText(TestName);
        elevenom.setText(elevename+" "+prename);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(Link),"application/pdf");
                startActivity(intent);
            }
        });
    }



    private void initWidget() {

        devoir =findViewById(R.id.answer_devoirname);
        elevenom=findViewById(R.id.answer_completnameD);
        download=findViewById(R.id.downloadtestanswerD);
    }
}