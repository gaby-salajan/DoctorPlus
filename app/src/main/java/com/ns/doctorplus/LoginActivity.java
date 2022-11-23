package com.ns.doctorplus;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ns.doctorplus.logic.UsersFirestoreManager;

import org.checkerframework.checker.nullness.qual.NonNull;


public class LoginActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;
    private EditText txtCNP;
    private EditText txtPassword;

    private UsersFirestoreManager usersFirestoreManager;

//    void getUser(){
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnConnect);
        txtCNP = findViewById(R.id.fieldCNPLogin);
        txtPassword = findViewById(R.id.fieldPasswordLogin);

        usersFirestoreManager = UsersFirestoreManager.newInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFirestoreManager.sendContactsBulk();
                //usersFirestoreManager.getUser(txtCNP.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(LoginActivity.this, "Bulk contacts sent", Toast.LENGTH_LONG).show();
            }
        });

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