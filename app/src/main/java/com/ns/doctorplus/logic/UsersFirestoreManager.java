package com.ns.doctorplus.logic;

import static com.ns.doctorplus.logic.UsersFirestoreDbContract.COLLECTION_NAME;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_ADDRESS;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_BIRTH_DATE;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_CNP;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_EMAIL;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_FIRST_NAME;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_LAST_NAME;
import static com.ns.doctorplus.logic.UsersFirestoreDbContract.FIELD_PASSWORD;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ns.doctorplus.model.User;

import java.util.Date;

public class UsersFirestoreManager {
    private static UsersFirestoreManager usersFirestoreManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference usersCollectionReference;

    private UsersFirestoreManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        usersCollectionReference = firebaseFirestore.collection(COLLECTION_NAME);
    }
    public static UsersFirestoreManager newInstance() {
        if (usersFirestoreManager == null) {
            usersFirestoreManager = new UsersFirestoreManager();
        }
        return usersFirestoreManager;
    }

    public void createDocument(User user) {
        usersCollectionReference.add(user);
    }

    public void getAllUsers(OnCompleteListener<QuerySnapshot> onCompleteListener)
    {
        usersCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateUser(User user) {
        String documentId = user.getDocumentId();
        DocumentReference documentReference = usersCollectionReference.document(documentId);
        documentReference.set(user);
    }

    public void deleteUser(String documentId) {
        DocumentReference documentReference = usersCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk() {

        // Create a new Contact document map of values and add it to the collection
        createDocument(new User("502", "Jack", "Miller", "jmiller@gmail.com", "str dorna", new Timestamp(new Date()), "parola"));

        // Create a new Contact document map of values and add it to the collection
        createDocument(new User("102", "Michael", "Johnson", "m_johnson@gmail.com", "str a", new Timestamp(new Date()), "parola"));

        // Create a new Contact document map of values and add it to the collection
        createDocument(new User("20", "Chris", "Stanley", "chrisstnl@gmail.com", "strada b", new Timestamp(new Date()), "parola"));
    }

    public void getUser(String cnp, String password){

        usersCollectionReference.whereEqualTo(FIELD_CNP, cnp).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    User user = new User((String) documentSnapshot.get(FIELD_CNP),
                            (String) documentSnapshot.get(FIELD_FIRST_NAME),
                            (String) documentSnapshot.get(FIELD_LAST_NAME),
                            (String) documentSnapshot.get(FIELD_EMAIL),
                            (String) documentSnapshot.get(FIELD_ADDRESS),
                            (Timestamp) documentSnapshot.get(FIELD_BIRTH_DATE),
                            (String) documentSnapshot.get(FIELD_PASSWORD));
                    user.setDocumentId(documentSnapshot.getId());
                    Log.i("Snapshot", user.toString());
                }
            }
        });
    }

}
