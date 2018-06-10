package com.example.yangj.drugdict_2018;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PickerAdapter extends RecyclerView.Adapter<PickerAdapter.ColorPicker> {
    private Context context;
    private List<String> dataList;
    private RecyclerView recyclerView;

    public PickerAdapter(Context context, List<String> dataList, RecyclerView recyclerView) {
        this.context = context;
        this.dataList = dataList;
        this.recyclerView = recyclerView;
    }

    @Override
    public ColorPicker onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.picker_item_layout, parent, false);
        return new PickerAdapter.ColorPicker(view);
    }

    @Override
    public void onBindViewHolder(ColorPicker holder, final int position) {
        ColorPicker colorPicker = holder;
        colorPicker.textView.setText(dataList.get(position));

        colorPicker.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView != null) {
                    recyclerView.smoothScrollToPosition(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void swapData(List<String> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    class ColorPicker extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout layout;
        public ColorPicker(View itemView) {
            super(itemView);
            layout = (LinearLayout)itemView.findViewById(R.id.picker);
            imageView = (ImageView)itemView.findViewById(R.id.picker_image);
            textView  =(TextView)itemView.findViewById(R.id.picker_text);
        }
    }
}
