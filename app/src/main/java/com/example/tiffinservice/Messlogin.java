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

public class Messlogin extends AppCompatActivity {
    TextInputLayout email, pass;
    Button signin,signinphone;
    TextView forgotpassword,signup;
    String emailid, pwd;
    FirebaseAuth Fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messlogin);

        try {
            email =(TextInputLayout)findViewById(R.id.Lemail);
            pass = (TextInputLayout)findViewById(R.id.Lpassword);
            signin = (Button)findViewById(R.id.signinmail);
            signup = (TextView)findViewById(R.id.textView3);
            forgotpassword = (TextView)findViewById(R.id.forgotpass);
            signinphone = (Button)findViewById(R.id.btnphone);

            Fauth = FirebaseAuth.getInstance();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(Messlogin.this);
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
                                        Toast.makeText(Messlogin.this, "Congratulation You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(Messlogin.this,MessFoodPanel_BottomNavigation.class);
//                                        Toast.makeText(Messlogin.this, "1", Toast.LENGTH_SHORT).show();
                                        startActivity(Z);
                                        finish();
//                                        Toast.makeText(Messlogin.this, "2", Toast.LENGTH_SHORT).show();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(Messlogin.this,"Verification Failed","You Have Not Verified Your Email");


                                    }

                                }else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Messlogin.this,"Error",task.getException().getMessage());

                                }
                            }
                        });
                    }

                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Messlogin.this,MessRegistration.class));
                    finish();

                }
            });
            forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Messlogin.this,MessForgotPassword.class));
                    finish();

                }
            });
            signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Messlogin.this,Messloginphone.class));
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