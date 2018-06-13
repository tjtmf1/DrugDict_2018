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

import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByShapeFragment extends Fragment {

    static Context context;
    List<String> color;
    List<String> shape;
    List<String> divisionLine;
    //String[] color = {"전체" ,"하양", "노랑", "주황", "분홍","빨강","갈색","연두","초록","청록","파랑","남색","자주","보라","회색","검정","투명"};
    //String[] shape = {"전체" ,"원형", "타원형","반원형","삼각형","사각형","마름모형","장방형","오각형","육각형","팔각형","기타"};
    //String[] divisionLine = {"없음","(-)형", "(+)형", "기타"};
    public SearchByShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        divisionLine = new ArrayList<>();
        divisionLine.add("없음"); divisionLine.add("(-)형"); divisionLine.add("(+)형");

        shape = new ArrayList<>();
        shape.add("전체"); shape.add("원형"); shape.add("타원형"); shape.add("삼각형"); shape.add("사각형");
        shape.add("마름모형"); shape.add("장방형"); shape.add("오각형"); shape.add("육각형"); shape.add("팔각형");

        color = new ArrayList<>();
        color.add("전체"); color.add("하양"); color.add("노랑"); color.add("주황"); color.add("분홍"); color.add("빨강");
        color.add("갈색"); color.add("연두"); color.add("초록"); color.add("청록"); color.add("파랑"); color.add("남색");
        color.add("자주"); color.add("보라"); color.add("회색"); color.add("검정"); color.add("투명");

        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_search_by_shape, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getActivity(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.99f);
        pickerLayoutManager.setScaleDownDistance(0.8f);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(pickerLayoutManager);
        PickerAdapter adapter = new PickerAdapter(getContext(), color, recyclerView);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView2 = (RecyclerView)view.findViewById(R.id.rv2);
        PickerLayoutManager pickerLayoutManager2 = new PickerLayoutManager(getActivity(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager2.setChangeAlpha(true);
        pickerLayoutManager2.setScaleDownBy(0.99f);
        pickerLayoutManager2.setScaleDownDistance(0.8f);

        SnapHelper snapHelper2 = new LinearSnapHelper();
        snapHelper2.attachToRecyclerView(recyclerView2);

        recyclerView2.setLayoutManager(pickerLayoutManager2);
        PickerAdapter adapter2 = new PickerAdapter(getContext(), shape, recyclerView2);
        recyclerView2.setAdapter(adapter2);

        RecyclerView recyclerView3 = (RecyclerView)view.findViewById(R.id.rv3);
        PickerLayoutManager pickerLayoutManager3 = new PickerLayoutManager(getActivity(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager3.setChangeAlpha(true);
        pickerLayoutManager3.setScaleDownBy(0.6f);
        pickerLayoutManager3.setScaleDownDistance(0.4f);

        SnapHelper snapHelper3 = new LinearSnapHelper();
        snapHelper3.attachToRecyclerView(recyclerView3);

        recyclerView3.setLayoutManager(pickerLayoutManager3);
        PickerAdapter adapter3 = new PickerAdapter(getContext(), divisionLine, recyclerView3);
        recyclerView3.setAdapter(adapter3);

        Button btn = (Button)view.findViewById(R.id.shapeSearchBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //검색후 프래그먼트 바꿔줌

                ArrayList<ProductInfo> a = new ArrayList<>();
                a.add(new ProductInfo("n", "s", "s", "1", "1", "1", "1","1","1"));
                if(getActivity() instanceof callListListener){
                    ((callListListener) getActivity()).changeListView(a);
                }
            }
        });
        return view;
    }

    public interface callListListener{
        public void changeListView(ArrayList<ProductInfo> list);
    }
}
