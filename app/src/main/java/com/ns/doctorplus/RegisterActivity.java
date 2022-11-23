package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.afollestad.materialdialogs.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

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

        fieldBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = new GregorianCalendar();
                AtomicInteger year = new AtomicInteger(cal.get(Calendar.YEAR));
                AtomicInteger month = new AtomicInteger(cal.get(Calendar.MONTH));
                AtomicInteger day = new AtomicInteger(cal.get(Calendar.DAY_OF_MONTH));

                DatePickerDialog datePicker = new DatePickerDialog(RegisterActivity.this,
                        R.style.Theme_DoctorPlus,
                        (datePicker1, y, m, d) -> {
                            year.set(y);
                            month.set(m);
                            day.set(d);
                        },
                        year.get(),
                        month.get(),
                        day.get());

                datePicker.updateDate(1988,9,8);
            }
        });

    }
}