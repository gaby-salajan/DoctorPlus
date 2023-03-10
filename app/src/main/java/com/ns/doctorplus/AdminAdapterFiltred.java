package com.ns.doctorplus;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ns.doctorplus.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapterFiltred  extends RecyclerView.Adapter<AdminAdapterFiltred.AdminHolder2> implements Filterable {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference addRequest = db.collection("Request");
    private List<User> mTubeList;
    private List<User> mTubeListFiltered;


    public AdminAdapterFiltred(List<User> tubeList){
        mTubeList = tubeList;
        mTubeListFiltered = tubeList;
    }

    @NonNull
    @Override
    public AdminHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item, parent, false);
        return new AdminHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolder2 adminHolder, int i) {
        final User user = mTubeListFiltered.get(i);
        final TextView name = adminHolder.name ;
        adminHolder.name.setText(user.getName());
        adminHolder.role.setText("Rol : "+ user.getType());
        adminHolder.email.setText("Email : "+ user.getEmail());
        adminHolder.cnp.setText("CNP : "+ user.getCnp());

        final String idPat = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        final String mail = user.getEmail();

        adminHolder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminSelectedUser.class);
                intent.putExtra("email", user.getEmail());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTubeListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String pattern = constraint.toString().toLowerCase();
                if(pattern.isEmpty()){
                    mTubeListFiltered = mTubeList;
                } else {
                    List<User> filteredList = new ArrayList<>();
                    for(User tube: mTubeList){
                        if (tube.getName().toLowerCase().contains(pattern) || tube.getName().toLowerCase().contains(pattern)) {
                            filteredList.add(tube);
                        }
                    }
                    mTubeListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mTubeListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mTubeListFiltered = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    class AdminHolder2 extends RecyclerView.ViewHolder {

        Button selectBtn;
        TextView name;
        TextView role;
        TextView email;
        TextView cnp;

        public AdminHolder2(@NonNull View itemView) {
            super(itemView);
            selectBtn = itemView.findViewById(R.id.selectBtn);
            name = itemView.findViewById(R.id.admin_view_name);
            role = itemView.findViewById(R.id.admin_view_role);
            email = itemView.findViewById(R.id.admin_view_mail);
            cnp = itemView.findViewById(R.id.admin_view_cnp);
        }
    }
    private void openPage(Context wf){
        Intent i = new Intent(wf, TestActivity.class);
        wf.startActivity(i);
    }
}
