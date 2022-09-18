package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtProgress;
    ProgressBar pbEjemplo;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbEjemplo = findViewById(R.id.pbEjemplo);
        btnAdd = findViewById(R.id.btnAdd);
        txtProgress = findViewById(R.id.txtProgress);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(pbEjemplo.getProgress() == 100){
                        pbEjemplo.setProgress(0);
                    } else {
                        pbEjemplo.incrementProgressBy(10);
                    }
                    txtProgress.setText(pbEjemplo.getProgress() + "%");
            }
        });

    }
}