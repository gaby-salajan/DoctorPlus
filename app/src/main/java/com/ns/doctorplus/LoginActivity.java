package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText txtCNP;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnRegister = findViewById(R.id.btnRegister);
        txtCNP = findViewById(R.id.fieldCNPLogin);
        txtPassword = findViewById(R.id.fieldPasswordLogin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    public void onFinish() {
                        txtCNP.setText("");
                        txtCNP.setHint("Inroduceti CNP");
                        txtPassword.setText("");
                        txtPassword.setHint("Introduceti Parola");
                    }
                }.start();
            }
        });
    }
}