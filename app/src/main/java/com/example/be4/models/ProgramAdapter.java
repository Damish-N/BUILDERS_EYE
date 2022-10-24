package com.example.be4.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.be4.MainStoreOwner;
import com.example.be4.R;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    String[] itemName;
    String[] itemCount;

    public ProgramAdapter(Context context, String[] itemName, String[] itemCount) {
        super(context, R.layout.single_card, R.id.itemNameCard, itemName);
        this.context = context;
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder holder = null;

        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            singleItem = layoutInflater.inflate(R.layout.single_card, parent, false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (ProgramViewHolder) singleItem.getTag();
        }

        holder.itemName.setText(itemName[position]);
        System.out.println(itemCount[position]);
        holder.itemCount.setText(itemCount[position]);

        ProgramViewHolder finalHolder = holder;
        final int[] val = {Integer.parseInt(itemCount[position])};
        holder.btnMinus.setOnClickListener(
                view -> {
                    if(val[0]>0){
                        val[0] = --val[0];
                        itemCount[position] = String.valueOf(val[0]);
                        finalHolder.itemCount.setText(String.valueOf(val[0]));
                        Toast.makeText(context, "minus here "+ val[0], Toast.LENGTH_SHORT).show();
                    }else {
                        val[0] = 0;
                        itemCount[position] = "0";
                        Toast.makeText(context, "Cannot be item value "+ val[0], Toast.LENGTH_SHORT).show();
                    }
                  }
        );
        holder.btnPlus.setOnClickListener(
                view -> {
                    if(val[0]>=0){
                        val[0] = ++val[0];
                        itemCount[position] = String.valueOf(val[0]);
                        finalHolder.itemCount.setText(String.valueOf(val[0]));
                        Toast.makeText(context, "minus here "+ val[0], Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "minus here "+ val[0], Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return singleItem;
    }
}
