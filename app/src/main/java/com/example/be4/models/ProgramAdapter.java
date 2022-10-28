package com.example.be4.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.be4.MainStoreOwner;
import com.example.be4.R;
import com.example.be4.UpdateItemPage;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> itemName;
    ArrayList<String> itemCount;
    ArrayList<String> itemId;

    public ProgramAdapter(Context context,ArrayList<String>itemId, ArrayList<String> itemName, ArrayList<String> itemCount) {
        super(context, R.layout.single_card, R.id.itemNameCard, itemName);
        this.context = context;
        this.itemId = itemId;
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

        holder.itemName.setText(itemName.get(position));
//        System.out.println(itemCount.get(position));
        holder.itemCount.setText(itemCount.get(position));

        ProgramViewHolder finalHolder = holder;
        final int[] val = {Integer.parseInt(itemCount.get(position))};
//        holder.btnMinus.setOnClickListener(
//                view -> {
//                    if(val[0]>0){
//                        val[0] = --val[0];
//                        itemCount.set(position, String.valueOf(val[0]));
//                        finalHolder.itemCount.setText(String.valueOf(val[0]));
//                        Toast.makeText(context, "minus here "+ val[0], Toast.LENGTH_SHORT).show();
//                    }else {
//                        val[0] = 0;
//                        itemCount.set(position, "0");
//                        Toast.makeText(context, "Cannot be item value "+ val[0], Toast.LENGTH_SHORT).show();
//                    }
//                  }
//        );
        holder.updateBtnOwner.setOnClickListener(
                view -> {
                    Intent intent = new Intent(getContext(), UpdateItemPage.class);
                    intent.putExtra("id",itemId.get(position));
                    intent.putExtra("itemName", itemName.get(position));
                    intent.putExtra("count", itemCount.get(position));
                    context.startActivity(intent);
                }
        );

        return singleItem;
    }

    public void notifyDataSetChanged(MainStoreOwner mainStoreOwner, ArrayList<String> itemNames, ArrayList<String> itemCounts) {
    }
}
