package com.example.tiffinservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DeliveryBoy_Login extends AppCompatActivity {
    TextInputLayout email, pass;
    Button signin,signinphone;
    TextView forgotpassword,signup;
    String emailid, pwd;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy_login);

        try {
            email =(TextInputLayout)findViewById(R.id.Demail);
            pass = (TextInputLayout)findViewById(R.id.Dpassword);
            signin = (Button)findViewById(R.id.Loginnbtn);
            signup = (TextView)findViewById(R.id.donot);
            forgotpassword = (TextView)findViewById(R.id.Dforgotpass);
            signinphone = (Button)findViewById(R.id.Dbtnphone);

            Fauth = FirebaseAuth.getInstance();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(DeliveryBoy_Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()){
                                        mDialog.dismiss();
                                        Toast.makeText(DeliveryBoy_Login.this, "Congratulation You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(DeliveryBoy_Login.this,DeliveryFoodPanel_BottomNavigation.class);
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(DeliveryBoy_Login.this,"Verification Failed","You Have Not Verified Your Email");


                                    }

                                }else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(DeliveryBoy_Login.this,"Error",task.getException().getMessage());

                                }
                            }
                        });
                    }

                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DeliveryBoy_Login.this,DeliveryBoy_Registration.class));
                    finish();

                }
            });
            forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DeliveryBoy_Login.this,DeliveryForgotPassword.class));
                    finish();

                }
            });
            signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DeliveryBoy_Login.this,DeliveryBoy_Loginphone.class));
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isValid=false, isValidemail = false, isValidpassword = false;
        if(TextUtils.isEmpty(emailid)){
            email.setErrorEnabled(true);
            email.setError("Email Id required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                email.setErrorEnabled(true);
                email.setError("please enter valid email id");
            }
        }
        if(TextUtils.isEmpty(pwd)){
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        }else {
            isValidpassword =true;
        }
        isValid=(isValidemail && isValidpassword )?true:false;
        return isValid;
    }

}