package com.example.learnenglish.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.learnenglish.Adapters.RVAdapter;
import com.example.learnenglish.Adapters.RvAdapter1;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.Booklist;
import com.example.learnenglish.R;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SellFragment extends Fragment {

    SwipeRefreshLayout swipeLayout;
    ArrayList<Book> arrayList;

    ProgressBar progressBar;
    SearchView searchView;
    RecyclerView recyclerView;
    RVAdapter adapter;
    Toolbar toolbar;



    public SellFragment() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        toolbar = view.findViewById(R.id.toolbarsell);
        searchView =view.findViewById(R.id.searchpanal01);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
//        toolbar.setTitle("buy sell your books");
         progressBar =view.findViewById(R.id.sellprogressBar);

                progressBar.setVisibility(View.VISIBLE);





        swipeLayout = view.findViewById(R.id.swipeContainer);








        // Adding Listener
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        arrayList=new ArrayList<Book>();
        recyclerView=view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh();





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
                Call<Booklist> call= apiInterface.buybooksearch(s);
                call.enqueue(new Callback<Booklist>() {
                    @Override
                    public void onResponse(Call<Booklist> call, Response<Booklist> response) {
                        arrayList = (ArrayList<Book>) response.body().getBook();
                        Log.e("check", "onResponse: " + arrayList.get(0).getName());
                        RVAdapter rvAdapter=new RVAdapter(getContext(),arrayList);
                        recyclerView.setAdapter(rvAdapter);

                    }
                    @Override
                    public void onFailure(Call<Booklist> call, Throwable t) {

                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });




        return view;


//        //just close issues
//
//        return inflater.inflate(R.layout.fragment_sell, container, false);

    }

    public void Refresh(){

        APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
        Call<Booklist> call=apiInterface.getall();
        call.enqueue(new Callback<Booklist>() {

            @Override
            public void onResponse(Call<Booklist> call, Response<Booklist> response) {
                try {

                    arrayList = (ArrayList<Book>) response.body().getBook();
                    RVAdapter rvAdapter = new RVAdapter(getContext(), arrayList);
                    recyclerView.setAdapter(rvAdapter);
                    progressBar.setVisibility(View.GONE);

                }catch (Exception exception){
                    Toast.makeText(getContext(), "data is not fetch", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Booklist> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profilemenu, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_notifications) {

        }
//        else if (id == R.id.notification) {
//
//            Notification b = new Notification();
//            b.show(getActivity().getSupportFragmentManager(), "tag");
//        }
        return super.onOptionsItemSelected(item);
    }

}
