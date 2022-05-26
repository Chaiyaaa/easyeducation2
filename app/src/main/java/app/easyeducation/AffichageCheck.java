package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AffichageCheck extends AppCompatActivity {
    TextView affichagenam;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_check);


        initWidget();


        Bundle extras=getIntent().getExtras();
        String name = extras.getString("Affichagename");
        String date = extras.getString("Affichagedate");
        String link = extras.getString("AffichageLink");
        String module = extras.getString("AffichageModule");

        affichagenam.setText(name);




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(link),"application/pdf");
                startActivity(intent);

            }
        });




    }

    private void initWidget() {

        affichagenam =findViewById(R.id.affichagenamecheck);
        download=findViewById(R.id.downloadaffichage);
    }
    }