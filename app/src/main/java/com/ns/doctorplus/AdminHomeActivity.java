package com.ns.doctorplus;

import android.content.Intent;
import android.os.Bundle;
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
    static String admin;
    Button btnSearch;
    Button btnListAll;
    Button btnDeconnect;
    EditText emailText;
    Unbinder unbinder;
    AdminAdapterFiltred adapter;

    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_admin_home);
        btnSearch = findViewById(R.id.searchBtn);
        btnListAll = findViewById(R.id.listAllBtn);
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

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.serachPatRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = adminRef.orderBy("name", Query.Direction.DESCENDING);


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapter = new AdminAdapterFiltred(task.getResult().toObjects(User.class));
                recyclerView.setAdapter(adapter);
            }
        });
        //FirestoreRecyclerOptions<Doctor> options = new FirestoreRecyclerOptions.Builder<Doctor>()
        //  .setQuery(query, Doctor.class)
        //  .build();

        //adapter = new DoctoreAdapter(options);


    }
}
