package com.example.cm.mywork.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cm.mywork.R;

import java.util.ArrayList;

/**
 * Created by cm on 30/04/2018.
 */

public class DatesAdapters  extends ArrayAdapter{

    Context context;
    int res;
    ArrayList<String> arrayList;

    public DatesAdapters(@NonNull Context context, int resource, @NonNull  ArrayList<String> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        TextView date = convertView.findViewById(R.id.dateList_date);
        date.setText(arrayList.get(position));

        return convertView;
    }
}
