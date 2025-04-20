package com.example.cropdoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rv_disease_adapter extends RecyclerView.Adapter<disease_holder> {
    private String disease_name[];
    private String dbase_string;
    private String crop_name;
    private Context context;
    private  FirebaseDatabase dbase;
    private DatabaseReference myRef;


    public rv_disease_adapter(String crop_name,String[] disease_name,String dbase_string, Context context) {
        this.disease_name = disease_name;
        this.dbase_string = dbase_string;
        this.context = context;
        this.crop_name=crop_name;

        dbase= FirebaseDatabase.getInstance();
        myRef = dbase.getReference();
    }

    @NonNull
    @Override
    public disease_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.card_item_1,parent,false);
        return new disease_holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull disease_holder holder, @SuppressLint("RecyclerView") int position) {

        holder.c_tv.setText(disease_name[position].toString());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {

                if (!CheckNetwork.isInternetAvailable(context)) {
                    Snackbar snackbar=Snackbar.make(rv_disease_name.myCl,"No internet connection",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {

                Intent intent;
                intent=new Intent(context,description.class);
                final String[] det = new String[1];

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        det[0] = snapshot.child(dbase_string).child(crop_name).child(disease_name[position].toString()).getValue().toString();
                        intent.putExtra("d",  det[0]);
                        intent.putExtra("disease",disease_name[position].toString());
                        context.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        det[0] = "database error";
                        intent.putExtra("d", det[0]);
                        intent.putExtra("disease",disease_name[position].toString());
                        context.startActivity(intent);
                    }
                });
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return disease_name.length;
    }
}
class disease_holder extends RecyclerView.ViewHolder{
    TextView c_tv;
    CardView cv;
    public disease_holder(@NonNull View itemView) {
        super(itemView);
        cv=itemView.findViewById(R.id.cv1);
        c_tv=itemView.findViewById(R.id.cardViewText1);
    }
}
