package com.example.tawseelah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_driver extends RecyclerView.Adapter<adapter_driver.myviewholder> {
    ArrayList<get_driver_info_from_data_dase> get_driver_info_from_data_dases;


    public adapter_driver(ArrayList<get_driver_info_from_data_dase> get_driver_info_from_data_dases) {
        this.get_driver_info_from_data_dases = get_driver_info_from_data_dases;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_all_drivers, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.first.setText(get_driver_info_from_data_dases.get(position).getFirstname());
        holder.second.setText(get_driver_info_from_data_dases.get(position).getSecondname());
        holder.type.setText(get_driver_info_from_data_dases.get(position).getCartype());
        holder.color.setText(get_driver_info_from_data_dases.get(position).getCarcolor());
        holder.phone.setText(String.valueOf(get_driver_info_from_data_dases.get(position).getPhonenumber()));

        holder.cash.setText(get_driver_info_from_data_dases.get(position).getCashdesired()+"   JD");
        holder.uid.setText(get_driver_info_from_data_dases.get(position).getUid_driver());
    }

    @Override
    public int getItemCount() {
        return get_driver_info_from_data_dases.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView first, second, type, color, phone, cash,uid;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            first = itemView.findViewById(R.id.first1);
            second = itemView.findViewById(R.id.second1);
            type = itemView.findViewById(R.id.type1);
            color = itemView.findViewById(R.id.color1);
            phone = itemView.findViewById(R.id.phone1);
            cash = itemView.findViewById(R.id.cash1);
            uid = itemView.findViewById(R.id.uid);
        }
    }

}
