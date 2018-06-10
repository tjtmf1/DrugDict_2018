package com.example.yangj.drugdict_2018;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.force.infodb.ProductInfo;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByShapeFragment extends Fragment {

    HorizontalPicker colorPicker, shapePicker, divisionPicker;
    static Context context;
    String[] color = {"전체" ,"하양", "노랑", "주황", "분홍","빨강","갈색","연두","초록","청록","파랑","남색","자주","보라","회색","검정","투명"};
    String[] shape = {"전체" ,"원형", "타원형","반원형","삼각형","사각형","마름모형","장방향","오각형","육각형","팔각형","기타"};
    String[] divisionLine = {"없음","(-)형", "(+)형", "기타"};
    public SearchByShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_search_by_shape, container, false);
        colorPicker = (HorizontalPicker)view.findViewById(R.id.colorPicker);
        shapePicker = (HorizontalPicker)view.findViewById(R.id.shapePicker);
        divisionPicker = (HorizontalPicker)view.findViewById(R.id.divisionPicker);
        colorPicker.setValues(color);
        shapePicker.setValues(shape);
        divisionPicker.setValues(divisionLine);
        colorPicker.setSelectedItem(2);
        shapePicker.setSelectedItem(1);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getActivity(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.5f);
        pickerLayoutManager.setScaleDownDistance(0.5f);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(pickerLayoutManager);
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");
        data.add("8");
        data.add("9");
        PickerAdapter adapter = new PickerAdapter(getContext(), data, recyclerView);
        recyclerView.setAdapter(adapter);




        Button btn = (Button)view.findViewById(R.id.shapeSearchBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchColor = color[colorPicker.getSelectedItem()];
                String searchShape = shape[shapePicker.getSelectedItem()];
                String searchDivision = divisionLine[divisionPicker.getSelectedItem()];
                //검색후 프래그먼트 바꿔줌

                ArrayList<ProductInfo> a = new ArrayList<>();
                a.add(new ProductInfo("n", "s", "s", "1", "1", "1", "1"));
                if(getActivity() instanceof callListListener){
                    ((callListListener) getActivity()).changeListView(a);
                }
            }
        });
        return view;
    }

    public interface callListListener{
        public void changeListView(ArrayList<com.example.force.infodb.ProductInfo> list);
    }
}
