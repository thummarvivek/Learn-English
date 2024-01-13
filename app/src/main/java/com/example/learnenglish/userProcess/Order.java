package com.example.learnenglish.userProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.CartBook;
import com.example.learnenglish.Apicategory.Reshowcart;
import com.example.learnenglish.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order extends AppCompatActivity {

    TextView orname ,oadd ,orphone,bname ,bprice ,fprice ,tprice ,sendamount;

    ArrayList<CartBook> arrayList;
    ImageView bimg;

    String amount ,card_id ,bookid;

    Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        arrayList=new ArrayList<>();
        orname =findViewById(R.id.ouname);
        oadd =findViewById(R.id.oaddress);
        orphone =findViewById(R.id.onumber);
        bname =findViewById(R.id.bookprice1);
        bprice =findViewById(R.id.bookprice2);
        fprice =findViewById(R.id.priceitem);
        tprice =findViewById(R.id.totalamount);
        sendamount =findViewById(R.id.amountoforder);
        bimg =findViewById(R.id.boorderimg1);
        sendbtn =findViewById(R.id.orderpayment);


        SharedPreferences preferences= getSharedPreferences("login", Context.MODE_PRIVATE);
        String User_id= preferences.getString("User_id",null);
        String user_name = preferences.getString("Username",null);
        String Address = preferences.getString("Address",null);
        String Phone_no = preferences.getString("Phone_no", null);


        Intent intent=getIntent();
        card_id=intent.getStringExtra("card_id");
        orname.setText(user_name);
        oadd.setText(Address);
        orphone.setText(Phone_no);



        APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
        Call<Reshowcart> call=apiInterface.orcart(User_id,card_id);
        call.enqueue(new Callback<Reshowcart>() {
            @Override
            public void onResponse(Call<Reshowcart> call, Response<Reshowcart> response) {
                try {
                    arrayList= (ArrayList<CartBook>) response.body().getCartBook();

                    Picasso.get().load(arrayList.get(0).getUrl()).into(bimg);
                    bname.setText(arrayList.get(0).getName());
                    bprice.setText(arrayList.get(0).getBookPrice());
                    fprice.setText(arrayList.get(0).getBookPrice());
                    tprice.setText(arrayList.get(0).getBookPrice());
                    sendamount.setText(arrayList.get(0).getBookPrice());

                }catch (Exception e){
                    Toast.makeText(Order.this, "Exception"+e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Reshowcart> call, Throwable t) {

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookid = arrayList.get(0).getId();
                amount =tprice.getText().toString();
                Intent intent1 =new Intent(Order.this,Payment.class);
                intent1.putExtra("amount",amount);
                intent1.putExtra("card_id",card_id );
                intent1.putExtra("bookid",bookid);
                startActivity(intent1);

            }
        });



    }
}