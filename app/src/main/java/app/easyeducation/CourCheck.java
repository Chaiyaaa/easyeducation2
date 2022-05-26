package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class CourCheck extends AppCompatActivity {
    TextView courname;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cour_check);

        initWidget();


        Bundle extras=getIntent().getExtras();
        String name = extras.getString("Courname");
        String date = extras.getString("Courdate");
        String link = extras.getString("CourLink");
        String module = extras.getString("CourModule");

        courname.setText(name);




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cour cour=new Cour(name,date,link,module);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(link),"application/pdf");
                startActivity(intent);

            }
        });




    }

    private void initWidget() {

        courname=findViewById(R.id.cournamecheck);
        download=findViewById(R.id.downloadcour);
    }
}