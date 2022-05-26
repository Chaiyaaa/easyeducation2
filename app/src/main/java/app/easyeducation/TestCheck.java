package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCheck extends AppCompatActivity {
    TextView testname;
    Button download,reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_check);


        initWidget();


        Bundle extras=getIntent().getExtras();
        String name = extras.getString("Testname");
        String date = extras.getString("Testdate");
        String link = extras.getString("TestLink");
        String module = extras.getString("TestModule");

        testname.setText(name);




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(link),"application/pdf");
                startActivity(intent);

            }
        });


        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestCheck.this,AddTestAnswer.class);

                intent.putExtra("Testname",name);
                intent.putExtra("Testdate",date);
                intent.putExtra("Testmodule",module);

                startActivity(intent);
            }
        });




    }

    private void initWidget() {

        testname =findViewById(R.id.testnamecheck);
        download=findViewById(R.id.downloadtest);
        reply=findViewById(R.id.replytest);
    }
    }
