package com.example.tawseelah;

import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class adapter_for_taken_orders_driver extends RecyclerView.Adapter<adapter_for_taken_orders_driver.myviewholder> {
    private ArrayList<get_taken_orders_data> get_taken_orders_data;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2;
    private FirebaseUser firebaseUser;
    private Context context;
    private String all_cash;
    private String num_of_taken_times;
    private double all_cash_1;
    private int num_of_all;

    public adapter_for_taken_orders_driver(ArrayList<com.example.tawseelah.get_taken_orders_data> get_taken_orders_data) {
        this.get_taken_orders_data = get_taken_orders_data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taken_order_for_driver, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder holder, int position) {
        holder.res_name11_taken_order.setText(get_taken_orders_data.get(position).getRes_name());
        holder.cus_name11_taken_order.setText(get_taken_orders_data.get(position).getCoust_name());
        holder.cus_phone11_taken_order.setText(get_taken_orders_data.get(position).getCus_phone_num());
        holder.meal_price11_taken_order.setText(get_taken_orders_data.get(position).getMeal_price());
        holder.meal_name11_taken_order.setText(get_taken_orders_data.get(position).getMeal_name());
        holder.from11_taken_order.setText(get_taken_orders_data.get(position).getStart_point());
        holder.to11_taken_order.setText(get_taken_orders_data.get(position).getEnd_point());
        holder.bill11_taken_order.setText(get_taken_orders_data.get(position).getDilivary_cost());
        holder.cash_desired_taken_order.setText(get_taken_orders_data.get(position).getCash_desierd());
        final String uid_taken_order_driver = get_taken_orders_data.get(position).getDriver_uid();
        final String key_taken_order_driver = get_taken_orders_data.get(position).getKey();
        final String key_res = get_taken_orders_data.get(position).getKey_res();
        final String uid_res = get_taken_orders_data.get(position).getUid_res();
        final String main_cash = get_taken_orders_data.get(position).getCash_desierd();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Driver").child(firebaseUser.getUid()).child("cashdesired");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                all_cash = String.valueOf(dataSnapshot.getValue());
                all_cash_1 = Double.parseDouble(all_cash);
                all_cash_1 = all_cash_1 - Double.parseDouble(main_cash);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Driver").child(firebaseUser.getUid()).child("num_of_taken_orders");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num_of_taken_times = String.valueOf(dataSnapshot.getValue());
                num_of_all = Integer.parseInt(num_of_taken_times);
                num_of_all = num_of_all - 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Objects.requireNonNull(holder).cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(key_taken_order_driver).setValue(null);
                databaseReference.child("Requests").child(uid_res).child(key_res).child("meal_states").setValue("waiting");
                databaseReference.child("all_Requests").child(key_res).child("meal_states").setValue("waiting");
                databaseReference.child("Driver").child(firebaseUser.getUid()).child("cashdesired").setValue(String.valueOf(all_cash_1));
                Snackbar.make(view, "Order Canceled", Snackbar.LENGTH_LONG).show();
                databaseReference.child("Driver").child(firebaseUser.getUid()).child("num_of_taken_orders").setValue(String.valueOf(num_of_all));

                Intent intent = new Intent(context, driver_main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);


            }
        });
        Objects.requireNonNull(holder).finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(key_taken_order_driver).setValue("finish");
                databaseReference.child("Requests").child(uid_res).child(key_res).child("meal_states").setValue("finish");
                databaseReference.child("all_Requests").child(key_res).child("meal_states").setValue("finish");
                databaseReference.child("Driver").child(firebaseUser.getUid()).child("num_of_taken_orders").setValue(String.valueOf(num_of_all));
                Intent intent = new Intent(context, driver_main.class);
                context.startActivity(intent);
                Snackbar.make(view, num_of_taken_times, Snackbar.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return get_taken_orders_data.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        private TextView res_name11_taken_order, cus_name11_taken_order, cus_phone11_taken_order, meal_price11_taken_order, meal_name11_taken_order, from11_taken_order, to11_taken_order, bill11_taken_order, cash_desired_taken_order;
        private Button cancel_order, finish;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            res_name11_taken_order = itemView.findViewById(R.id.res_name11_taken_order);
            cus_name11_taken_order = itemView.findViewById(R.id.cus_name11_taken_order);
            cus_phone11_taken_order = itemView.findViewById(R.id.cus_phone11_taken_order);
            meal_price11_taken_order = itemView.findViewById(R.id.meal_price11_taken_order);
            meal_name11_taken_order = itemView.findViewById(R.id.meal_name11_taken_order);
            from11_taken_order = itemView.findViewById(R.id.from11_taken_order);
            to11_taken_order = itemView.findViewById(R.id.to11_taken_order);
            bill11_taken_order = itemView.findViewById(R.id.bill11_taken_order);
            cash_desired_taken_order = itemView.findViewById(R.id.cash_desired_taken_order);
            cancel_order = itemView.findViewById(R.id.cancel11_taken_order);
            finish = itemView.findViewById(R.id.finish);
            context = itemView.getContext();
        }
    }
}
