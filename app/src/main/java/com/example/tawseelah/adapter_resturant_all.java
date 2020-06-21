package com.example.tawseelah;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_resturant_all extends RecyclerView.Adapter<adapter_resturant_all.view_holder> {
    ArrayList<get_data_res> getdata;
    Context context;

    public adapter_resturant_all(ArrayList<get_data_res> getdata) {
        this.getdata = getdata;
    }


    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_handler, parent, false);

        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.firstname2.setText("first name: " + getdata.get(position).getFirstname2());
        holder.secondname2.setText("second name: " + getdata.get(position).getSecondname2());
        holder.restaurantname2.setText("restaurant  name: " + getdata.get(position).getRestaurantname2());
        holder.restaurantplace2.setText("restaurant place: " + getdata.get(position).getRestaurantplace2());
        holder.res_phone1.setText("restaurant phone number: " + getdata.get(position).getPhonenumber());
        final String longitude=getdata.get(position).getLoc_longitude();
        final String lontitude=getdata.get(position).getLoc_latitude();
        holder.loc_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(context,MapsActivity_for_admin.class);
                intent.putExtra("longitude",longitude);
                intent.putExtra("latitude",lontitude);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return getdata.size();
    }

    class view_holder extends RecyclerView.ViewHolder {
        TextView firstname2, secondname2, restaurantname2, restaurantplace2, res_phone1;
        Button loc_res;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            firstname2 = itemView.findViewById(R.id.first_name1);
            secondname2 = itemView.findViewById(R.id.second_name1);
            restaurantname2 = itemView.findViewById(R.id.res_name1);
            restaurantplace2 = itemView.findViewById(R.id.res_place1);
            res_phone1 = itemView.findViewById(R.id.res_phone1);
            loc_res = itemView.findViewById(R.id.loc_res);
            context = itemView.getContext();
        }
    }

}