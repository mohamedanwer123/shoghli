package com.example.cm.mywork.Activites;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cm.mywork.Adapters.DatesAdapters;
import com.example.cm.mywork.Models.BillData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Client_Info extends AppCompatActivity {

    TextView job , name , phone , call , location ;
    ListView listView;
    FloatingActionButton add;
    String n , j , ph , l ;
    DatesAdapters datesAdapters;
    ArrayList<String> arrayList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__info);
        init();
        putData();
        Bills();

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ph));
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Client_Info.this,AddBills.class);
                intent.putExtra("phone",ph);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Client_Info.this,ShowBills.class);
                String s[]= adapterView.getItemAtPosition(i).toString().split(":");
                intent.putExtra("date",s[1].trim());
                intent.putExtra("phone",ph);
                startActivity(intent);
            }
        });
    }

    private void init() {

        job = findViewById(R.id.clientInfo_job);
        name = findViewById(R.id.clientInfo_name);
        phone = findViewById(R.id.clientInfo_phone);
        call  = findViewById(R.id.clientInfo_call);
        location = findViewById(R.id.clientInfo_location);
        listView = findViewById(R.id.clientInfo_lv);
        add = findViewById(R.id.clientInfo_add);
        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public  void  putData()
    {
        n = getIntent().getStringExtra("name");
        j = getIntent().getStringExtra("job");
        ph = getIntent().getStringExtra("phone");
        l = getIntent().getStringExtra("location");

        name.setText(n);
        job.setText(j);
        phone.setText(ph);
        location.setText(l);
    }

    public  void Bills()
    {

        databaseReference.child(ph+"_Bills").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    if(!arrayList.contains("Date : "+d.getValue(BillData.class).getDate()))
                    {
                        arrayList.add("Date : "+d.getValue(BillData.class).getDate());
                    }

                }

                ArrayList<String> stringArrayList = new ArrayList<>();
                for (int i = arrayList.size()- 1; i >=0 ; i--) {
                    stringArrayList.add(arrayList.get(i));
                }

                datesAdapters = new DatesAdapters(Client_Info.this,R.layout.date_list, stringArrayList);
                listView.setAdapter(datesAdapters);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
