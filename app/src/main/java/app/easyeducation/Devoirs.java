package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Devoirs extends AppCompatActivity {
private Button tests;
private Button module;
private Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devoirs);

        this.tests = findViewById(R.id.test_intLight);
        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test = new Intent(getApplicationContext(), Test.class);
                startActivity(Test);
                finish();
            }
        });

        this.module = findViewById(R.id.module_intLight);
        module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Module = new Intent(getApplicationContext(), Module.class);
                startActivity(Module);
                finish();
            }
        });
    }
}