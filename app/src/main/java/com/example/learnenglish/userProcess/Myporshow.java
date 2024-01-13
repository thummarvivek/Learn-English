package com.example.learnenglish.userProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.learnenglish.Adapters.OrderAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Myorder;
import com.example.learnenglish.Apicategory.Resultmyorder;
import com.example.learnenglish.Apicategory.Tence;
import com.example.learnenglish.R;
import com.example.learnenglish.userseen.Vocabulary;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Myporshow extends AppCompatActivity {

    SwipeRefreshLayout swipeLayout;

    ArrayList<Myorder> arrayList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myporshow);
        swipeLayout =findViewById(R.id.swiporshow);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getApplicationContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<Myorder>();
        recyclerView=findViewById(R.id.orderrv);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);

        Refresh();


    }

    public void Refresh(){
        SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_id = preferences.getString("User_id",null);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<Resultmyorder> call=apiInterface.myorders(user_id);
        call.enqueue(new Callback<Resultmyorder>() {
            @Override
            public void onResponse(Call<Resultmyorder> call, Response<Resultmyorder> response) {
                arrayList= (ArrayList<Myorder>) response.body().getMyorder();
                OrderAdapter orderAdapter =new OrderAdapter(Myporshow.this ,arrayList);
                Log.e("check", "onResponse: "+arrayList.get(0).getUrl());
                Log.e("check", "onResponse: "+arrayList.get(0).getName());
                Log.e("check", "onResponse: "+arrayList.get(0).getBookEdition());
                Log.e("check", "onResponse: "+arrayList.get(0).getBookPrice());
                recyclerView.setAdapter(orderAdapter);

            }

            @Override
            public void onFailure(Call<Resultmyorder> call, Throwable t) {

            }
        });

    }

}