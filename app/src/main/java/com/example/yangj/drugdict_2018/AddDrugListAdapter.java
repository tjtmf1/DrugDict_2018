package com.example.yangj.drugdict_2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

public class AddDrugListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<ProductInfo> mDrugs;
    ArrayList<EditText> mEditTexts;
    ArrayList<CheckBox> mCheckBoxs;


    public AddDrugListAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.context = context;
        this.mDrugs = objects;

        mEditTexts = new ArrayList<>();
        mCheckBoxs = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.add_drug_layout, null);

            ProductInfo drug = mDrugs.get(position);
            EditText etDetectDrug = (EditText) v.findViewById(R.id.etDetectDrug);
            CheckBox cbPrescription = (CheckBox) v.findViewById(R.id.cbPrescription);

            etDetectDrug.setText(drug.getName());
            cbPrescription.setChecked(true);

            mEditTexts.add(etDetectDrug);
            mCheckBoxs.add(cbPrescription);

            cbPrescription.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCheckBoxs.get(position).setChecked(isChecked);

                    String str = "";
                    for(int i=0; i<mDrugs.size(); i++){
                        if(mCheckBoxs.get(i).isChecked())
                            str += mDrugs.get(i).getName();
                    }
                    Log.d("DrugListAdapter", str);
                }
            });
        }
        return v;
    }

    public ArrayList<EditText> getmEditTexts() {
        return mEditTexts;
    }

    public ArrayList<CheckBox> getmCheckBoxs() {
        return mCheckBoxs;
    }
}
