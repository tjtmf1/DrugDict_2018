package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginBtn(View view) {
        EditText editID = (EditText)findViewById(R.id.inputID);
        EditText editPW = (EditText)findViewById(R.id.inputPW);
        String id = editID.getText().toString();
        String pw = editPW.getText().toString();
    }

    public void SingupBtn(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
