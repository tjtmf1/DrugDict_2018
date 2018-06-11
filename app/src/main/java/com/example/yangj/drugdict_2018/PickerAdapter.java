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

import java.util.ArrayList;
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
            case "원형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_circled_thin_filled_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "타원형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_oval_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "삼각형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_triangle_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "사각형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_rectangular_30);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "마름모형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_rhombus_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "장방형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_pill_32);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "오각형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_pentagon_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "육각형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_hexagon_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "팔각형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_octagon_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "없음":
                colorPicker.imageView.setImageResource(R.drawable.icons8_round_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "(-)형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_round_minus_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "(+)형":
                colorPicker.imageView.setImageResource(R.drawable.icons8_round_plus_50);
                colorPicker.imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
        }

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
