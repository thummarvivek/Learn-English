package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.learnenglish.Adapters.PaptAdapter;
import com.example.learnenglish.Adapters.SptAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Aence;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Resultaence;
import com.example.learnenglish.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Papt extends AppCompatActivity {
    SwipeRefreshLayout paptswip07;
    RecyclerView recyclerView;
    ArrayList<Aence>arrayList;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papt);
        arrayList =new ArrayList<>();
        paptswip07=findViewById(R.id.papt07);
        recyclerView=findViewById(R.id.paptrv07);
        progressDialog =new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Your screen are loading");
        progressDialog.show();

        paptswip07.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getApplicationContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds


                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Stop animation (This will be after 3 seconds)
                        paptswip07.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        paptswip07.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<Aence>();
        recyclerView=findViewById(R.id.paptrv07);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);

        Refresh();

    }

    public void Refresh(){
        APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
        Call<Resultaence> call=apiInterface.getspt();
        call.enqueue(new Callback<Resultaence>() {
            @Override
            public void onResponse(Call<Resultaence> call, Response<Resultaence> response) {
                arrayList= (ArrayList<Aence>) response.body().getAence();
                progressDialog.dismiss();
                Log.e("check", "onResponse: "+arrayList.get(0).getPapt());
                PaptAdapter paptAdapter=new PaptAdapter(Papt.this, arrayList);
                recyclerView.setAdapter(paptAdapter);
            }

            @Override
            public void onFailure(Call<Resultaence> call, Throwable t) {
                Toast.makeText(Papt.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.show();

            }
        });

    }

}