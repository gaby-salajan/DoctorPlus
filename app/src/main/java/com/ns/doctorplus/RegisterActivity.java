package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {

    ConstraintLayout registerLayout;
    Button registerButton;
    TextView fieldBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerLayout = findViewById(R.id.layoutRegister);

        registerButton = findViewById(R.id.registerButton);
        fieldBirthDate = findViewById(R.id.fieldBirthDate);

        fieldBirthDate.setOnClickListener(view -> {
            showDatePickDialog();
        });
    }

    public void showDatePickDialog(){
        MaterialDatePicker<?> picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select date of birth")
                .build();

        picker.addOnPositiveButtonClickListener(selection -> {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
            fieldBirthDate.setText(simpleFormat.format(selection));
        });
        picker.show(getSupportFragmentManager(), "DATE_PICKER");


    }
}