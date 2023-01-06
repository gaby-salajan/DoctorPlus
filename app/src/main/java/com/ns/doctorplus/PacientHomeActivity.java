package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ns.doctorplus.model.User;

public class PacientHomeActivity extends AppCompatActivity {
    String welcome = "Buna ziua, ";
    User user;

    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);

        welcomeText = findViewById(R.id.textTitluHome);
        welcomeText.setText(welcome + user.getFirstName());
    }


}