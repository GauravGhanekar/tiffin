package com.example.tiffinservice;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tiffinservice.messFoodPanal.MessHomeFragment;
import com.example.tiffinservice.messFoodPanal.MessOrdersFragment;
import com.example.tiffinservice.messFoodPanal.MessPendingOrderFragment;
import com.example.tiffinservice.messFoodPanal.MessProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MessFoodPanel_BottomNavigation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_food_panel_bottom_navigation);

        bottomNavigationView= findViewById(R.id.mess_bottom_navigation);


        String name = getIntent().getStringExtra("PAGE");
        if(name!=null){
            Toast.makeText(this, "entered", Toast.LENGTH_SHORT).show();

            if(name.equalsIgnoreCase("Orderpage")){
                loademessfragment(new MessPendingOrderFragment());
            }else if(name.equalsIgnoreCase("Confirmpage")){
                loademessfragment(new MessOrdersFragment());
            }else if(name.equalsIgnoreCase("AcceptOrderpage")){
                loademessfragment(new MessHomeFragment());
            }else if(name.equalsIgnoreCase("Deliveredpage")){
                loademessfragment(new MessHomeFragment());
            }
        }else{
            loademessfragment(new MessHomeFragment());
            Toast.makeText(this, "entered", Toast.LENGTH_SHORT).show();
        }


  //     replaceFragment(new Fragment_home());
//       bottomNavigationView.setSelectedItemId(R.id);

    }

    private void UpdateToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isComplete()){
                    String token = task.getResult();
                    FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
                }
            }
        });
    }


    private boolean loademessfragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

//    private void replaceFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
//    }
//
//    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //return false;
            Fragment fragment=null;
            switch (item.getItemId()){
                case R.id.messHome:
                    fragment=new MessHomeFragment();
                    break;
                case R.id.pendingOrders:
                    fragment=new MessPendingOrderFragment();
                    break;
                case R.id.Orders:
                    fragment=new MessOrdersFragment();
                    break;
                case R.id.messProfile:
                    fragment=new MessProfileFragment();
                    break;
            }
            return loademessfragment(fragment);
        }
}
