package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ns.doctorplus.logic.FirestoreCallback;
import com.ns.doctorplus.logic.UsersFirestoreDbContract;
import com.ns.doctorplus.logic.UsersFirestoreManager;
import com.ns.doctorplus.model.User;


public class LoginActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;
    private EditText txtCNP;
    private EditText txtPassword;

    private UsersFirestoreManager usersFirestoreManager;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnConnect);
        EditText txtCNP = findViewById(R.id.fieldCNPLogin);
        EditText txtPassword = findViewById(R.id.fieldPasswordLogin);


        usersFirestoreManager = UsersFirestoreManager.newInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //usersFirestoreManager.sendContactsBulk();

                if(txtCNP.getText().toString().matches("") || txtPassword.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "completati ambele campuri", Toast.LENGTH_LONG).show();
                }
                else{
                    usersFirestoreManager.getUser3(txtCNP.getText().toString(), txtPassword.getText().toString(), new FirestoreCallback() {
                        @Override
                        public void onResponse(User user1) {
                            user = user1;

                            if (user != null){
                                if(user.getPassword().contentEquals(txtPassword.getText())){
                                    Intent toHomePage = new Intent(LoginActivity.this, HomePageActivity.class);
                                    Gson gson = new Gson();
                                    String myJson = gson.toJson(user);
                                    toHomePage.putExtra("myjson", myJson);
                                    startActivity(toHomePage);

                                    txtCNP.setText("");
                                    txtPassword.setText("");
                                    user = null;
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "cnp/parola gresite", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "utilizatorul nu a fost gasit", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {}

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