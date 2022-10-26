package com.example.be4.models;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.be4.R;

public class ProgramViewHolder {
    TextView itemName;
    TextView itemCount;
    Button updateBtnOwner;
//    Button btnMinus;

    ProgramViewHolder(View v){
        itemName = v.findViewById(R.id.itemNameCard);
        itemCount = v.findViewById(R.id.itemCountCard);
        updateBtnOwner = v.findViewById(R.id.updateBtnOwner);
//        btnMinus = v.findViewById(R.id.minusBtn);
    }

}
