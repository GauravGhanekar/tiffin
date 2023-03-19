package com.example.tiffinservice.messFoodPanal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.tiffinservice.R;
import com.example.tiffinservice.mess_postDish;

public class MessProfileFragment extends Fragment {
    Button postDish;
    ConstraintLayout backimg;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mess_profile,null);
        getActivity().setTitle("Post Dish");

        postDish =(Button) v.findViewById(R.id.post_dish);

        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), mess_postDish.class));
            }
        });
        return v;
    }
}
