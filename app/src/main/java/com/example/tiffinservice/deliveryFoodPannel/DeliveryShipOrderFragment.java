package com.example.tiffinservice.deliveryFoodPannel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tiffinservice.R;

public class DeliveryShipOrderFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.fragment_delivery_ship_order,null);
            getActivity().setTitle("Ship Order");
            return v;
        }
    }
