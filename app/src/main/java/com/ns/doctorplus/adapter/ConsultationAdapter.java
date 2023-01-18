package com.ns.doctorplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ns.doctorplus.PrescriptionInfo;
import com.ns.doctorplus.R;
import com.ns.doctorplus.model.File;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConsultationAdapter  extends FirestoreRecyclerAdapter<File, ConsultationAdapter.FileHolder>{

    public ConsultationAdapter(@NonNull FirestoreRecyclerOptions<File> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FileHolder holder, int position, @NonNull final File model) {
        FirebaseFirestore.getInstance().collection("Doctor").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    if(queryDocumentSnapshot.get("name").equals(model.getDoctor())){
                        holder.doctor_name.setText(queryDocumentSnapshot.get("name").toString());
                    }
                }

            }
        });
        /*FirebaseFirestore.getInstance().document("Doctor/" + model.getDoctor()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                holder.doctor_name.setText(documentSnapshot.getString("name"));
            }
        });*/
        holder.type.setText(model.getType());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(v.getContext(), model);
            }
        });
        String[] date;
        if (model.getDateCreated() != null) {

            date = model.getDateCreated().toString().split(" ");
            // Thu Jun 04 14:46:12 GMT+01:00 2020
            holder.appointement_day_name.setText(date[0]);
            holder.appointement_day.setText(date[2]);
            holder.appointement_month.setText(date[1]);
            holder.doctor_view_title.setText(date[3]);
        }
    }

    private void openPage(Context wf, File m){
        Intent i = new Intent(wf, PrescriptionInfo.class);
        i.putExtra("dateCreated", m.getDateCreated().toString());
        i.putExtra("doctor", m.getDoctor());
        i.putExtra("description", m.getDescription());
        i.putExtra("disease", m.getDisease());
        i.putExtra("treatment", m.getTreatment());
        wf.startActivity(i);
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultation_item,
                parent, false);
        return new FileHolder(v);
    }
    class FileHolder extends RecyclerView.ViewHolder {
        TextView doctor_name;
        TextView type;
        Button btn;
        TextView appointement_month;
        TextView appointement_day;
        TextView appointement_day_name;
        TextView doctor_view_title;

        public FileHolder(View itemView) {
            super(itemView);
            doctor_name = itemView.findViewById(R.id.doctor_name);
            type = itemView.findViewById(R.id.text_view_description);
            btn = itemView.findViewById(R.id.view_file_btn);
            appointement_month = itemView.findViewById(R.id.appointement_month);
            appointement_day = itemView.findViewById(R.id.appointement_day);
            appointement_day_name = itemView.findViewById(R.id.appointement_day_name);
            doctor_view_title = itemView.findViewById(R.id.doctor_view_title);
        }
    }
}
