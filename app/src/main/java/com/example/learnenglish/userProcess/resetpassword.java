package com.example.learnenglish.userProcess;

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
import com.example.learnenglish.MainActivity;
import com.example.learnenglish.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resetpassword extends AppCompatActivity {
    EditText firstxt,secondtxt;

    Button btn;

    ArrayList<Registration>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        firstxt=findViewById(R.id.newpassword);
        secondtxt=findViewById(R.id.repassword);
        btn=findViewById(R.id.resetpassword);


//        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed=preferences.edit();
//        ed.putString("User_id", (String) arrayList.get(0).getUserId());
//        ed.apply();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences= getSharedPreferences("login", Context.MODE_PRIVATE);
                String User_id= preferences.getString("User_id",null);
                String Passw= firstxt.getText().toString();



                APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
                Call<Resultregistration> call=apiInterface.resetpassword(User_id,Passw);
                call.enqueue(new Callback<Resultregistration>() {
                    @Override
                    public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {
                        arrayList=(ArrayList<Registration>) response.body().getRegistration();
                        Toast.makeText(resetpassword.this, "Your Password Sucessfully Reset", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(resetpassword.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
//
                    }

                    @Override
                    public void onFailure(Call<Resultregistration> call, Throwable t) {
                        Toast.makeText(resetpassword.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}