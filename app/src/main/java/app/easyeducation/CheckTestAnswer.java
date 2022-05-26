package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckTestAnswer extends AppCompatActivity {
 TextView test,elevenom;
 Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_test_answer);
        initWidget();

        Bundle extras=getIntent().getExtras();
        String elevename =extras.getString("Elevename");
        String prename =extras.getString("ElevePrenom");
        String TestName =extras.getString("TestName");
        String Link =extras.getString("Link");


        test.setText(TestName);
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

        test=findViewById(R.id.answer_testname);
        elevenom=findViewById(R.id.answer_completname);
        download=findViewById(R.id.downloadtestanswer);
    }
}