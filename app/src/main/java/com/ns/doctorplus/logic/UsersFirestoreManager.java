package com.ns.doctorplus.logic;

import static com.ns.doctorplus.logic.UsersFirestoreDbContract.COLLECTION_NAME;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
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
}
