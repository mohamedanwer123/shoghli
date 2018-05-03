package com.example.cm.mywork.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cm.mywork.Activites.AddProduct;
import com.example.cm.mywork.Adapters.ClientAdapter;
import com.example.cm.mywork.Adapters.ProductAdapter;
import com.example.cm.mywork.Models.ProductData;
import com.example.cm.mywork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Products extends Fragment {


    ArrayList<ProductData> arrayList , searchList;
    ProductAdapter productAdapter;
    ListView listView;
    FloatingActionButton add;
    DatabaseReference databaseReference;
    String phone;
    EditText search ;


    public Products() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        init(view);
        putData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddProduct.class).putExtra("phone",phone));
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchList.clear();
                for(int x = 0 ; x < arrayList.size() ; x++)
                {
                    if(arrayList.get(x).getName().contains(charSequence))
                    {
                        searchList.add(arrayList.get(x));
                    }
                }

                productAdapter = new ProductAdapter(getActivity(),R.layout.products_list,searchList);
                listView.setAdapter(productAdapter);

                if(charSequence.length()==0 || charSequence.equals("") || charSequence == "")
                {
                    productAdapter = new ProductAdapter(getActivity(),R.layout.products_list,arrayList);
                    listView.setAdapter(productAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        search.addTextChangedListener(textWatcher);
        return view;
    }

    public  void init(View view)
    {
        listView = view.findViewById(R.id.productFragment_lv);
        add = view.findViewById(R.id.productFragment_add);
        arrayList = new ArrayList<>();
        searchList = new ArrayList<>();
        phone = getArguments().getString("phone");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        search = view.findViewById(R.id.productFragment_search);
    }

    public void putData()
    {

        databaseReference.child(phone+"_Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();
                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    arrayList.add(d.getValue(ProductData.class));
                }

                productAdapter = new ProductAdapter(getActivity(),R.layout.products_list,arrayList);
                listView.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
