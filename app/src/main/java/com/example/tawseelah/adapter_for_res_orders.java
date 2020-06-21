package com.example.tawseelah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adapter_for_res_orders extends RecyclerView.Adapter<adapter_for_res_orders.myviewholder> {
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private Context context;
    private ArrayList<get_all_orders_all_users> get_all_orders_all_users;
    private get_driver_info_from_data_dase get_driver_info_from_data_dase;

    public adapter_for_res_orders(Context context, ArrayList<com.example.tawseelah.get_all_orders_all_users> get_all_orders_all_users) {
        this.context = context;
        this.get_all_orders_all_users = get_all_orders_all_users;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_for_resturant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.res_name11.setText(get_all_orders_all_users.get(position).getRes_name());

        holder.cus_name11.setText(get_all_orders_all_users.get(position).getCoust_name());

        holder.meal_name11.setText(get_all_orders_all_users.get(position).getMeal_name());

        holder.meal_price11.setText(String.valueOf(get_all_orders_all_users.get(position).getMeal_price()));

        holder.from11.setText(get_all_orders_all_users.get(position).getStart_point());

        holder.to11.setText(get_all_orders_all_users.get(position).getEnd_point());

        holder.bill11.setText(get_all_orders_all_users.get(position).getDilivary_cost());

        holder.cus_phone11.setText(get_all_orders_all_users.get(position).getCus_phone_num());


        final String cust_name = get_all_orders_all_users.get(position).getCoust_name();

        final String meal_name = get_all_orders_all_users.get(position).getMeal_name();

        get_driver_info_from_data_dase = new get_driver_info_from_data_dase();

        final String driver_uid = get_driver_info_from_data_dase.getUid_driver();

        holder.cancel11.setClickable(true);
        final String a = get_all_orders_all_users.get(position).getKey();


        holder.cancel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               databaseReference = FirebaseDatabase.getInstance().getReference();
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
               databaseReference.child("Requests").child(firebaseUser.getUid()).child(a).child("meal_states").setValue("cancel");
               databaseReference.child("all_Requests").child(a).child("meal_states").setValue("cancel");


                Snackbar.make(view, "you have canceled the order for customer: " + cust_name + " meal name:  " + meal_name, Snackbar.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return get_all_orders_all_users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView res_name11, cus_name11, cus_phone11, meal_name11, meal_price11, from11, to11, bill11;
        Button cancel11;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            res_name11 = itemView.findViewById(R.id.res_name11);
            cus_name11 = itemView.findViewById(R.id.cus_name11);
            cus_phone11 = itemView.findViewById(R.id.cus_phone11);
            meal_name11 = itemView.findViewById(R.id.meal_name11);
            meal_price11 = itemView.findViewById(R.id.meal_price11);
            from11 = itemView.findViewById(R.id.from11);
            to11 = itemView.findViewById(R.id.to11);
            bill11 = itemView.findViewById(R.id.bill11);
            cancel11 = itemView.findViewById(R.id.cancel11);


        }
    }
}
