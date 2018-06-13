package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupToolBar();

        mAuth = FirebaseAuth.getInstance();
    }

    public void setupToolBar(){
        getSupportActionBar().setTitle("회원 가입");
    }

    public void Confirm(View view) {
        EditText editPASSWORD = (EditText)findViewById(R.id.inputPASSWORD);
        EditText editEMAIL = (EditText)findViewById(R.id.inputEMAIL);
        EditText editCONFIRM = (EditText)findViewById(R.id.inputCONFIRM);
        String password = editPASSWORD.getText().toString();
        String email = editEMAIL.getText().toString();
        String confirmPassword = editCONFIRM.getText().toString();

        if(password.equals(confirmPassword)){
            createUser(email, password);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("flag", 1);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
