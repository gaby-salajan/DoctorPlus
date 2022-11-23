package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ns.doctorplus.logic.UsersFirestoreManager;
import com.ns.doctorplus.model.User;


public class LoginActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;
    private EditText txtCNP;
    private EditText txtPassword;

    private UsersFirestoreManager usersFirestoreManager;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnConnect);
        txtCNP = findViewById(R.id.fieldCNPLogin);
        txtPassword = findViewById(R.id.fieldPasswordLogin);

        usersFirestoreManager = UsersFirestoreManager.newInstance();

        btnLogin.setOnClickListener(view -> {
            currentUser = null;
            usersFirestoreManager.getUser(txtCNP.getText().toString()).addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    currentUser = document.toObject(User.class);
                    //Log.i("User", user.toString());
                }
                if(currentUser != null){
                    if(currentUser.getPassword().contentEquals(txtPassword.getText())){
                        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                    }
                }
            });;


        });

        btnRegister.setOnClickListener(view -> {
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
        });
    }
}