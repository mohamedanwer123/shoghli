package com.example.cm.mywork.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cm.mywork.Models.UserData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateUserData extends AppCompatActivity {

    CircleImageView img;
    EditText name , phone , company , job;
    Button update;
    ImageView back;
    String n,j,ph,c,i;
    DatabaseReference databaseReference;
    UserData userData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_data);
        init();
        showData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UpdateUserData.this,Home.class);
                intent.putExtra("name",n);
                intent.putExtra("job",j);
                intent.putExtra("phone",ph);
                intent.putExtra("company",c);
                intent.putExtra("image",i);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init()
    {
        img = findViewById(R.id.updateActivity_img);
        name = findViewById(R.id.updateActivity_name);
        phone = findViewById(R.id.updateActivity_phone);
        job = findViewById(R.id.updateActivity_job);
        company = findViewById(R.id.updateActivity_company);
        update = findViewById(R.id.updateActivity_btn);
        back = findViewById(R.id.updateActivity_back);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        n = getIntent().getStringExtra("name");
        j = getIntent().getStringExtra("job");
        ph =  getIntent().getStringExtra("phone");
        c = getIntent().getStringExtra("company");
        i = getIntent().getStringExtra("image");

    }

    public void showData()
    {
        name.setText(n);
        job.setText(j);
        phone.setText(ph);
        company.setText(c);

        if(i.length() ==0 || i.equals("") || i=="")
        {
            img.setImageResource(R.drawable.pro);
        }else
        {
            Picasso.get().load(Uri.parse(i)).into(img);
        }

    }

    public void Update()
    {
        if(n.length() == 0 || j.length() == 0 || c.length() == 0 ||
                ph.length() == 0 ||  ph.length() !=11 || ph.charAt(0) != '0' ||  ph.charAt(1) != '1' ){

            Toast.makeText(this, "Enter The Data / Correct Data", Toast.LENGTH_LONG).show();
        }else
        {
            userData = new UserData();
            Query query = databaseReference.child("Users_Data").orderByChild("phone").equalTo(ph);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot d : dataSnapshot.getChildren())
                    {
                        userData = d.getValue(UserData.class);
                        userData.setName(name.getText().toString().trim());
                        userData.setJob(job.getText().toString().trim());
                        userData.setCompany(company.getText().toString().trim());
                        userData.setPhone(phone.getText().toString().trim());
                        d.getRef().setValue(userData);
                        Toast.makeText(UpdateUserData.this, "Data Updated", Toast.LENGTH_LONG).show();
                        n = userData.getName();
                        j = userData.getJob();
                        ph = userData.getPhone();
                        i = userData.getImage();
                        c = userData.getCompany();
                    }





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {

    }
}
