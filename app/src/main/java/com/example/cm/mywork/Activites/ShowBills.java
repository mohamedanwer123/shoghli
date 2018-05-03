package com.example.cm.mywork.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cm.mywork.Adapters.BillsAdapter;
import com.example.cm.mywork.Models.BillData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowBills extends AppCompatActivity {

    ListView listView;
    String phone , date;
    ArrayList<BillData> arrayList;
    BillsAdapter billsAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);
        init();
        putData();
    }

    public void init()
    {
        listView = findViewById(R.id.showBills_tv);
        phone = getIntent().getStringExtra("phone");
        date = getIntent().getStringExtra("date");
        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void putData()
    {
        Query query = databaseReference.child(phone+"_Bills").orderByChild("date").equalTo(date);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    arrayList.add(d.getValue(BillData.class));
                }

                billsAdapter = new BillsAdapter(ShowBills.this,R.layout.bills_list,arrayList);
                listView.setAdapter(billsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
