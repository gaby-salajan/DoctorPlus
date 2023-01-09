package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.ns.doctorplus.fireStoreApi.PatientHelper;
import com.ns.doctorplus.fireStoreApi.UserHelper;

import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText fullName;
    private TextView birthDate;
    private EditText teL;
    private Button btn;

    public void showDatePickDialog(){
        MaterialDatePicker<?> picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Selectati data nasterii")
                .build();

        picker.addOnPositiveButtonClickListener(selection -> {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
            birthDate.setText(simpleFormat.format(selection));
        });
        picker.show(getSupportFragmentManager(), "DATE_PICKER");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = (Button) findViewById(R.id.confirmBtn);
        fullName = (EditText) findViewById(R.id.registerFullName);
        birthDate = (TextView) findViewById(R.id.registerBirthDate);
        teL = (EditText) findViewById(R.id.registerTel);

        birthDate.setOnClickListener(view -> {
            showDatePickDialog();
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, birtDay, tel, type;
                fullname = fullName.getText().toString();
                birtDay = birthDate.getText().toString();
                tel = teL.getText().toString();
                type = "Patient";
                UserHelper.addUser(fullname, birtDay, tel, type);
                PatientHelper.addPatient(fullname, "adress", tel);
                System.out.println("Add patient " + fullname + " to patient collection");

                //DoctorHelper.addDoctor(fullname, "adress", tel, specialite);

                Intent k = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(k);
            }


        });
    }

}
