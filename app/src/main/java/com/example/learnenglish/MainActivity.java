package com.example.learnenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Registration;
import com.example.learnenglish.Apicategory.Resultregistration;
import com.example.learnenglish.userProcess.Forgotpassword;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText txt1,txt2;
    TextView txt3,txt4;

    ArrayList<Registration>arrayList;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.loginbutton);
        txt3=findViewById(R.id.textView4);
        txt1=findViewById(R.id.username);
        txt2=findViewById(R.id.password);
        txt4=findViewById(R.id.textView2);
        arrayList = new ArrayList<>();




        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        Boolean b=preferences.getBoolean("is_regi",false);
        if(b){

            Intent intent=new Intent(MainActivity.this,homepage.class);
            startActivity(intent);
            finish();
        }

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Forgotpassword", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, Forgotpassword.class);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (b){

                    Intent intent=new Intent(MainActivity.this,homepage.class);
                    startActivity(intent);

                }
                String User_name  = txt1.getText().toString();
                String Password = txt2.getText().toString();




                APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                Call<Resultregistration> call=apiInterface.login(User_name,Password);


                call.enqueue(new Callback<Resultregistration>() {
                    @Override
                    public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {

                        try {
                            arrayList =(ArrayList<Registration>) response.body().getRegistration();
                            String u=arrayList.get(0).getUserName();
                            String p=arrayList.get(0).getPassword();

                            if (User_name.equals(u) && Password.equals(p)){
//                                progressDoalog = new ProgressDialog(MainActivity.this);
//                                progressDoalog.setMax(100);
//                                progressDoalog.setMessage("Account verification");
//                                progressDoalog.setTitle("Login are Processing");
////                                progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                progressDoalog.show();
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//
//                                            while (progressDoalog.getProgress() <= progressDoalog
//                                                    .getMax()) {
//                                                Thread.sleep(2000);
//
//                                                handle.sendMessage(handle.obtainMessage());
//                                                if (progressDoalog.getProgress() == progressDoalog
//                                                        .getMax()) {
//                                                    progressDoalog.dismiss();
//                                                }
//
//                                            }
//
//
//                                        } catch (Exception e) {
//
//                                            e.printStackTrace();
//
//                                        }
//                                        Intent intent=new Intent(MainActivity.this, homepage.class);
//                                        startActivity(intent);
//                                        progressDoalog.dismiss();
//
//                                    }
//
//
//                                }).start();

                                Intent intent=new Intent(MainActivity.this, homepage.class);
                                        startActivity(intent);
                                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();



                                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed=preferences.edit();
                                ed.putString(User_name,u);
                                ed.putString(Password,p);
                                ed.putString("User_id", (String) arrayList.get(0).getUserId());
                                ed.putString("Username", (String) arrayList.get(0).getUserName());
                                ed.putString("Email", (String) arrayList.get(0).getEmail());
                                ed.putString("Address",(String) arrayList.get(0).getAddress());
                                ed.putString("Bio",(String) arrayList.get(0).getBio());
                                ed.putString("Dob", (String) arrayList.get(0).getDob());
                                ed.putString("Gender", (String) arrayList.get(0).getGender());
                                ed.putString("Profilepicture", (String) arrayList.get(0).getProfilePicture());
                                ed.putString("log",(String) arrayList.get(0).getLog());
                                ed.putString("lat",(String) arrayList.get(0).getLat());
                                ed.putString("Phone_no",(String) arrayList.get(0).getPhoneno());
                                ed.putString("password",(String) arrayList.get(0).getPassword());

                                ed.putBoolean("is_regi",true);
                                ed.apply();
                            }
                            else {

                                txt1.setText(" ");
                                txt2.setText(" ");

                                Toast.makeText(MainActivity.this, "please retry", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this , "Error!"+e+"", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Resultregistration> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "fail"+t, Toast.LENGTH_SHORT).show();
                    }


                });






            }

        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });
    }
//
//   public Handler handle = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            progressDoalog.incrementProgressBy(1);
//        }
//    };

}