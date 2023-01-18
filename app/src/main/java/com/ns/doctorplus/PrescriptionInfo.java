package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PrescriptionInfo extends AppCompatActivity {
    TextView dateView , diseaseView, descriptionView, treatmentView, doctorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_info);
        dateView = findViewById(R.id.dateView);
        diseaseView = findViewById(R.id.diseaseView);
        treatmentView = findViewById(R.id.treatmentView);
        descriptionView = findViewById(R.id.descriptionView);
        doctorView = findViewById(R.id.doctorView);

        String givenDate = getIntent().getStringExtra("dateCreated");

        String[] date;
        String day_name, day, month, time, year;

        date = givenDate.split(" ");
        // Thu Jun 04 14:46:12 GMT+01:00 2020
        day_name = date[0];
        day = date[2];
        month = date[1];
        time = date[3];
        year = date[5];

        dateView.setText("Data: " + day + "/" + monthToNumber(month) + "/" + year);
        diseaseView.setText(getIntent().getStringExtra("disease"));
        treatmentView.setText(getIntent().getStringExtra("treatment"));
        descriptionView.setText(getIntent().getStringExtra("description"));
        doctorView.setText("Doctor,\n" + getIntent().getStringExtra("doctor"));
    }

    private String monthToNumber (String month) {
        String res = "0";

        switch (month){
            case "Jan":
                res = "1";
                break;
            case "Feb":
                res = "2";
                break;
            case "Mar":
                res = "3";
                break;
            case "Apr":
                res = "4";
                break;
            case "May":
                res = "5";
                break;
            case "Jun":
                res = "6";
                break;
            case "Jul":
                res = "7";
                break;
            case "Aug":
                res = "8";
                break;
            case "Sep":
                res = "9";
                break;
            case "Oct":
                res = "10";
                break;
            case "Nov":
                res = "11";
                break;
            case "Dec":
                res = "12";
                break;
        }
        return res;
    }

}

