package com.example.cropdoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class description extends AppCompatActivity {
TextView details;
TextView disease_name;
CardView back;
String  treatmentString;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        disease_name=findViewById(R.id.textView6);
        details=findViewById(R.id.tv_disease_description);
        back=findViewById(R.id.details_back);

        details.setText(getIntent().getStringExtra("d"));
        disease_name.setText(getIntent().getStringExtra("disease"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}