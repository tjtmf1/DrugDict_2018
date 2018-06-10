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

        switch (colorPicker.textView.getText().toString()){
            case "전체":
                colorPicker.imageView.setImageResource(R.drawable.ic_bubble_chart_black_24dp);
                break;
            case "하양":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_white_24dp);
                break;
            case "노랑":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_yellow_24dp);
                break;
            case "주황":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_orange_24dp);
                break;
            case "분홍":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_pink_24dp);
                break;
            case "빨강":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_red_24dp);
                break;
            case "갈색":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_wood_24dp);
                break;
            case "연두":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_light_green_24dp);
                break;
            case "초록":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_green_24dp);
                break;
            case "청록":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_cyan_24dp);
                break;
            case "파랑":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_blue_24dp);
                break;
            case "남색":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_deep_blue_24dp);
                break;
            case "자주":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_wine_24dp);
                break;
            case "보라":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_purple_24dp);
                break;
            case "회색":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_gray_24dp);
                break;
            case "검정":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_black_24dp);
                break;
            case "투명":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_transparent_24dp);
                break;
            case " ":
                colorPicker.imageView.setImageResource(R.drawable.ic_fiber_manual_record_transparent_24dp);
                break;
        }

        //color.add(" ");
        //        color.add("전체"); color.add("하양"); color.add("노랑"); color.add("주황"); color.add("분홍"); color.add("빨강");
        //        color.add("갈색"); color.add("연두"); color.add("초록"); color.add("청록"); color.add("파랑"); color.add("남색");
        //        color.add("자주"); color.add("보라"); color.add("회색"); color.add("검정"); color.add("투명"); color.add(" ");
        
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
