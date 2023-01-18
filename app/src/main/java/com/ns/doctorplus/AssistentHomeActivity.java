package com.ns.doctorplus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ns.doctorplus.Common.Common;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AssistentHomeActivity extends AppCompatActivity {
    static String doc;
    Button SignOutBtn2;
    Button BtnRequst;
    Button listPatients;
    Button appointementBtn;

    TextView welcomeText;
    String welcome = "Buna ziua, ";

    @OnClick(R.id.profile)
    void profileBtnClick(){
        Intent k = new Intent(AssistentHomeActivity.this, ProfileAsistentActivity.class);
        startActivity(k);
    }
    Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistent_home);
        unbinder = ButterKnife.bind(this,this);
        Common.CurrentDoctor = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        Common.CurrentUserType = "asistent";
        listPatients = findViewById(R.id.listPatients);
        BtnRequst=findViewById(R.id.btnRequst);
        SignOutBtn2=findViewById(R.id.signOutBtn);
        appointementBtn = findViewById(R.id.appointement);

        SignOutBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        listPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(AssistentHomeActivity.this, AsistentMyPatientsActivity.class);
                startActivity(k);
            }
        });

        welcomeText = findViewById(R.id.welcomeTextDoctor);
        FirebaseFirestore.getInstance().collection("User").document(Common.CurrentDoctor).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Common.CurrentUserName = documentSnapshot.getString("name");
                welcomeText.setText(welcome + Common.CurrentUserName);
            }
        });
    }
}
