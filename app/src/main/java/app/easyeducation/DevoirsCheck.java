package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DevoirsCheck extends AppCompatActivity {
    TextView devoirname,modulename;
    Button download,reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoirs_check);


        initWidget();


        Bundle extras=getIntent().getExtras();
        String name = extras.getString("Devoirname");
        String date = extras.getString("Devoirdate");
        String link = extras.getString("DevoirLink");
        String module = extras.getString("DevoirModule");

        devoirname.setText(name);
        modulename.setText(module);



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
                Intent intent=new Intent(DevoirsCheck.this,AddDevoirAnswer.class);


                intent.putExtra("Devoirname",name);
                intent.putExtra("Devoirdate",date);
                intent.putExtra("Devoirmodule",module);
                startActivity(intent);
            }
        });




    }

    private void initWidget() {

        devoirname =findViewById(R.id.devoirnamecheck);
        download=findViewById(R.id.downloaddevoir);
        reply=findViewById(R.id.replydevoir);
        modulename=findViewById(R.id.modulename_devoircheck);
    }

    }
