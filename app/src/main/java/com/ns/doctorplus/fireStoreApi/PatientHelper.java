package com.ns.doctorplus.fireStoreApi;

import com.google.firebase.Timestamp;
import com.ns.doctorplus.model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference PatientRef = db.collection("Patient");

    public static void addPatient(String cnp, String fullName, Timestamp birthDate, String tel, String address){
        Patient patient = new Patient(cnp, fullName, birthDate, tel, address, FirebaseAuth.getInstance().getCurrentUser().getEmail());
        System.out.println("Create object patient");
        PatientRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(patient);
    }
}
