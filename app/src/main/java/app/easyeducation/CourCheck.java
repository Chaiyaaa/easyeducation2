package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class CourCheck extends AppCompatActivity {
    TextView courname,modulename;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cour_check);

        initWidget();

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bundle extras=getIntent().getExtras();
        String name = extras.getString("Courname");
        String date = extras.getString("Courdate");
        String link = extras.getString("CourLink");
        String module = extras.getString("CourModule");

        String niveau=sharedPreferences.getString("niveau","");
        courname.setText(name);
        modulename.setText(module);



        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cour cour=new Cour(name,date,link,module,niveau);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(link),"application/pdf");
                startActivity(intent);

            }
        });




    }

    private void initWidget() {

        courname=findViewById(R.id.cournamecheck);
        download=findViewById(R.id.downloadcour);
        modulename=findViewById(R.id.modulename_courcheck);
    }
}