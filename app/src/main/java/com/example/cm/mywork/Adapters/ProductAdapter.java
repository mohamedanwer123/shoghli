package com.example.cm.mywork.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cm.mywork.Models.BillData;
import com.example.cm.mywork.Models.ProductData;
import com.example.cm.mywork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cm on 02/05/2018.
 */

public class ProductAdapter extends ArrayAdapter {

    Context context;
    int res;
    ArrayList<ProductData> arrayList;

    public ProductAdapter(@NonNull Context context, int resource , ArrayList<ProductData> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.res = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        CircleImageView circleImageView = convertView.findViewById(R.id.productList_img);
        TextView name = convertView.findViewById(R.id.productList_name);
        TextView price = convertView.findViewById(R.id.productList_price);
        TextView company = convertView.findViewById(R.id.productList_company);
        TextView des = convertView.findViewById(R.id.productList_description);

        ProductData productData = arrayList.get(position);
        
        Picasso.get().load(Uri.parse(productData.getImg())).into(circleImageView);
        name.setText(productData.getName());
        price.setText(productData.getPrice()+" PE");
        company.setText(productData.getCompany());
        des.setText(productData.getDescribtion());

        return convertView;
    }
}
