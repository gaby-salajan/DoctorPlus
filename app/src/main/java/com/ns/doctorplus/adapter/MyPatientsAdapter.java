package com.ns.doctorplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ns.doctorplus.ChatActivity;
import com.ns.doctorplus.EditProfileDoctorActivity;
import com.ns.doctorplus.MedicalFolder;
import com.ns.doctorplus.R;
import com.ns.doctorplus.model.Patient;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPatientsAdapter extends FirestoreRecyclerAdapter<Patient, MyPatientsAdapter.MyPatientsHolder> {
    StorageReference pathReference ;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.
     * @param options
     */
    public MyPatientsAdapter(@NonNull FirestoreRecyclerOptions<Patient> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull final MyPatientsHolder myPatientsHolder, int position, @NonNull final Patient patient) {
        myPatientsHolder.textViewTitle.setText(patient.getName());
        myPatientsHolder.textViewTelephone.setText("Telefon : "+patient.getTel());
        myPatientsHolder.contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(v.getContext(),patient);
            }
        });

        myPatientsHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientMedicalFolder(v.getContext(),patient);

            }
        });
        myPatientsHolder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(myPatientsHolder.contactButton.getContext(),patient.getTel());
            }
        });

        String imageId = patient.getEmail()+".jpg"; //add a title image
        pathReference = FirebaseStorage.getInstance().getReference().child("UserProfile/"+ imageId); //storage the image
        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(myPatientsHolder.imageViewPatient.getContext())
                        .load(uri)
                        .centerCrop()
                        .into(myPatientsHolder.imageViewPatient);

                // profileImage.setImageURI(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                myPatientsHolder.imageViewPatient.setImageDrawable(myPatientsHolder.resources.getDrawable(R.drawable.ic_person));
            }
        });


    }
    private void openPage(Context wf, String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        wf.startActivity(intent);
    }

    private void openPatientMedicalFolder(Context medicalFolder, Patient patient){
        Intent intent = new Intent(medicalFolder, MedicalFolder.class);
        intent.putExtra("patient_name", patient.getName());
        intent.putExtra("patient_email",patient.getEmail());
        intent.putExtra("patient_phone", patient.getTel());
        medicalFolder.startActivity(intent);
    }

    private void openPage(Context wf,Patient p){
        Intent i = new Intent(wf, ChatActivity.class);
        i.putExtra("key1",p.getEmail()+"_"+ FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        i.putExtra("key2",FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()+"_"+p.getEmail());
        wf.startActivity(i);
    }
    @NonNull
    @Override
    public MyPatientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_patient_item, parent, false);
        return new MyPatientsHolder(v);
    }

    class MyPatientsHolder extends RecyclerView.ViewHolder{
        //Here we hold the MyDoctorItems
        Button callBtn;
        TextView textViewTitle;
        TextView textViewTelephone;
        ImageView imageViewPatient;
        Button contactButton;
        RelativeLayout parentLayout;

        Resources resources;
        public MyPatientsHolder(@NonNull View itemView) {
            super(itemView);
            callBtn = itemView.findViewById(R.id.callBtn);
            textViewTitle = itemView.findViewById(R.id.patient_view_title);
            textViewTelephone = itemView.findViewById(R.id.text_view_telephone);
            imageViewPatient = itemView.findViewById(R.id.patient_item_image);
            contactButton = itemView.findViewById(R.id.contact);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            resources = itemView.getResources();
        }
    }




}
