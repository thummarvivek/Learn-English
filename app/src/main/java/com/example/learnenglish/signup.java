package com.example.learnenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Resultregistration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {
    TextView txt4;
    Button btn;
    EditText txt1,txt2,txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt4=findViewById(R.id.textView4);
        btn=findViewById(R.id.button);
        txt1=findViewById(R.id.username);
        txt2=findViewById(R.id.Email);
        txt3=findViewById(R.id.password);



        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User_name=txt1.getText().toString();
                String Email=txt2.getText().toString();
                String Password=txt3.getText().toString();
                APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
                Call<Resultregistration> call =apiInterface.Register_Data(User_name,Email,Password);

                call.enqueue(new Callback<Resultregistration>() {
                    @Override
                    public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {
                        Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(signup.this,MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Resultregistration> call, Throwable t) {

                    }
                });

            }

        });

//end
    }
}