package com.example.cm.mywork.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm.mywork.Models.ClientData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClients extends AppCompatActivity {

    String ph;
    EditText name , job , phone , location;
    String n , j , ph2 , l ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);
        init();
    }

    private void init() {

        ph = getIntent().getStringExtra("phone");
        name = findViewById(R.id.addclientactivity_name);
        job = findViewById(R.id.addclientactivity_job);
        phone = findViewById(R.id.addclientactivity_phone);
        location = findViewById(R.id.addclientactivity_location);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void Save(View view) {

        n = name.getText().toString().trim();
        j = job.getText().toString().trim();
        ph2 = phone.getText().toString().trim();
        l = location.getText().toString().trim();

        if(n.length()==0 || job.length()==0 || l.length()==0
                || ph2.length()==0 || ph2.length()!=11 || ph2.charAt(0)!='0' || ph2.charAt(1)!='1')
        {
            Toast.makeText(this, "Enter Data / Correct Data", Toast.LENGTH_LONG).show();
        }else
        {
            ClientData clientData = new ClientData(n,j,l,ph2);
            databaseReference.child(ph+"_Clients").push().setValue(clientData);
            Toast.makeText(this, "Client Added", Toast.LENGTH_LONG).show();
            name.setText("");
            job.setText("");
            phone.setText("");
            location.setText("");
        }
    }
}
