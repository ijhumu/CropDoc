package com.example.cropdoc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class result extends AppCompatActivity {
ImageView image;
TextView disease;
TextView crop;
TextView confidence;
CardView back;

Button treatment;
Button rescan;

Context context;
ConstraintLayout constraintLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        image = findViewById(R.id.imageView2);
        disease = findViewById(R.id.tv_result_disease);
        confidence = findViewById(R.id.tv_result_confidence);

        rescan=findViewById(R.id.button2);
        treatment=findViewById(R.id.button);
        crop=findViewById(R.id.textView3);

        back=findViewById(R.id.cardView2);

        image.setImageBitmap(data.image);
        disease.setText(data.identified_disease);
        crop.setText(data.selected_crop);
        confidence.setText(String.valueOf(data.confidence));

        constraintLayout=findViewById(R.id.result_layout);

        context=result.this;

        if(data.identified_disease.equals("Invalid")){
            treatment.setVisibility(View.INVISIBLE);
        }
        else if(data.identified_disease.equals("Healthy")){
            treatment.setVisibility(View.INVISIBLE);
        }

        rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!CheckNetwork.isInternetAvailable(context)) {
                    Snackbar snackbar=Snackbar.make(constraintLayout,"No internet connection",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {

                    Intent intent = new Intent(context, description.class);
                    String trt[];
                    final String[] res = {null};

                    FirebaseDatabase dbase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = dbase.getReference();


                    switch (data.selected_crop) {
                        case "Corn":
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    res[0] = snapshot.child("disease_trt").child(data.selected_crop).child(data.identified_disease).getValue().toString();
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    res[0] = "database error";
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }
                            });

                            break;

                        case "Potato":
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    res[0] = snapshot.child("disease_trt").child(data.selected_crop).child(data.identified_disease).getValue().toString();
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    res[0] = "database error";
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }
                            });
                            break;

                        case "Rice":
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    res[0] = snapshot.child("disease_trt").child(data.selected_crop).child(data.identified_disease).getValue().toString();
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    res[0] = "database error";
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }
                            });
                            break;

                        case "Wheat":
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    res[0] = snapshot.child("disease_trt").child(data.selected_crop).child(data.identified_disease).getValue().toString();
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    res[0] = "database error";
                                    intent.putExtra("d", res[0]);
                                    intent.putExtra("disease", data.selected_crop);
                                    startActivity(intent);
                                }
                            });
                            break;
                    }


                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    public int findIndex(String input,String[] sol) {
        String[] solution=sol;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i].equalsIgnoreCase(input)) {
                return i;

            }
        }
        return 0;
    }
}




