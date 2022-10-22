package com.example.be4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] quantity;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] quantity){
        super(context, R.layout.activity_main_store, itemname);

        this.context= context;
        this.itemname= itemname;
        this.quantity = quantity;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.activity_main_store, null,true);
//        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemname);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.number);
//
//        txtTitle.setText(itemname[position]);
//        imageView.setImageResource(imgid[position]);
        return rowView;

    };
}
