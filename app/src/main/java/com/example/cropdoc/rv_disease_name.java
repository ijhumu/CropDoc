package com.example.cropdoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class rv_disease_name extends AppCompatActivity {
TextView crop_name;
RecyclerView rv_crop_name;
String[] value;
String dbase_String;

static ConstraintLayout myCl;

CardView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_crop_name);

        crop_name =findViewById(R.id.tv_crop_name);
        back=findViewById(R.id.cardView5);

        Intent intent = getIntent();
        value=intent.getStringArrayExtra("a");

        dbase_String=intent.getStringExtra("dbase_string");
        crop_name.setText(intent.getStringExtra("corp_heading"));
        rv_crop_name =(RecyclerView) findViewById(R.id.rv_desease_name);
        rv_crop_name.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myCl=findViewById(R.id.d_name_cl);

        rv_crop_name.setAdapter(new rv_disease_adapter(intent.getStringExtra("corp_heading"),value,dbase_String,rv_disease_name.this));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}