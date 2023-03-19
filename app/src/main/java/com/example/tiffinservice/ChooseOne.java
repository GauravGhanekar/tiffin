package com.example.tiffinservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {

    Button Mess_Admin,User,Delivery_Boy;
    Intent intent;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        Mess_Admin = (Button)findViewById(R.id.Mess_Admin);
        User = (Button)findViewById(R.id.User);
        Delivery_Boy = (Button)findViewById(R.id.Delivery_Boy);

        Mess_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail  = new Intent(ChooseOne.this,Messlogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Messloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("Signup")){
                    Intent Register  = new Intent(ChooseOne.this,MessRegistration.class);
                    startActivity(Register);
                }
            }
        });

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemailcust  = new Intent(ChooseOne.this,UserLogin.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphonecust  = new Intent(ChooseOne.this,UserLoginphone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if(type.equals("Signup")){
                    Intent Registercust  = new Intent(ChooseOne.this,UserRegistration.class);
                    startActivity(Registercust);

                }
            }
        });

        Delivery_Boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,DeliveryBoy_Login.class);
                    startActivity(loginemail);
                    finish();
                }
                else if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,DeliveryBoy_Loginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                else if(type.equals("Signup")){
                    Intent Registerdelivery  = new Intent(ChooseOne.this,DeliveryBoy_Registration.class);
                    startActivity(Registerdelivery);
                }
            }
        });





    }
}