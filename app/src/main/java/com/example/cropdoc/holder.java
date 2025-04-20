package com.example.cropdoc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class holder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public ImageView imageView;
    public TextView textView;



    public holder(@NonNull View itemView) {
        super(itemView);
        cardView=(CardView) itemView.findViewById(R.id.cv);
        imageView=(ImageView) itemView.findViewById(R.id.cardViewImage);
        textView=(TextView) itemView.findViewById(R.id.cardViewText);
    }
}
