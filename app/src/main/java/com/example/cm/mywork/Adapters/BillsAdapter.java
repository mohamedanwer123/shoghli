package com.example.cm.mywork.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cm.mywork.Models.BillData;
import com.example.cm.mywork.R;

import java.util.ArrayList;

/**
 * Created by cm on 30/04/2018.
 */

public class BillsAdapter extends ArrayAdapter {

    Context context;
    int res;
    ArrayList<BillData> arrayList;

    public BillsAdapter(@NonNull Context context, int resource, @NonNull  ArrayList<BillData> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(res,parent,false);


        TextView  date = view.findViewById(R.id.billsList_date);
        TextView  name = view.findViewById(R.id.billsList_name);
        TextView  discount = view.findViewById(R.id.billsList_discount);
        TextView  amount = view.findViewById(R.id.billsList_amount);
        TextView  public_price = view.findViewById(R.id.billsList_price);
        TextView  price_before_discount = view.findViewById(R.id.billsList_before_discount);
        TextView  price_after_discount = view.findViewById(R.id.billsList_after_discount);


        BillData productData = arrayList.get(position);

        date.setText(productData.getDate());
        name.setText(productData.getName());
        discount.setText(productData.getDiscount());
        amount.setText(productData.getAmount());
        public_price.setText(productData.getPublicPrice());
        price_before_discount.setText(productData.getTotalPrice_beforeDiscount());
        price_after_discount.setText(productData.getTotalPrice_afterDiscount());





        return view;
    }

}
