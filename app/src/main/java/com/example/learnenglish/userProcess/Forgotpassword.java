package com.example.learnenglish.userProcess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Registration;
import com.example.learnenglish.Apicategory.Resultregistration;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import android.widget.ProgressBar;

import com.example.learnenglish.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgotpassword extends AppCompatActivity {

//     EditText txt;
//     FirebaseAuth mAuth;
 ArrayList<Registration> arrayList;

public EditText inputMobile;
public Button buttonGetOTP;

String User_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        arrayList = new ArrayList<>();

        inputMobile = findViewById(R.id.forgotid);
         buttonGetOTP = findViewById(R.id.forgetbtn);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        buttonGetOTP.setOnClickListener(v -> {

            //toast error
            if(inputMobile.getText().toString().isEmpty()){
                Toast.makeText(Forgotpassword.this, "Enter mobile", Toast.LENGTH_SHORT).show();
                return;
            }
            String Phoneno =inputMobile.getText().toString();

//            ed.putString("Phone_no",(String) arrayList.get(0).getPhoneno());

            APIInterface apiInterface=Appclient.getclient().create(APIInterface.class);
            Call<Resultregistration> call=apiInterface.forget(Phoneno);
            call.enqueue(new Callback<Resultregistration>() {
                @Override
                public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {
                    arrayList=(ArrayList<Registration>) response.body().getRegistration();
                    SharedPreferences preferences= getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed= preferences.edit();
                    ed.putString("User_id", arrayList.get(0).getUserId());
                    ed.apply();

                }

                @Override
                public void onFailure(Call<Resultregistration> call, Throwable t) {
                    Toast.makeText(Forgotpassword.this, "This User account does not exist", Toast.LENGTH_SHORT).show();

                }
            });

            //set visibility
            buttonGetOTP.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            //verify phone number
            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder()
                            .setPhoneNumber("+91"+inputMobile.getText().toString())
                            .setTimeout(60L,TimeUnit.SECONDS)
                            .setActivity(this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                    Toast.makeText(Forgotpassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                    //action
                                    Intent intent = new Intent(Forgotpassword.this,otp.class);
                                    intent.putExtra("mobile",inputMobile.getText().toString());
                                    intent.putExtra("verificationId",verificationId);
                                    startActivity(intent);
                                }
                            })
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        });



//                String phoneno = txt.getText().toString();
//                Intent intent = new Intent(Forgotpassword.this, otp.class);
//                intent.putExtra("keyphoneno", phoneno);
//                startActivity(intent);
    }

    }
