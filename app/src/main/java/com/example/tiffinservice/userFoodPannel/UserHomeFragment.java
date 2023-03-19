package com.example.tiffinservice.userFoodPannel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tiffinservice.R;
import com.example.tiffinservice.UpdateDishModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    private List<UpdateDishModel>updateDishModelList;
    private UserHomeAdapter adapter;
    String State,City,Area;
    DatabaseReference dataa,databaseReference;
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_userhome,null);
        getActivity().setTitle("Home");

        setHasOptionsMenu(true);

        recyclerView =v.findViewById(R.id.recycle_menu);
        recyclerView.setHasFixedSize(true);
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.move);
        recyclerView.startAnimation(animation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.Swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimaryDark,R.color.Red);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dataa = FirebaseDatabase.getInstance().getReference("User").child(userid);
                dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        User userr = snapshot.getValue(User.class);
                        State = userr.getState();
                        Area = userr.getArea();
                        City = userr.getCity();
                        Usermenu();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



        return v;
    }

    @Override
    public void onRefresh() {
        Usermenu();


    }

    private void Usermenu() {
            swipeRefreshLayout.setRefreshing(true);
            databaseReference =FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    updateDishModelList.clear();
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        for(DataSnapshot snapshot2: snapshot1.getChildren()){
                            UpdateDishModel updateDishModel = snapshot2.getValue(UpdateDishModel.class);
                            updateDishModelList.add(updateDishModel);
                        }
                    }
                    adapter = new UserHomeAdapter(getContext(),updateDishModelList);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);

               }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    swipeRefreshLayout.setRefreshing(false);

                }
            });
    }
}


