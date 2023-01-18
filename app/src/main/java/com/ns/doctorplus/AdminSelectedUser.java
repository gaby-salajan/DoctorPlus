package com.ns.doctorplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ns.doctorplus.model.User;

import butterknife.Unbinder;

public class AdminSelectedUser extends AppCompatActivity {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference adminRef = db.collection("Admin");
    private CollectionReference asistantRef = db.collection("Asistent");
    private CollectionReference doctorRef = db.collection("Doctor");
    private CollectionReference patientRef = db.collection("Patient");
    private CollectionReference userRef = db.collection("User");

        static String admin;
        User user;
        String givenEmail;
        TextView textType;
        TextView textName;
        TextView textAddress;
        TextView textPhone;
        EditText fieldType;
        EditText fieldName;
        EditText fieldAddress;
        EditText fieldPhone;
        Button btnModify;

        @Override
        protected void onCreate(Bundle savedInstaceState) {


            super.onCreate(savedInstaceState);
            setContentView(R.layout.activity_admin_selected_user);
            givenEmail = getIntent().getExtras().get("email").toString();
            textType = findViewById(R.id.textType);
            textAddress = findViewById(R.id.textAddress);
            textName = findViewById(R.id.textName);
            textPhone = findViewById(R.id.textPhone);
            fieldAddress = findViewById(R.id.fieldAddress);
            fieldName = findViewById(R.id.fieldName);
            fieldType = findViewById(R.id.fieldType);
            fieldPhone = findViewById(R.id.fieldPhone);
            btnModify = findViewById(R.id.btnModify);

            Query query = userRef.whereEqualTo("email", givenEmail);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    user = task.getResult().toObjects(User.class).get(0);
                    textType.setText("Type : " + user.getType());
                    textAddress.setText("Address : " + user.getAddress());
                    textName.setText("Name : " + user.getName());
                    textPhone.setText("Phone : " + user.getTel());
                }
            });

            btnModify.setOnClickListener(new View.OnClickListener() {
                String text = fieldType.getText().toString();
                @Override
                public void onClick(View view) {

                    if(!fieldName.getText().toString().equals("")){
                        //modify the cellphone number in all the collections
                        userRef.document(givenEmail).update("name", fieldName.getText().toString());
                        adminRef.document(givenEmail).update("name", fieldName.getText().toString());
                        doctorRef.document(givenEmail).update("name", fieldName.getText().toString());
                        asistantRef.document(givenEmail).update("name", fieldName.getText().toString());
                        patientRef.document(givenEmail).update("name", fieldName.getText().toString());

                        //modify the Phone text
                        Query query = userRef.whereEqualTo("email", givenEmail);
                        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                user = task.getResult().toObjects(User.class).get(0);
                                textName.setText("Name : " + user.getName());
                            }
                        });
                    }

                    if(!fieldAddress.getText().toString().equals("")){
                        //modify the cellphone number in all the collections
                        userRef.document(givenEmail).update("address", fieldAddress.getText().toString());
                        adminRef.document(givenEmail).update("address", fieldAddress.getText().toString());
                        doctorRef.document(givenEmail).update("address", fieldAddress.getText().toString());
                        asistantRef.document(givenEmail).update("address", fieldAddress.getText().toString());
                        patientRef.document(givenEmail).update("address", fieldAddress.getText().toString());

                        //modify the Phone text
                        Query query = userRef.whereEqualTo("email", givenEmail);
                        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                user = task.getResult().toObjects(User.class).get(0);
                                textAddress.setText("Address : " + user.getAddress());
                            }
                        });
                    }

                    if(!fieldPhone.getText().toString().equals("")){
                        //modify the cellphone number in all the collections
                        userRef.document(givenEmail).update("tel", fieldPhone.getText().toString());
                        adminRef.document(givenEmail).update("tel", fieldPhone.getText().toString());
                        doctorRef.document(givenEmail).update("tel", fieldPhone.getText().toString());
                        asistantRef.document(givenEmail).update("tel", fieldPhone.getText().toString());
                        patientRef.document(givenEmail).update("tel", fieldPhone.getText().toString());

                        //modify the Phone text
                        Query query = userRef.whereEqualTo("email", givenEmail);
                        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                user = task.getResult().toObjects(User.class).get(0);
                                textPhone.setText("Phone : " + user.getTel());
                            }
                        });
                    }

                    //field type
                    if(fieldType.getText().toString().equals("Doctor") ||
                       fieldType.getText().toString().equals("Patient") ||
                       fieldType.getText().toString().equals("Admin") ||
                       fieldType.getText().toString().equals("Asistent")){

                        if(fieldType.getText().toString().equals("Doctor")){
                            //modify the tyoe in user
                            userRef.document(givenEmail).update("type", fieldType.getText().toString());

                            //delete any instance in any other collection
                            adminRef.document(givenEmail).delete();
                            asistantRef.document(givenEmail).delete();
                            patientRef.document(givenEmail).delete();

                            //add the new user into the Doctor collection
                            Query query = userRef.whereEqualTo("email", givenEmail);
                            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    user = task.getResult().toObjects(User.class).get(0);
                                    doctorRef.document(givenEmail).set(user);
                                    textType.setText("Type : " + user.getType());
                                }
                            });
                        }

                        if(fieldType.getText().toString().equals("Asistent")){
                            //modify the tyoe in user
                            userRef.document(givenEmail).update("type", fieldType.getText().toString());

                            //delete any instance in any other collection
                            adminRef.document(givenEmail).delete();
                            patientRef.document(givenEmail).delete();
                            doctorRef.document(givenEmail).delete();

                            //add the new user into the Doctor collection
                            Query query = userRef.whereEqualTo("email", givenEmail);
                            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    user = task.getResult().toObjects(User.class).get(0);
                                    asistantRef.document(givenEmail).set(user);
                                    textType.setText("Type : " + user.getType());
                                }
                            });
                        }

                        if(fieldType.getText().toString().equals("Patient")){
                            //modify the tyoe in user
                            userRef.document(givenEmail).update("type", fieldType.getText().toString());

                            //delete any instance in any other collection
                            adminRef.document(givenEmail).delete();
                            asistantRef.document(givenEmail).delete();
                            doctorRef.document(givenEmail).delete();

                            //add the new user into the Doctor collection
                            Query query = userRef.whereEqualTo("email", givenEmail);
                            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    user = task.getResult().toObjects(User.class).get(0);
                                    patientRef.document(givenEmail).set(user);
                                    textType.setText("Type : " + user.getType());
                                }
                            });
                        }

                        if(fieldType.getText().toString().equals("Admin")){
                            //modify the tyoe in user
                            userRef.document(givenEmail).update("type", fieldType.getText().toString());

                            //delete any instance in any other collection
                            asistantRef.document(givenEmail).delete();
                            patientRef.document(givenEmail).delete();
                            doctorRef.document(givenEmail).delete();

                            //add the new user into the Doctor collection
                            Query query = userRef.whereEqualTo("email", givenEmail);
                            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    user = task.getResult().toObjects(User.class).get(0);
                                    adminRef.document(givenEmail).set(user);
                                    textType.setText("Type : " + user.getType());
                                }
                            });
                        }

                    }else{
                        if(!fieldType.getText().toString().equals("")){
                            Toast.makeText(AdminSelectedUser.this, "Introduceti un tip corect !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
}
