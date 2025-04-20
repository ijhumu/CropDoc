package com.example.cropdoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class rv_adapter4 extends RecyclerView.Adapter<holder> {

private int img1[];
private String txt1[];
private String dbase_string;
Context context;


public rv_adapter4(int[] img1, String[] txt1, Context context) {
    this.img1 = img1;
    this.txt1 = txt1;
    this.context=context;
}

@NonNull
@Override
public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
    View view=layoutInflater.inflate(R.layout.card_item,parent,false);
    return new holder(view);
}

@RequiresApi(api = Build.VERSION_CODES.M)
@Override
public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
    dbase_string="disease_trt";
    holder.cardView.setCardBackgroundColor(context.getColor(R.color.treatment));
    holder.textView.setText(txt1[position]);
    holder.imageView.setImageResource(img1[position]);

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent=new Intent(context,rv_disease_name.class);
            intent.putExtra("corp_heading",txt1[position]);
            intent.putExtra("dbase_string",dbase_string);
            switch (position){
                case 0:
                    intent.putExtra("a",data.disease_name);
                    break;
                case 1:
                    intent.putExtra("a",data.disease_name_1);
                    break;
                case 2:
                    intent.putExtra("a",data.disease_name_2);
                    break;
                case 3:
                    intent.putExtra("a",data.disease_name_3);
                    break;
                default:
                    intent.putExtra("a",data.disease_name);
                    break;
            }

            context.startActivity(intent);

        }
    });
}

@Override
public int getItemCount() {
    return txt1.length;
}
}
