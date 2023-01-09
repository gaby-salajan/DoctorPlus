package com.ns.doctorplus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.ns.doctorplus.model.File;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class FileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText disease;
    private EditText descriptionField;
    private EditText treatmentField;
    private Spinner fileType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        disease = findViewById(R.id.file_disease);
        descriptionField = findViewById(R.id.file_description);
        treatmentField = findViewById(R.id.file_treatment);
        fileType = findViewById(R.id.file_type_spinner);

        //Spinner to choose fiche type
        Spinner spinner = findViewById(R.id.file_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.file_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Add fiche
        Button addFicheButton = findViewById(R.id.button_add_file);
        addFicheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFiche();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String SelectedfileType = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addFiche(){
        String maladieFiche = disease.getText().toString();
        String descriptionFieldFiche =  descriptionField.getText().toString();
        String traitemenfiche = treatmentField.getText().toString();
        String typeFiche = fileType.getSelectedItem().toString();

        String patient_name = getIntent().getStringExtra("patient_name");
        String patient_email = getIntent().getStringExtra("patient_email");


        CollectionReference ficheRef = FirebaseFirestore.getInstance().collection("Patient").document(""+patient_email+"")
                .collection("MyMedicalFolder");
        ficheRef.document().set(new File(maladieFiche, descriptionFieldFiche, traitemenfiche, typeFiche, FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()));
        //ficheRef.add(new Fiche(maladieFiche, descriptionFieldFiche, traitemenfiche, typeFiche, FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()));
        Toast.makeText(this, "Fisa adaugata pentru "+patient_name, Toast.LENGTH_LONG).show();
        finish();
    }

}
