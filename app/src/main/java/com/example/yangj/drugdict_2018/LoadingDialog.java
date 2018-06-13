package com.example.yangj.drugdict_2018;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingDialog extends ProgressDialog {
    private Context context;
    private ImageView imageVIew;
    private TextView textView;

    public LoadingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        imageVIew = (ImageView) findViewById(R.id.loading_image);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.loading);
        imageVIew.setAnimation(anim);
    }

    public void setTextViewText(String str){
        View view = getLayoutInflater().inflate(R.layout.loading_dialog, null);

        textView = (TextView) view.findViewById(R.id.loading_text);
        textView.setText(str);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
