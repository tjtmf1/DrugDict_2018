package com.example.yangj.drugdict_2018;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TakingDrugListAdapter extends ArrayAdapter {

    Context context;
    ArrayList<ProductInfo> mDrugs;

    public TakingDrugListAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.context = context;
        this.mDrugs = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.taking_drug_layout, null);

            ProductInfo drug = mDrugs.get(position);
            ImageView ivDrugImage = (ImageView) v.findViewById(R.id.ivDrugImage);
            TextView tvDrugName = (TextView) v.findViewById(R.id.tvDrugName);
            tvDrugName.setTextColor(Color.BLACK);
            //ivDrugImage.setImageResource(); // 약 이미지 추가
            tvDrugName.setText(drug.getName());
        }

        return v;
    }

}
