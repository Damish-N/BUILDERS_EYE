package com.example.be4.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.be4.R;
import com.example.be4.SupervisorProjects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProgramAdapterForSite extends ArrayAdapter<String> {
    Context context;
    String siteId;
    ArrayList<String> itemName;
    ArrayList<String> itemCount;
    ArrayList<String> itemId;
    ArrayList<Item> itemArrayList;
    ArrayList<String> itemRemainList;
    Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProgramAdapterForSite(Context context, String id, ArrayList<String> itemId, ArrayList<String> itemName, ArrayList<String> itemCount, ArrayList<Item> itemArrayList, ArrayList<String> itemRemainList, Button updateBtnSupProjects) {
        super(context, R.layout.single_card, R.id.itemNameCard, itemName);
        this.context = context;
        this.siteId = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemArrayList = itemArrayList;
        this.itemRemainList = itemRemainList;
        this.btn = updateBtnSupProjects;
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
        holder.itemCount.setText("Selected :" + itemCount.get(position) + "AI: " + itemRemainList.get(position));


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
                        finalHolder.itemCount.setText("Selected :" + val[0] + "  AI:  " + remain[0]);
                        itemCount.set(p, String.valueOf(val[0]));
                        itemRemainList.set(p, String.valueOf(remain[0]));
                    }
//                    Intent intent = new Intent(getContext(), UpdateItemPage.class);
//                    intent.putExtra("id",itemId.get(position));
//                    intent.putExtra("itemName", itemName.get(position));
//                    intent.putExtra("count", itemCount.get(position));
//                    context.startActivity(intent);
                    Toast.makeText(context, "Cannot be item value ", Toast.LENGTH_SHORT).show();

                }
        );

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemName.size() > 0) {
                            ArrayList<Item> items = new ArrayList<>();
                            for (int i = 0; i < itemName.size(); i++) {
                                items.add(new Item(itemId.get(i), itemName.get(i), Integer.valueOf(itemCount.get(i))));
                            }
                            db.collection("sites")
                                    .document(siteId)
                                    .update("siteItems", items)
                                    .addOnCompleteListener(
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(context, "Updated List", Toast.LENGTH_SHORT).show();
                                                    updateTheItemsOnQuery(items);
                                                }
                                            }
                                    ).addOnFailureListener(
                                            new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    );

                        }
                    }
                }
        );

        return singleItem;
    }

    private void updateTheItemsOnQuery(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            int finalI = i;
            db.collection("items")
                    .document(items.get(i).getId())
                    .update("count", Integer.valueOf(itemRemainList.get(i)))
                    .addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "updated : " + items.get(finalI).getId(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
        }

        Intent intent = new Intent(context, SupervisorProjects.class);
        context.startActivity(intent);

    }

}
