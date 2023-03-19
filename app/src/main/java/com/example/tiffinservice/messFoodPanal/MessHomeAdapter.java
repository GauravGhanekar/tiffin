package com.example.tiffinservice.messFoodPanal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiffinservice.R;
import com.example.tiffinservice.UpdateDishModel;

import java.util.List;

public class MessHomeAdapter extends RecyclerView.Adapter<MessHomeAdapter.ViewHolder> {

    private Context mcont;
    private List<UpdateDishModel> updateDishModelList;

    public MessHomeAdapter(Context context, List<UpdateDishModel>updateDishModelList){
        this.updateDishModelList = updateDishModelList;
        this.mcont = context;

    }
    @NonNull
    @Override
    public MessHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.mess_menu_update_delete,parent,false);
        return new MessHomeAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessHomeAdapter.ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel = updateDishModelList.get(position);
        holder.dishes.setText(updateDishModel.getDishesh());
        updateDishModel.getRandomUID();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcont,UpdateDelete_Dish.class);
                intent.putExtra("updatedeleteddish",updateDishModel.getRandomUID());
                mcont.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {

        return updateDishModelList.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dishes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishes = itemView.findViewById(R.id.dish_name);

        }
    }
}
