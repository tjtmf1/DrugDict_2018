package com.example.yangj.drugdict_2018;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SignUpDialog extends Dialog {
    private Context context;
    final private String[] TEXT = {"Login with GOOGLE", "Sign Up"};
    private ListView listView;
    private ArrayAdapter adapter;
    public SignUpDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.sign_up_dialog);
        listView = (ListView)findViewById(R.id.dialogListView);
        adapter = new ArrayAdapter(context, R.layout.sign_up_dialog_row){
            @Override
            public int getCount() {
                return 2;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = convertView;
                if(view == null){
                    LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = vi.inflate(R.layout.sign_up_dialog_row, null);
                    ((ImageView)view.findViewById(R.id.dialogRowImage)).setImageResource(android.R.drawable.arrow_up_float);
                    ((TextView)view.findViewById(R.id.dialogRowText)).setText(TEXT[position]);
                }

                return view;
            }
        };
        listView.setAdapter(adapter);
    }
}
