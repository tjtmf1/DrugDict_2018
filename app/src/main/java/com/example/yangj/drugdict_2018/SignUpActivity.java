package com.example.yangj.drugdict_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    SignUpDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dialog = new SignUpDialog(this);
        dialog.show();
    }

    public void Confirm(View view) {
        EditText editUSERID = (EditText)findViewById(R.id.inputUSERID);
        EditText editPASSWORD = (EditText)findViewById(R.id.inputPASSWORD);
        EditText editEMAIL = (EditText)findViewById(R.id.inputEMAIL);
        EditText editCONFIRM = (EditText)findViewById(R.id.inputCONFIRM);
        String userid = editUSERID.getText().toString();
        String password = editPASSWORD.getText().toString();
        String email = editEMAIL.getText().toString();
        String confirmPassword = editCONFIRM.getText().toString();
    }
}
