package com.example.cm.mywork.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm.mywork.Models.BillData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddBills extends AppCompatActivity  implements TextWatcher{

    EditText name , amount , price , before , after , discount ;
    DatabaseReference databaseReference;
    String phone;
    String pn , pp , pa , pd , ppb, ppa , pdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bills);
        init();
        discount.addTextChangedListener(this);
    }

    private void init() {

        name = findViewById(R.id.addBills_name);
        amount = findViewById(R.id.addBills_amount);
        price = findViewById(R.id.addBills_price);
        discount = findViewById(R.id.addBills_discount);
        before = findViewById(R.id.addBills_before);
        after = findViewById(R.id.addBills_aftter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        phone = getIntent().getStringExtra("phone");
    }

    public void Save(View view) {
        Calendar calendar = Calendar.getInstance();
        pdate = calendar.get(Calendar.DAY_OF_MONTH) +"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);

        pn = name.getText().toString().trim();
        pp = price.getText().toString().trim();
        pa = amount.getText().toString().trim();
        pd = discount.getText().toString().trim();
        ppb = before.getText().toString().trim();
        ppa = after.getText().toString().trim();

        if(pn.length()==0 || pp.length()==0 || pa.length()==0 || pd.length()==0)
        {
            Toast.makeText(this, "Enter Bill Data", Toast.LENGTH_LONG).show();
        }else
        {
            BillData billData = new BillData(pdate,pn,pa,pp+" PE",pd+"%",ppb,ppa);
            databaseReference.child(phone+"_Bills").push().setValue(billData);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

            name.setText("");
            price.setText("");
            discount.setText("");
            after.setText("");
            before.setText("");
            amount.setText("");
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        pp = price.getText().toString().trim();
        pa = amount.getText().toString().trim();
        pd = discount.getText().toString().trim();

        if(pp.length()==0 || pa.length()==0)
        {
            Toast.makeText(this, "Enter amount and public price", Toast.LENGTH_LONG).show();
        }else
        {
            float p = Float.valueOf(pp);
            int a = Integer.valueOf(pa);
            Float d = Float.valueOf(pd);

            float r1 = p*a;
            float r2  = r1*((100-d)/100);
            before.setText(""+r1);
            after.setText(""+r2);
        }

    }
}
