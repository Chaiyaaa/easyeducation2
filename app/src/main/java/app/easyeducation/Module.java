package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Module extends AppCompatActivity {
private Button devoirs;
private Button tests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module);
        
        this.devoirs=findViewById(R.id.devoir_intLight);
        devoirs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Devoirs = new Intent( getApplicationContext(), Devoirs.class);
                startActivity(Devoirs);
                finish();
            }
        });
        
        this.tests = findViewById(R.id.test_intLight);
        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test = new Intent(getApplicationContext(), Test.class);
                startActivity(Test);
                finish();
            }
        });
    }
}
