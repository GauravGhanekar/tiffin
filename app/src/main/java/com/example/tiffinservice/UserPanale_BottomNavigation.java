package com.example.tiffinservice;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tiffinservice.userFoodPannel.UserCartFragment;
import com.example.tiffinservice.userFoodPannel.UserHomeFragment;
import com.example.tiffinservice.userFoodPannel.UserOrdersFragment;
import com.example.tiffinservice.userFoodPannel.UserProfileFragment;
import com.example.tiffinservice.userFoodPannel.UserTrackFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserPanale_BottomNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panale_bottom_navigation);

        bottomNavigationView= findViewById(R.id.User_bottom_navigation);



       //BottomNavigationView navigationView = findViewById(R.id.User_bottom_navigation);
//        navigationView.setOnNavigationItemSelectedListener(this);

//            String name = getIntent().getStringExtra("PAGE");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if(name != null){
//            if(name.equalsIgnoreCase("Homepage")){
//                loadFragment(new UserHomeFragment());
//            }else if(name.equalsIgnoreCase("Preparingpage")){
//                loadFragment(new UserTrackFragment());
//            }else if(name.equalsIgnoreCase("DeliveryOrderpage")){
//                loadFragment(new UserTrackFragment());
//            }else if(name.equalsIgnoreCase("Thankyoupage")){
//                loadFragment(new UserHomeFragment());
//            }
//        }else{
//            loadFragment(new UserHomeFragment());
//        }
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //return false;
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.user_Home:
                        fragment = new UserHomeFragment();
                        break;

                }
                switch (item.getItemId()) {
                    case R.id.user_Cart:
                        fragment = new UserCartFragment();
                        break;

                }
                switch (item.getItemId()) {
                    case R.id.user_Profile:
                        fragment = new UserProfileFragment();
                        break;

                }
                switch (item.getItemId()) {
                    case R.id.user_Orders:
                        fragment = new UserOrdersFragment();
                        break;

                }
                switch (item.getItemId()) {
                    case R.id.user_Track:
                        fragment = new UserTrackFragment();
                        break;

                }
                return loadFragment(fragment);
            }

        });
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

}