package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainStore extends AppCompatActivity {
    private ListView listView;
    private TextView textView;

    String [] text_list = {
            "Steel frames",
            "Escalator",
            "Concrete Mixer",
            "earth Compactor",
            "Steel Bar cutting machine",
            "Concrete Pumps"
    };

    Integer[] numbers = {0,
    0,
    0,
    0,
    0,
    0};



   // String username = getIntent().getStringExtra("keyusername");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_store);

//         listView = (ListView) findViewById(R.id.listview);
//         textView = (TextView) findViewById(R.id.itemname);
//

        //ArrayList<Integer>list = new ArrayList<>();
        //ArrayAdapter adapter = new ArrayAdapter<Integer>(this,R.layout.storelayout,list);

//       CustomListAdapter adapter = new CustomListAdapter(this,text_list,quantity);
//        listView.setAdapter(adapter);

       /* listView.setOnClickListener(new AdpterView.OnItemClickListner(){
            ListView list = (ListView) findViewById(R.id.listview);
           String Selcteditem = text_list



        };*/

        //final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.storelayout,R.id.itemname,text_list);
        //listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Hasindu");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    list.add((Integer) snapshot.getValue());
//                }
//                adapter.notifyDataSetChanged();            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}