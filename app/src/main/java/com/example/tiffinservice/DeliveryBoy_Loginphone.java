package com.example.tiffinservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class DeliveryBoy_Loginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinmail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy_loginphone);

        num = (EditText)findViewById(R.id.Dphonenumber);
        sendotp = (Button)findViewById(R.id.Sendotp);
        cpp = (CountryCodePicker)findViewById(R.id.countrycode);
        signinmail = (Button)findViewById(R.id.DbtnEmail);
        signup = (TextView)findViewById(R.id.Singupif);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(DeliveryBoy_Loginphone.this,DeliveryBoy_Sendotp.class);

                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeliveryBoy_Loginphone.this,DeliveryBoy_Registration.class));
                finish();
            }
        });
        signinmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeliveryBoy_Loginphone.this,DeliveryBoy_Login.class));
                finish();
            }
        });
    }
}