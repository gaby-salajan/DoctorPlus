package com.ns.doctorplus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.ns.doctorplus.model.File;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText disease;
    private EditText descriptionField;
    private EditText treatmentField;
    String doctor_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        FirebaseFirestore.getInstance().collection("Doctor").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                doctor_name = documentSnapshot.getString("name");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseFirestore.getInstance().collection("Asistent").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        doctor_name = documentSnapshot.getString("name");
                    }
                });
            }
        });

        disease = findViewById(R.id.file_disease);
        descriptionField = findViewById(R.id.file_description);
        treatmentField = findViewById(R.id.file_treatment);

        //Add fiche
        Button addFicheButton = findViewById(R.id.button_add_file);
        addFicheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFile();
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

    private void addFile(){
        String diseaseFile = disease.getText().toString();
        String descriptionFile =  descriptionField.getText().toString();
        String treatmentFile = treatmentField.getText().toString();
        String fileType = "Consultatie";

        String patient_name = getIntent().getStringExtra("patient_name");
        String patient_email = getIntent().getStringExtra("patient_email");


        CollectionReference fileRef = FirebaseFirestore.getInstance().collection("Patient").document(""+patient_email+"")
                .collection("MyMedicalFolder");
        fileRef.document().set(new File(diseaseFile, descriptionFile, treatmentFile, fileType, doctor_name));
        //ficheRef.add(new Fiche(diseaseFile, descriptionFile, treatmentFile, fileType, FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()));

        Toast.makeText(this, "Fisa adaugata pentru " + patient_name, Toast.LENGTH_LONG).show();

        finish();
    }

}
