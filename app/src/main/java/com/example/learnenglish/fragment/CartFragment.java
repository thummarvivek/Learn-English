package com.example.learnenglish.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

import com.example.learnenglish.Adapters.CartAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;

import com.example.learnenglish.Apicategory.CartBook;
import com.example.learnenglish.Apicategory.Reshowcart;

import com.example.learnenglish.R;
import com.example.learnenglish.homepage;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    SwipeRefreshLayout refreshpage;
    ArrayList<CartBook> arrayList;

    ImageView emtimg;
    TextView emtext ,emtxt;
    Button embtn;
    View emview;
    Toolbar toolbar;

    RecyclerView recyclerView;


    public CartFragment() {

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        toolbar =view.findViewById(R.id.toolcart);
        refreshpage =view.findViewById(R.id.cartswip);
        emtimg =view.findViewById(R.id.emimg01);
        emview =view.findViewById(R.id.emp02);
        emtext =view.findViewById(R.id.emte03);
        emtxt =view.findViewById(R.id.emte04);
        embtn =view.findViewById(R.id.embt05);

        emtimg.setVisibility(View.GONE);
        emview.setVisibility(View.GONE);
        emtext.setVisibility(View.GONE);
        emtxt.setVisibility(View.GONE);
        embtn.setVisibility(View.GONE);





        arrayList=new ArrayList<>();


        refreshpage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        refreshpage.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        refreshpage.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<CartBook>();
        recyclerView =view.findViewById(R.id.cartrv);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh();


      return view;
    }
    public void Refresh(){

        SharedPreferences preferences= getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String User_id= preferences.getString("User_id",null);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<Reshowcart> call =apiInterface.showcart(User_id);
        call.enqueue(new Callback<Reshowcart>() {
            @Override
            public void onResponse(Call<Reshowcart> call, Response<Reshowcart> response) {
                try {
                   arrayList= (ArrayList<CartBook>) response.body().getCartBook();
                    CartAdapter cartAdapter = new CartAdapter(getContext(), arrayList);
                    recyclerView.setAdapter(cartAdapter);
                }catch (Exception exception){
                    Toast.makeText(getContext(), "data is not fetch", Toast.LENGTH_SHORT).show();
                    emtimg.setVisibility(View.VISIBLE);
                    emview.setVisibility(View.VISIBLE);
                    emtext.setVisibility(View.VISIBLE);
                    emtxt.setVisibility(View.VISIBLE);
                    embtn.setVisibility(View.VISIBLE);
                    refreshpage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<Reshowcart> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
                emtimg.setVisibility(View.VISIBLE);
                emview.setVisibility(View.VISIBLE);
                emtext.setVisibility(View.VISIBLE);
                emtxt.setVisibility(View.VISIBLE);
                embtn.setVisibility(View.VISIBLE);
                refreshpage.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);

                embtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent =new Intent(getActivity(), homepage.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }

}