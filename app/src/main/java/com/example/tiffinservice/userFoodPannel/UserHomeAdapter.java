package com.example.tiffinservice.userFoodPannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiffinservice.R;
import com.example.tiffinservice.UpdateDishModel;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class UserHomeAdapter extends RecyclerView.Adapter <UserHomeAdapter.ViewHolder>{

    private Context mcontext;
    private List<UpdateDishModel> updateDishModelslist;
    DatabaseReference databaseReference;

    public UserHomeAdapter(Context context,List<UpdateDishModel>updateDishModelslist){

        this.updateDishModelslist =updateDishModelslist;
        this.mcontext = context;




    }


    @NonNull
    @Override
    public UserHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.user_menudish,parent,false);
        return new UserHomeAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserHomeAdapter.ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel = updateDishModelslist.get(position);
        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getPrice());
        updateDishModel.getRandomUID();
        updateDishModel.getMessId();
        holder.Price.setText("Price:"+updateDishModel.getPrice()+"Rs");

    }

    @Override
    public int getItemCount() {
        return updateDishModelslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView Dishname,Price;

       public  ViewHolder(@NonNull View itemView) {
           super(itemView);

           imageView = itemView.findViewById(R.id.menu_image);
           Dishname =itemView.findViewById(R.id.dishname);
           Price = itemView.findViewById(R.id.dishprice);

       }

    }
}
