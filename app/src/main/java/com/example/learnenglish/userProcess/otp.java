package com.example.learnenglish.userProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.learnenglish.Apicategory.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import com.example.learnenglish.R;
import com.example.learnenglish.userProcess.GenericTextWatcher;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp extends AppCompatActivity {

    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;

    private TextView textMobile ;
    private Button buttonVerify;
    private ProgressBar progressBar;
    private String verificationId;


    Button verifyOTPBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
        setTextMobile();
        setupOTPInputs();
        setVerificationId();
        setListener();


//        String phoneno = getIntent().getStringExtra("keyphoneno");

//        tv.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
//        getotp = getIntent().getStringExtra("s");

//        EditText[] edit = { inputCode1, inputCode2, inputCode3, inputCode4, inputCode5 , inputCode6};
//
//        inputCode1.addTextChangedListener(new GenericTextWatcher(inputCode1, edit));
//        inputCode2.addTextChangedListener(new GenericTextWatcher(inputCode2, edit));
//        inputCode3.addTextChangedListener(new GenericTextWatcher(inputCode3, edit));
//        inputCode4.addTextChangedListener(new GenericTextWatcher(inputCode4, edit));
//        inputCode5.addTextChangedListener(new GenericTextWatcher(inputCode5, edit));
//        inputCode6.addTextChangedListener(new GenericTextWatcher(inputCode6, edit));

    }

    private void init() {

        inputCode1 = findViewById(R.id.editTextTextPassword);
        inputCode2 = findViewById(R.id.editTextTextPassword1);
        inputCode3 = findViewById(R.id.editTextTextPassword2);
        inputCode4 = findViewById(R.id.editTextTextPassword3);
        inputCode5 = findViewById(R.id.editTextTextPassword4);
        inputCode6 = findViewById(R.id.editTextTextPassword5);
        textMobile = findViewById(R.id.textView9);
        buttonVerify = findViewById(R.id.otpbutton);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setListener() {
        buttonVerify.setOnClickListener(v -> {
            if (inputCode1.getText().toString().trim().isEmpty()
                    || inputCode2.getText().toString().trim().isEmpty()
                    || inputCode3.getText().toString().trim().isEmpty()
                    || inputCode4.getText().toString().trim().isEmpty()
                    || inputCode5.getText().toString().trim().isEmpty()
                    || inputCode6.getText().toString().trim().isEmpty()) {
                Toast.makeText(otp.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                return;
            }
            String code =
                    inputCode1.getText().toString() +
                            inputCode2.getText().toString() +
                            inputCode3.getText().toString() +
                            inputCode4.getText().toString() +
                            inputCode5.getText().toString() +
                            inputCode6.getText().toString();

            if (verificationId != null) {
                progressBar.setVisibility(View.VISIBLE);
                buttonVerify.setVisibility(View.INVISIBLE);
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationId,
                        code
                );

                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(task -> {
                            progressBar.setVisibility(View.GONE);
                            buttonVerify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(otp.this, resetpassword.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(otp.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        findViewById(R.id.resend).setOnClickListener(v -> {
            //verify phone number
            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder()
                            .setPhoneNumber("+91" + getIntent().getStringExtra("mobile"))
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    Toast.makeText(otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    verificationId = newVerificationId;
                                    Toast.makeText(otp.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        });
    }

    private void setVerificationId() {
        verificationId = getIntent().getStringExtra("verificationId");
    }

    /**
     * If Intent() getStringExtra == "mobile" -> startActivity(VerifyActivity),
     * (TextView) textMobile will be received value "user mobile number"
     */
    private void setTextMobile() {
        textMobile.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));
    }

    /** When the edittext1 (inputCode1) was inserted, the cursor will be jump to the
     * next edittext (in this case it would be "inputCode2")*/


//    private void setupOTPInputs(){
//        inputCode1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().trim().isEmpty()){
//                    inputCode2.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        inputCode2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().trim().isEmpty()){
//                    inputCode3.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        inputCode3.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().trim().isEmpty()){
//                    inputCode4.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        inputCode4.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().trim().isEmpty()){
//                    inputCode5.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        inputCode5.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.toString().trim().isEmpty()){
//                    inputCode6.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//}
    private void setupOTPInputs(){
        EditText[] edit = { inputCode1, inputCode2, inputCode3, inputCode4, inputCode5 , inputCode6};

        inputCode1.addTextChangedListener(new GenericTextWatcher(inputCode1, edit));
        inputCode2.addTextChangedListener(new GenericTextWatcher(inputCode2, edit));
        inputCode3.addTextChangedListener(new GenericTextWatcher(inputCode3, edit));
        inputCode4.addTextChangedListener(new GenericTextWatcher(inputCode4, edit));
        inputCode5.addTextChangedListener(new GenericTextWatcher(inputCode5, edit));
        inputCode6.addTextChangedListener(new GenericTextWatcher(inputCode6, edit));

    }
}
