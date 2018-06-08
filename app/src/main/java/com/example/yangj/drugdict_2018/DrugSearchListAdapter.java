package com.example.yangj.drugdict_2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.force.infodb.ProductInfo;

import java.util.List;

public class DrugSearchListAdapter extends ArrayAdapter<com.example.force.infodb.ProductInfo> {
    private List<ProductInfo> productInfoList;
    private Context context;

    public DrugSearchListAdapter(@NonNull Context context, int resource, @NonNull List<ProductInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        productInfoList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.drug_list_row, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.list_row_image);
        TextView textView = (TextView) view.findViewById(R.id.list_row_name);
        imageView.setImageResource(android.R.drawable.btn_star);
        textView.setText(productInfoList.get(position).getmName());

        return view;
    }
}

