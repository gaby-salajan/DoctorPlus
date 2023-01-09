package com.ns.doctorplus.fireStoreApi;

import com.google.firebase.Timestamp;
import com.ns.doctorplus.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference UsersRef = db.collection("User");

    public static void addUser(String cnp, String fullName, String birthDate, String tel, String address, String type){
        User user = new User(cnp, fullName, birthDate, tel, address, FirebaseAuth.getInstance().getCurrentUser().getEmail(),type);
        UsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(user);
    }
}
