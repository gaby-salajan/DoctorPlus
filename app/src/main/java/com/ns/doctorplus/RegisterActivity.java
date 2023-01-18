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
    private EditText cnpField;
    private EditText fullNameField;
    private TextView birthDateField;
    private EditText telField;
    private EditText addressField;

    private Button btn;

    public void showDatePickDialog(){
        MaterialDatePicker<?> picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Selectati data nasterii")
                .build();

        picker.addOnPositiveButtonClickListener(selection -> {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
            birthDateField.setText(simpleFormat.format(selection));
        });
        picker.show(getSupportFragmentManager(), "DATE_PICKER");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = (Button) findViewById(R.id.confirmBtn);
        cnpField = (EditText) findViewById(R.id.fieldType);
        fullNameField = (EditText) findViewById(R.id.fieldName);
        birthDateField = (TextView) findViewById(R.id.fieldAddress);
        telField = (EditText) findViewById(R.id.fieldPhone);
        addressField = (EditText) findViewById(R.id.registerAddress);

        birthDateField.setOnClickListener(view -> {
            showDatePickDialog();
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cnp, fullname, tel, address;
                String birthDate;

                cnp = cnpField.getText().toString();
                fullname = fullNameField.getText().toString();
                birthDate =birthDateField.getText().toString();
                tel = telField.getText().toString();
                address = addressField.getText().toString();

                UserHelper.addUser(cnp, fullname, birthDate, tel, address, "Patient");
                PatientHelper.addPatient(cnp, fullname, birthDate, tel, address);
                System.out.println("Add patient " + fullname + " to patient collection");

                //DoctorHelper.addDoctor(fullname, "adress", tel, specialite);

                Intent k = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(k);
            }


        });
    }

}
