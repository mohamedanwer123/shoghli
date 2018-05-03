package com.example.cm.mywork.Activites;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.cm.mywork.Fragments.Clients;
import com.example.cm.mywork.Fragments.Products;
import com.example.cm.mywork.Fragments.Profile;
import com.example.cm.mywork.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Home extends AppCompatActivity {

    FrameLayout frameLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.profile:

                    Fragment profile = new Profile();
                    Profile(profile);
                    loadPage(profile);
                    break;

                case R.id.clients:

                    Clients clients = new Clients();
                    String ph = getIntent().getStringExtra("phone");
                    Bundle bundle = new Bundle();
                    bundle.putString("phone",ph);
                    clients.setArguments(bundle);
                    loadPage(clients);
                    break;

                case R.id.products:

                    Products products = new Products();
                    String ph2 = getIntent().getStringExtra("phone");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("phone",ph2);
                    products.setArguments(bundle2);
                    loadPage(products);
                    break;




            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        init();
    }


    public void init()
    {

        frameLayout = findViewById(R.id.homeActivity_frame);
        Fragment profile = new Profile();
        Profile(profile);
        loadPage(profile);
    }

    public void loadPage(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeActivity_frame,fragment);
        fragmentTransaction.commit();
    }

    public void Profile(Fragment fragment)
    {
        Intent intent = getIntent();
        String n = intent.getStringExtra("name");
        String j = intent.getStringExtra("job");
        String c = intent.getStringExtra("company");
        String ph = intent.getStringExtra("phone");
        String i = intent.getStringExtra("image");


        Bundle bundle = new Bundle();
        bundle.putString("name",n);
        bundle.putString("job",j);
        bundle.putString("phone",ph);
        bundle.putString("company",c);

        if(i.length()!=0 || !i.equals("") || i!="")
        {
            bundle.putString("image",i);
        }

        fragment.setArguments(bundle);
    }

    @Override
    public void onBackPressed() {

    }
}
