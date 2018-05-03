package com.example.cm.mywork.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cm.mywork.Models.UserData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    LinearLayout upper,lower;
    Button signup,signin;
    EditText name , pass;
    DatabaseReference databaseReference;
    String n , p;
    SharedPreferences.Editor editor ;
    SharedPreferences  get ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        upperAnim();
        lowerAnim();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Signup.class));
                finish();

            }
        });
    }

    private void init() {

        upper = findViewById(R.id.login_upper);
        lower = findViewById(R.id.login_lower);
        signup = findViewById(R.id.loginActivity_singup_btn);
        signin = findViewById(R.id.login_signin);
        name = findViewById(R.id.login_name);
        pass = findViewById(R.id.login_password);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        get = getSharedPreferences("mydata", MODE_PRIVATE);
        editor = get.edit();

        name.setText(get.getString("name",""));
        pass.setText(get.getString("password",""));
    }

    private void upperAnim()
    {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.upper);
        upper.setAnimation(animation);
    }

    private void lowerAnim()
    {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lower);
        lower.setAnimation(animation);
    }

    int flag = 0;
     UserData userData;

    public void SignIN(View view) {
        n = name.getText().toString().trim();
        p = pass.getText().toString().trim();

        if(n.length() == 0 || p.length() == 0)
        {
            Toast.makeText(this, "Enter The Data", Toast.LENGTH_LONG).show();
        }else
        {



            Query query = databaseReference.child("Users_Data").orderByChild("name").equalTo(n);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot d : dataSnapshot.getChildren())
                    {
                         userData = d.getValue(UserData.class);
                        if(userData.getPassword().equals(p))
                        {
                            flag = 1;
                        }
                    }
                    if(flag == 0)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect Data ", Toast.LENGTH_LONG).show();
                    }else
                    {
                        editor.putString("name",n);
                        editor.putString("password",p);
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this,Home.class);
                        intent.putExtra("name",userData.getName());
                        intent.putExtra("job",userData.getJob());
                        intent.putExtra("phone",userData.getPhone());
                        intent.putExtra("company",userData.getCompany());
                        intent.putExtra("image",userData.getImage());
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
