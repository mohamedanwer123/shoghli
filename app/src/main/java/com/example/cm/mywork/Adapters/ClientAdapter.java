package com.example.cm.mywork.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cm.mywork.Activites.Client_Info;
import com.example.cm.mywork.Fragments.Clients;
import com.example.cm.mywork.Models.ClientData;
import com.example.cm.mywork.R;

import java.util.ArrayList;

/**
 * Created by cm on 29/04/2018.
 */

public class ClientAdapter extends ArrayAdapter {

    Context context;
    int res;
    ArrayList<ClientData> arrayList;

    public ClientAdapter(@NonNull Context context, int resource, @NonNull  ArrayList<ClientData> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);
        TextView name = convertView.findViewById(R.id.clientItem_name);
        TextView job =  convertView.findViewById(R.id.clientItem_job);
        LinearLayout linearLayout = convertView.findViewById(R.id.clientItem_ly);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Client_Info.class);
                intent.putExtra("name",arrayList.get(position).getName());
                intent.putExtra("job",arrayList.get(position).getJob());
                intent.putExtra("phone",arrayList.get(position).getPhone());
                intent.putExtra("location",arrayList.get(position).getLocation());

                context.startActivity(intent);
            }
        });

        name.setText(arrayList.get(position).getName());
        job.setText(arrayList.get(position).getJob());

        return convertView;
    }
}
