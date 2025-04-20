package com.example.cropdoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

    public class rv_adapter extends RecyclerView.Adapter<holder> {
    private int img1[];
    private String txt1[];
    Context context;


    public rv_adapter(int[] img1, String[] txt1,Context context) {
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
        holder.cardView.setCardBackgroundColor(context.getColor(R.color.home));
        holder.textView.setText(txt1[position]);
        holder.imageView.setImageResource(img1[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.cropModel=txt1[position];
                bottom_sheet bottom_sheet=new bottom_sheet();
                bottom_sheet.show(MainActivity.fm, bottom_sheet.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return txt1.length;
    }
}
