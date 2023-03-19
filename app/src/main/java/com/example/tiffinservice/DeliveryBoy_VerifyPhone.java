package com.example.tiffinservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

public class DeliveryBoy_VerifyPhone extends AppCompatActivity {
    String verificationId;
    FirebaseAuth FAuth;
    Button verify, Resend;
    TextView txt;
    EditText entercode;
    String phoneno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy_verify_phone);


        phoneno = getIntent().getStringExtra("phonenumber").trim();

        entercode = (EditText) findViewById(R.id.Dcode);
        txt = (TextView) findViewById(R.id.textt);
        Resend = (Button) findViewById(R.id.Resendcode);
        verify = (Button) findViewById(R.id.Verifycode);
        FAuth = FirebaseAuth.getInstance();

        Resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

//        sendverificationcode(phoneno);
    }
}