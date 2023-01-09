package com.ns.doctorplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ns.doctorplus.Common.Common;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdminHomeActivity extends AppCompatActivity {
    static String admin;
    Button btnSearch;
    Button btnListAll;
    EditText emailText;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_admin_home);
        btnSearch = findViewById(R.id.searchBtn);
        btnListAll = findViewById(R.id.listAllBtn);


    }
}
