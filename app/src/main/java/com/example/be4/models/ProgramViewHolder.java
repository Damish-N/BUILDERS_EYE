package com.example.be4.models;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.be4.R;

public class ProgramViewHolder {
    TextView itemName;
    TextView itemCount;
    Button btnPlus;
    Button btnMinus;

    ProgramViewHolder(View v){
        itemName = v.findViewById(R.id.itemNameCard);
        itemCount = v.findViewById(R.id.itemCountCard);
        btnPlus = v.findViewById(R.id.plusBtn);
        btnMinus = v.findViewById(R.id.minusBtn);
    }

}
