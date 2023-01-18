package com.ns.doctorplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ns.doctorplus.Common.Common;
import com.ns.doctorplus.model.Doctor;
import com.ns.doctorplus.model.User;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdminHomeActivity extends AppCompatActivity {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference adminRef = db.collection("Admin");
    private CollectionReference doctorRef = db.collection("Doctor");
    private CollectionReference patientRef = db.collection("Patient");
    private CollectionReference userRef = db.collection("User");

    static String admin;
    Button btnSearch;
    Button btnListAll;
    Button btnDeconnect;
    EditText emailText;
    Unbinder unbinder;
    AdminAdapterFiltred adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_admin_home);
        emailText = findViewById(R.id.editText2);
        btnSearch = findViewById(R.id.searchBtn);
        recyclerView = findViewById(R.id.searchUserRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnDeconnect = findViewById(R.id.signOutBtn);
        btnDeconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        setUpRecyclerView();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailText.getText().toString().equals("")){
                    Query query = userRef.orderBy("name", Query.Direction.ASCENDING);

                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            adapter = new AdminAdapterFiltred(task.getResult().toObjects(User.class));
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }else{
                    Query query = userRef.whereEqualTo("email", emailText.getText().toString());
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            adapter = new AdminAdapterFiltred(task.getResult().toObjects(User.class));
                            Log.i("admin", String.valueOf(adapter.getItemCount()));
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }

            }
        });

    }

    private void setUpRecyclerView() {
        Query query = userRef.orderBy("name", Query.Direction.ASCENDING);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapter = new AdminAdapterFiltred(task.getResult().toObjects(User.class));
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
