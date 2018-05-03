package com.example.cm.mywork.Activites;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.mywork.Models.UserData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText name , pass , phone ,job , company;
    TextView title;
    ImageView back ;
    Button save;
    ProgressBar progressBar;

    String n , p , ph , j , c ;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setAnimation();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,MainActivity.class));
                finish();
            }
        });



    }


    public void init()
    {
        name = findViewById(R.id.signup_name);
        pass = findViewById(R.id.signup_pass);
        phone = findViewById(R.id.signup_phone);
        job = findViewById(R.id.signup_job);
        company = findViewById(R.id.signup_company);
        title = findViewById(R.id.signup_title);
        back = findViewById(R.id.signup_back);
        save = findViewById(R.id.signup_save);
        progressBar = findViewById(R.id.signup_progress);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void setAnimation()
    {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.signup);
        name.setAnimation(animation);
        pass.setAnimation(animation);
        phone.setAnimation(animation);
        job.setAnimation(animation);
        company.setAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.rotate);
       title.setAnimation(animation2);
        back.setAnimation(animation2);
        save.setAnimation(animation2);
    }

    public void Save(View view) {

        n= name.getText().toString().trim();
        p = pass.getText().toString().trim();
        j = job.getText().toString().trim();
        c = company.getText().toString().trim();
        ph = phone.getText().toString().trim();

        if(n.length() == 0 || p.length() == 0 || ph.length() == 0 ||
                j.length() == 0 || c.length() == 0 ||
                ph.length() !=11 || ph.charAt(0) != '0' ||  ph.charAt(1) != '1' ){

            Toast.makeText(this, "Enter The Data / Correct Data", Toast.LENGTH_LONG).show();
        }else
        {
            UserData userData = new UserData(n,p,ph,j,c,"");
            databaseReference.child("Users_Data").push().setValue(userData);
            progress();
        }
    }

    int x=0;
    public void progress()
    {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (x<=110)
                {
                    x+=10;
                    if(x==110)
                    {
                        Intent intent = new Intent(Signup.this,Home.class);
                        intent.putExtra("name",n);
                       intent.putExtra("job",j);
                        intent.putExtra("phone",ph);
                        intent.putExtra("company",c);
                        intent.putExtra("image","");
                        startActivity(intent);
                        finish();
                    }else
                    {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed() {

    }
}
