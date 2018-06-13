package com.example.yangj.drugdict_2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

/*
public class AddDrugListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<String> mDrugs;
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

            String drug = mDrugs.get(position);
            EditText etDetectDrug = (EditText) v.findViewById(R.id.etDetectDrug);
            CheckBox cbPrescription = (CheckBox) v.findViewById(R.id.cbPrescription);

            etDetectDrug.setText(drug);
            cbPrescription.setChecked(true);

            mEditTexts.add(etDetectDrug);
            mCheckBoxs.add(cbPrescription);

            cbPrescription.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCheckBoxs.get(position).setChecked(isChecked);
//
//                    String str = "";
//                    for(int i=0; i<mDrugs.size(); i++){
//                        if(mCheckBoxs.get(i).isChecked())
//                            str += mDrugs.get(i);
//                    }
//                    Log.d("DrugListAdapter", str);
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
*/

public class AddDrugListAdapter extends RecyclerView.Adapter<AddDrugListAdapter.ViewHolder>{
    ArrayList<String> name;
    ArrayList<Boolean> arrCheckced;
    public AddDrugListAdapter(ArrayList<String> _name){
        name = _name;
        arrCheckced = new ArrayList<>();
        for(int i=0;i<name.size();i++)
            arrCheckced.add(true);
    }
    @NonNull
    @Override
    public AddDrugListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_drug_layout, parent,  false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AddDrugListAdapter.ViewHolder holder, int position) {
        holder.editText.setText(name.get(position));
        holder.checkBox.setChecked(true);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                arrCheckced.set(position, isChecked);
            }
        });
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name.set(position, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText editText;
        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.etDetectDrug);
            checkBox = itemView.findViewById(R.id.cbPrescription);
        }
    }

    public ArrayList<Boolean> getCheckced() {
        return arrCheckced;
    }

    public ArrayList<String> getName() {
        return name;
    }
}