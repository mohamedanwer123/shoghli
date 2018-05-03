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
import android.widget.Toast;

import com.example.cm.mywork.Activites.AddClients;
import com.example.cm.mywork.Adapters.ClientAdapter;
import com.example.cm.mywork.Models.ClientData;
import com.example.cm.mywork.Models.UserData;
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
public class Clients extends Fragment  {

    FloatingActionButton add;
    ArrayList<ClientData> arrayList , searchList;
    ClientAdapter clientAdapter;
    ListView listView;
    String ph;
    DatabaseReference databaseReference;
    ClientData clientData;
    EditText search;

    public Clients() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_clients, container, false);
       init(view);
       putData();

       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getActivity(), AddClients.class).putExtra("phone",ph));
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

               clientAdapter = new ClientAdapter(getActivity(),R.layout.clien_list,searchList);
               listView.setAdapter(clientAdapter);

               if(charSequence.length()==0 || charSequence.equals("") || charSequence == "")
               {
                   clientAdapter = new ClientAdapter(getActivity(),R.layout.clien_list,arrayList);
                   listView.setAdapter(clientAdapter);
               }
           }

           @Override
           public void afterTextChanged(Editable editable) {
           }
       };

        search.addTextChangedListener(textWatcher);

       return view;
    }

    public void init(View view)
    {
        add = view.findViewById(R.id.clientFragment_add_client);
        search = view.findViewById(R.id.ClientsFragment_search);
        listView = view.findViewById(R.id.ClientsFragment_lv);
        arrayList = new ArrayList<>();
        searchList = new ArrayList<>();
        ph = getArguments().getString("phone");
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void putData()
    {

        databaseReference.child(ph+"_Clients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    clientData =  d.getValue(ClientData.class);
                    arrayList.add(clientData);
                }

                clientAdapter = new ClientAdapter(getActivity(),R.layout.clien_list,arrayList);
                listView.setAdapter(clientAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
