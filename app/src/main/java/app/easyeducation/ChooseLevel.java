package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;

public class ChooseLevel extends AppCompatActivity {

    FrameLayout L1,L2,L3,M1,M2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(ChooseLevel.this);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        initWidget();

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("niveau","L1");
                editor.apply();
                OpenFragmentHolder();
            }
        });


        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("niveau","L2");
                editor.apply();
                OpenFragmentHolder();
            }
        });

        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("niveau","L3");
                editor.apply();
                OpenFragmentHolder();
            }
        });

        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("niveau","M1");
                editor.apply();
                OpenFragmentHolder();
            }
        });

        M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("niveau","M2");
                editor.apply();
                OpenFragmentHolder();
            }
        });


    }

    private void OpenFragmentHolder() {
        Intent intent=new Intent(getApplicationContext(),FragmentsHolder.class);
        startActivity(intent);
        finish();
    }

    private void initWidget() {
        L1=findViewById(R.id.L1);
        L2=findViewById(R.id.L2);
        L3=findViewById(R.id.L3);
        M1=findViewById(R.id.M1);
        M2=findViewById(R.id.M2);
    }
}