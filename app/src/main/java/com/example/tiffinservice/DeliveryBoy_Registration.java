package com.example.tiffinservice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryBoy_Registration extends AppCompatActivity {

    String[] Maharastra= {"Mumbai","Pune","Nashik"};
    String[] Madhyapradesh ={"Bhopal","Indore","Ujjain"};

    TextInputLayout Fname,Lname,Email,Pass,cpass,Mobileno,houseno,area,pincode;
    Spinner Statespin,Cityspin;
    Button signup,Emaill,Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fName, lName, emailId, password, comPassword, mobile, house, area2, pinCode2, role="DeliveryBoy",state, city;
    boolean isValid;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy_registration);

        Fname = (TextInputLayout)findViewById(R.id.fname);
        Lname = (TextInputLayout) findViewById(R.id.lname);
        Email = (TextInputLayout) findViewById(R.id.Emailid);
        Pass = (TextInputLayout) findViewById(R.id.password);
        cpass = (TextInputLayout) findViewById(R.id.confirmpassword);
        Mobileno =(TextInputLayout)findViewById(R.id.mobileno);
        houseno = (TextInputLayout) findViewById(R.id.Houseno);
        area = (TextInputLayout) findViewById(R.id.Areaa);
        pincode = (TextInputLayout) findViewById(R.id.Pincodee);


        // Spinners
        Statespin = (Spinner) findViewById(R.id.State);
        Cityspin = (Spinner) findViewById(R.id.City);


        //BUttons
        signup = (Button) findViewById(R.id.Signuup);
        Phone = (Button) findViewById(R.id.Phonenumbersign);
        Emaill = (Button) findViewById(R.id.emaillid);

        //CountryCodePicker
        Cpp = (CountryCodePicker)findViewById(R.id.ctrycode);



        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                state = value.toString().trim();
                if(state.equals("Maharashtra")){
                    ArrayList<String> list = new ArrayList<>();
                    for(String city:Maharastra){
                        list.add(city);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DeliveryBoy_Registration.this, android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(state.equals("Madhyapradesh")){
                    ArrayList<String> list = new ArrayList<>();
                    for(String city:Madhyapradesh){
                        list.add(city);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DeliveryBoy_Registration.this, android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                city = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Mess");
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fName = Fname.getEditText().getText().toString().trim();
                lName = Lname.getEditText().getText().toString().trim();
                emailId = Email.getEditText().getText().toString().trim();
                mobile = Mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                comPassword = cpass.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                area2 =  area.getEditText().getText().toString().trim();
                pinCode2 = pincode.getEditText().getText().toString().trim();

                if(isValid()) {
                    //Toast.makeText(MessRegistration.this, "heyhere ", Toast.LENGTH_SHORT).show();
                    final ProgressDialog mDialog = new ProgressDialog(DeliveryBoy_Registration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait....");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userId);
                                final HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String, String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile no",mobile);
                                        hashMap1.put("First Name",fName);
                                        hashMap1.put("Last Name",lName);
                                        hashMap1.put("Email",emailId);
                                        hashMap1.put("City",city);
                                        hashMap1.put("Area",area2);
                                        hashMap1.put("Password",password);
                                        hashMap1.put("Pin Code",pinCode2);
                                        hashMap1.put("State",state);
                                        hashMap1.put("Confirm Password", comPassword);
                                        hashMap1.put("house",house);

                                        FirebaseDatabase.getInstance().getReference("DeliveryBoy")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryBoy_Registration.this);
                                                                    builder.setMessage("You Have Registered now please verify your Email...");
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();

//                                                                           String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                            Intent b = new Intent(DeliveryBoy_Registration.this,DeliveryBoy_VerifyPhone.class);
//                                                                            b.putExtra("phonenumber",phonenumber);
                                                                            startActivity(b);


                                                                        }
                                                                    });
                                                                    AlertDialog Alert = builder.create();
                                                                    Alert.show();
                                                                }else{
                                                                    mDialog.dismiss();
                                                                    ReusableCodeForAll.ShowAlert(DeliveryBoy_Registration.this,"ERROR",task.getException().getMessage());
                                                                }
                                                            }
                                                        });
                                                    }
                                                });

                                    }
                                });
                            }else{
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(DeliveryBoy_Registration.this,"ERROR",task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DeliveryBoy_Registration.this,UserLogin.class));
                finish();
            }
        });
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeliveryBoy_Registration.this,UserLoginphone.class));
                finish();
            }
        });

    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");;
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");
//, isValidName= false

        boolean isValidlName =false, isValidfName =false, isValidEmail=false,isValidPassword=false,isValidConfirmPassword=false,isValidMobileNo=false,isValidArea=false,isValidHouseNO=false,isValidPinCode=false;
        if(TextUtils.isEmpty(fName)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidfName = true;
//            Toast.makeText(this, "1"+isValidfName, Toast.LENGTH_SHORT).show();
        }


        if(TextUtils.isEmpty(lName)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlName = true;
//            Toast.makeText(this, "2"+isValidlName, Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(emailId)){
            Email.setErrorEnabled(true);
            Email.setError("Email is required");
        }else{
            if(emailId.matches(emailpattern)){
                isValidEmail = true;
//                Toast.makeText(this, "3"+isValidEmail, Toast.LENGTH_SHORT).show();
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter Valid EmailID");
            }
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError(" Password is required");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is weak");
            }else{
                isValidPassword = true;
//                Toast.makeText(this, "4"+isValidPassword, Toast.LENGTH_SHORT).show();
            }

        }

        if(TextUtils.isEmpty(comPassword)){
            cpass.setErrorEnabled(true);
            cpass.setError("Enter Password Again");
        }else{
            if(!password.equals(comPassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Password Doesn't Match");
            }else{
                isValidConfirmPassword = true;
//                Toast.makeText(this, "5"+isValidConfirmPassword, Toast.LENGTH_SHORT).show();
            }

        }
        if(TextUtils.isEmpty(mobile)){
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Mobile Number is required");
        }else{
            if(mobile.length()<10){
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Invalid Mobile Number");
            }else{
                isValidMobileNo = true;
//                Toast.makeText(this, "6"+isValidMobileNo, Toast.LENGTH_SHORT).show();
            }

        }
        if(TextUtils.isEmpty(area2)){
            area.setErrorEnabled(true);
            area.setError("Area is required");
        }else{

            isValidArea = true;
//            Toast.makeText(this, "7"+isValidArea, Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(pinCode2)){
            pincode.setErrorEnabled(true);
            pincode.setError("Pin-Code is required");
        }else{

            isValidPinCode = true;
//            Toast.makeText(this, "8"+isValidPinCode, Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(house)){
            houseno.setErrorEnabled(true);
            houseno.setError("Fields can't be empty");
        }else{

            isValidHouseNO= true;

        }

        isValid =(isValidArea && isValidPassword && isValidConfirmPassword && isValidPinCode && isValidEmail && isValidMobileNo && isValidfName && isValidlName && isValidHouseNO)? true:false;
        //Toast.makeText(MessRegistration.this, ""+isValid, Toast.LENGTH_SHORT).show();


        return isValid;
        //return true;

    }
}