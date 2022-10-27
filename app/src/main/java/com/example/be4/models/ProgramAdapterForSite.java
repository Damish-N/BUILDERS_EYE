package com.example.be4.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.be4.R;

import java.util.ArrayList;

public class ProgramAdapterForSite extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> itemName;
    ArrayList<String> itemCount;
    ArrayList<String> itemId;
    ArrayList<Item> itemArrayList;
    ArrayList<String> itemRemainList;

    public ProgramAdapterForSite(Context context, ArrayList<String> itemId, ArrayList<String> itemName, ArrayList<String> itemCount, ArrayList<Item> itemArrayList, ArrayList<String> itemRemainList) {
        super(context, R.layout.single_card, R.id.itemNameCard, itemName);
        this.context = context;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemArrayList = itemArrayList;
        this.itemRemainList = itemRemainList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolderSupervisor holder = null;

        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            singleItem = layoutInflater.inflate(R.layout.single_card_site_items, parent, false);
            holder = new ProgramViewHolderSupervisor(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (ProgramViewHolderSupervisor) singleItem.getTag();
        }

        holder.itemName.setText(itemName.get(position));
        System.out.println("position :" + position);
        holder.itemCount.setText("Selected :"+itemCount.get(position) + "AI: " + itemRemainList.get(position));

        ProgramViewHolderSupervisor finalHolder = holder;
        final int[] val = {Integer.parseInt(itemCount.get(position))};
        final int[] remain = {Integer.parseInt(itemRemainList.get(position))};
        holder.minusBtn.setOnClickListener(
                view -> {
//                    if(val[0]>0){
//                        val[0] = --val[0];
//                        itemCount.set(position, String.valueOf(val[0]));
//                        finalHolder.itemCount.setText(String.valueOf(val[0]));
//                        Toast.makeText(context, "minus here "+ val[0], Toast.LENGTH_SHORT).show();
//                    }else {
//                        val[0] = 0;
//                        itemCount.set(position, "0");
                    Toast.makeText(context, "Cannot be item value ", Toast.LENGTH_SHORT).show();
//                    }
                }
        );
        holder.plusBtn.setOnClickListener(
                view -> {
                    int p = position;
                    if (remain[0] > 0) {
                        --remain[0];
                        System.out.println(itemArrayList.get(p).count);
                        ++val[0];
                        finalHolder.itemCount.setText("Selected :"+val[0] + "  AI:  " + remain[0]);
                        itemCount.set(p, String.valueOf(val[0]));
                    }
//                    Intent intent = new Intent(getContext(), UpdateItemPage.class);
//                    intent.putExtra("id",itemId.get(position));
//                    intent.putExtra("itemName", itemName.get(position));
//                    intent.putExtra("count", itemCount.get(position));
//                    context.startActivity(intent);
                    Toast.makeText(context, "Cannot be item value ", Toast.LENGTH_SHORT).show();

                }
        );

        return singleItem;
    }

}
