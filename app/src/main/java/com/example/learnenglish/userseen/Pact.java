package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;
import android.widget.Toast;

import com.example.learnenglish.Adapters.PactAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Aence;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Resultaence;
import com.example.learnenglish.R;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pact extends AppCompatActivity {
    SwipeRefreshLayout pactswip06;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;
    ArrayList<Aence>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pact);
        arrayList =new ArrayList<>();
        pactswip06 =findViewById(R.id.pact06);
        recyclerView =findViewById(R.id.pactrv06);
        progressDialog =new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Your screen are loading");
        progressDialog.show();

        pactswip06.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();

                // Your code here
                Toast.makeText(getApplicationContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds


                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Stop animation (This will be after 3 seconds)
                        pactswip06.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        pactswip06.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        arrayList= new ArrayList<Aence>();
        recyclerView =findViewById(R.id.pactrv06);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(linearLayoutManager);

        Refresh();
    }

    public void Refresh(){
        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<Resultaence> call =apiInterface.getspt();
        call.enqueue(new Callback<Resultaence>() {
            @Override
            public void onResponse(Call<Resultaence> call, Response<Resultaence> response) {
                arrayList = (ArrayList<Aence>) response.body().getAence();
                progressDialog.dismiss();
                Log.e("check","onResponce: "+arrayList.get(0).getPact());
                PactAdapter pactAdapter= new PactAdapter(Pact.this,arrayList);
                recyclerView.setAdapter(pactAdapter);
            }

            @Override
            public void onFailure(Call<Resultaence> call, Throwable t) {
                Toast.makeText(Pact.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.show();

            }
        });
    }
}