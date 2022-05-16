package app.easyeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Test extends AppCompatActivity {

    private Button module;
    private Button devoirs;
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        this.module = findViewById(R.id.module_intLight);
        module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Module = new Intent(getApplicationContext(),Module.class);
                startActivity(Module);
                finish();
            }
        });

        this.devoirs = findViewById(R.id.devoir_intLight);
        module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Devoirs = new Intent(getApplicationContext(),Module.class);
                startActivity(Devoirs);
                finish();
            }
        });

    }

}