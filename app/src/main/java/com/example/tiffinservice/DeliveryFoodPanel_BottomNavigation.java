package com.example.tiffinservice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tiffinservice.deliveryFoodPannel.DeliveryPendingOrderFragment;
import com.example.tiffinservice.deliveryFoodPannel.DeliveryShipOrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryFoodPanel_BottomNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_food_panel_bottom_navigation);

        bottomNavigationView = findViewById(R.id.delivery_bottom_navigation);


        BottomNavigationView bottomNavigationView = findViewById(R.id.delivery_bottom_navigation);

        String name = getIntent().getStringExtra("PAGE");

        if (name != null) {
            if (name.equalsIgnoreCase("DeliveryOrderpage")) {
                loaddeliveryfragment(new DeliveryPendingOrderFragment());
            }
        } else {
            loaddeliveryfragment(new DeliveryPendingOrderFragment());
        }


    }

    //      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//        @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //return false;
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.ship_orders:
                fragment = new DeliveryShipOrderFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.pendingOrders:
                fragment = new DeliveryPendingOrderFragment();
                break;
        }
        return loaddeliveryfragment(fragment);
    }






    private boolean loaddeliveryfragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerbott, fragment).commit();
            return true;
        }
        return false;
    }

}
