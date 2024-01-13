package com.example.learnenglish.userProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Adapters.RVAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.Booklist;
import com.example.learnenglish.Apicategory.Resultcart;
import com.example.learnenglish.R;
import com.example.learnenglish.homepage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class selectcart extends AppCompatActivity {




    ArrayList<Book> arrayList;

    Button Adcart;
    ImageView img;
    TextView btitle, bookedition, bookprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcart);


        img = findViewById(R.id.selectimg);
        btitle = findViewById(R.id.bname);
        bookedition = findViewById(R.id.bedition);
        bookprice = findViewById(R.id.bprice);
        Adcart =findViewById(R.id.addtocart);
        arrayList = new ArrayList<>();

        Intent intent = getIntent();
        String category_id = intent.getStringExtra("categoryid");
        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<Booklist> call = apiInterface.selectbook(category_id);
        call.enqueue(new Callback<Booklist>() {
            @Override
            public void onResponse(Call<Booklist> call, Response<Booklist> response) {
                try {

                    arrayList =(ArrayList<Book>)response.body().getBook();

                    Picasso.get().load(arrayList.get(0).getUrl()).into(img);

                    String name = arrayList.get(0).getName();
                    btitle.setText(name);

                    String bookEdition = arrayList.get(0).getBookEdition();
                    bookedition.setText( bookEdition);

                    String bookPrice = arrayList.get(0).getBookPrice();
                    bookprice.setText(bookPrice);


                } catch (Exception e) {
                    Toast.makeText(selectcart.this, "this App does not have a data " + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Booklist> call, Throwable t) {
                Toast.makeText(selectcart.this, "error : " + t, Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences preferences= getSharedPreferences("login", Context.MODE_PRIVATE);
        String User_id= preferences.getString("User_id",null);
        
        Adcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                APIInterface apiInterface1 =Appclient .getclient().create(APIInterface.class);
                Call<Resultcart> call1 =apiInterface1.selectcart(User_id, category_id ,"1");
                call1.enqueue(new Callback<Resultcart>() {
                    @Override
                    public void onResponse(Call<Resultcart> call, Response<Resultcart> response) {
                        Toast.makeText(selectcart.this, "go to Cart", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(selectcart.this, homepage.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<Resultcart> call, Throwable t) {
                        Toast.makeText(selectcart.this, "process are failed", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
}



