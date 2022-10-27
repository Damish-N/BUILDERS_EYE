package com.example.be4.models;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.be4.R;

public class ProgramViewHolderSupervisor {
    TextView itemName;
    TextView itemCount;
    Button plusBtn;
    Button minusBtn;

    ProgramViewHolderSupervisor(View v){
        itemName = v.findViewById(R.id.itemNameCardSup);
        itemCount = v.findViewById(R.id.itemCountCardSup);
        plusBtn = v.findViewById(R.id.plusBtn);
        minusBtn = v.findViewById(R.id.minusBtn);
    }
}
