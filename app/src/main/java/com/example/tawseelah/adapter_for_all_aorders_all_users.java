package com.example.tawseelah;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class adapter_for_all_aorders_all_users extends RecyclerView.Adapter<adapter_for_all_aorders_all_users.myviewholder> {
    private Context context;
    private ArrayList<get_all_orders_all_users> get_all_orders_all_users;



    public adapter_for_all_aorders_all_users(Context context, ArrayList<com.example.tawseelah.get_all_orders_all_users> get_all_orders_all_users) {
        this.context = context;
        this.get_all_orders_all_users = get_all_orders_all_users;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_on_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder holder, int position) {

        holder.res_name.setText(get_all_orders_all_users.get(position).getRes_name());

        holder.cus_name.setText(get_all_orders_all_users.get(position).getCoust_name());

        holder.meal_name.setText(get_all_orders_all_users.get(position).getMeal_name());

        holder.meal_price.setText(String.valueOf(get_all_orders_all_users.get(position).getMeal_price()));

        holder.from.setText(get_all_orders_all_users.get(position).getStart_point());

        holder.to.setText(get_all_orders_all_users.get(position).getEnd_point());

        holder.bill.setText(get_all_orders_all_users.get(position).getDilivary_cost());

        final String res_name = get_all_orders_all_users.get(position).getRes_name();

        final String cust_name = get_all_orders_all_users.get(position).getCoust_name();

        final String meal_name = get_all_orders_all_users.get(position).getMeal_name();

        final String meal_price = get_all_orders_all_users.get(position).getMeal_price();

        final String from = get_all_orders_all_users.get(position).getStart_point();

        final String to = get_all_orders_all_users.get(position).getEnd_point();

        final String bill = get_all_orders_all_users.get(position).getDilivary_cost();

        final String cust_phone = get_all_orders_all_users.get(position).getCus_phone_num();
        final String key = get_all_orders_all_users.get(position).getKey();

        final String uid = get_all_orders_all_users.get(position).getUid();

        final String longitude = get_all_orders_all_users.get(position).getLoc_longitude();

        final String latitude = get_all_orders_all_users.get(position).getLoc_latitude();

        holder.order_confirm.setClickable(true);
        holder.order_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, view_order.class);
                intent.putExtra("res_name_data", res_name);
                intent.putExtra("cust_name_data", cust_name);
                intent.putExtra("meal_name_data", meal_name);
                intent.putExtra("meal_price_data", meal_price);
                intent.putExtra("from_data", from);
                intent.putExtra("to_data", to);
                intent.putExtra("bill_data", bill);
                intent.putExtra("cust_phone_data", cust_phone);
                intent.putExtra("key", key);
                intent.putExtra("uid", uid);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                Toast.makeText(context, "Confirmation order", Toast.LENGTH_LONG).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return get_all_orders_all_users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView res_name, cus_name, cus_phone, meal_name, meal_price, from, to, bill;
        private ConstraintLayout order_confirm;


        public myviewholder(@NonNull final View itemView) {
            super(itemView);
            res_name = itemView.findViewById(R.id.res_name);
            cus_name = itemView.findViewById(R.id.cus_name);
            cus_phone = itemView.findViewById(R.id.cus_phone);
            meal_name = itemView.findViewById(R.id.meal_name);
            meal_price = itemView.findViewById(R.id.meal_price);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            bill = itemView.findViewById(R.id.bill);
            order_confirm = itemView.findViewById(R.id.order_confirm);


        }


    }


}
