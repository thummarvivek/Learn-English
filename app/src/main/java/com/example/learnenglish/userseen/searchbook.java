package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.learnenglish.Adapters.SearchAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.Booklist;
import com.example.learnenglish.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchbook extends AppCompatActivity {

    SearchView searchView;
    ArrayList<Book> arrayList;
    RecyclerView searchrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);
        searchView=findViewById(R.id.searchpanal);
        searchrv=findViewById(R.id.searchrv);
        arrayList =new ArrayList<>();

        LinearLayoutManager lv=new GridLayoutManager(searchbook.this,2);
        searchrv.setLayoutManager(lv);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                Call<Booklist> call=apiInterface.buybooksearch(s);
                call.enqueue(new Callback<Booklist>() {
                    @Override
                    public void onResponse(Call<Booklist> call, Response<Booklist> response) {
                    arrayList=(ArrayList<Book>)response.body().getBook();
                    SearchAdapter searchAdapter= new SearchAdapter(searchbook.this,arrayList);
                    searchrv.setAdapter(searchAdapter);
                    }

                    @Override
                    public void onFailure(Call<Booklist> call, Throwable t) {
                        Toast.makeText(searchbook.this, "error "+t, Toast.LENGTH_SHORT).show();

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}