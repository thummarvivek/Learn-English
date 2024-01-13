package com.example.learnenglish.userProcess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import com.razorpay.PaymentResultListener;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.OrderPayment;
import com.example.learnenglish.Apicategory.Pay;
import com.example.learnenglish.Apicategory.ResultBookPay;
import com.example.learnenglish.Apicategory.ResultorderPay;

import com.example.learnenglish.R;
import com.example.learnenglish.homepage;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity implements PaymentResultListener , RadioGroup.OnCheckedChangeListener  {

    String amount, card_id, bookid ,payid ,payment_status ;
    TextView bprice, tprice ,sendpay;

    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS =110 ;
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 101;

    RadioGroup radioGroup;
    ArrayList<Pay>arrayList;

    ArrayList<Pay>arrayList1;
    Button paybtn ,paybtn2;
    RadioButton radioButton, radiobtn1, radiobtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        radioGroup =findViewById(R.id.radiog);

        radioGroup.setOnCheckedChangeListener(this);
        radiobtn1 =findViewById(R.id.radione);
        radiobtn2 =findViewById(R.id.radiotwo);
        paybtn =findViewById(R.id.paymentbtn);
        paybtn2 =findViewById(R.id.paymentbtn02);

        paybtn.setVisibility(View.GONE);
        paybtn2.setVisibility(View.GONE);

        bprice =findViewById(R.id.bookprice1);
        tprice =findViewById(R.id.totalprice01);
        sendpay = findViewById(R.id.amountpayment);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();

        Intent intent1=getIntent();
        amount= intent1.getStringExtra("amount");
        card_id= intent1.getStringExtra("card_id");
        bookid= intent1.getStringExtra("bookid");

        bprice.setText(amount);
        tprice.setText(amount);
        sendpay.setText(amount);




        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   startPayment();
            }
        });

        paybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentdata();
                Intent intent =new Intent(Payment.this, homepage.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radione){
                paybtn.setVisibility(View.VISIBLE);
                paybtn2.setVisibility(View.GONE);

            }else if (i == R.id.radiotwo){
                paybtn.setVisibility(View.GONE);
                paybtn2.setVisibility(View.VISIBLE);
            }
    }


    public void startPayment() {

        SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_name = preferences.getString("Username",null);
        String Phone_no = preferences.getString("Phone_no",null);
        String Email = preferences.getString("Email",null);
        String Profilepicture = preferences.getString("Profilepicture",null);

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_JxydaN4pzceXhD");

        /**
         * Set your logo here
         */
        //checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", user_name);
            options.put("description", "Reference No. #123456");
            options.put("image", Profilepicture);
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount+"00");//pass amount in currency subunits
            options.put("prefill.email",Email );
            options.put("prefill.contact",Phone_no);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();

        }

    }

    public void onPaymentSuccess(String s) {


        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        paymentdata();
        Intent intent =new Intent(Payment.this, homepage.class);
        startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "your transaction is failed", Toast.LENGTH_SHORT).show();

    }


    public void paymentdata(){

        int radioId =radioGroup.getCheckedRadioButtonId();
         radioButton =findViewById(radioId);
         String radiodata =radioButton.getText().toString();

        SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_id = preferences.getString("User_id",null);
        APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
        Call<ResultBookPay> call=apiInterface.bopayment( bookid, card_id, user_id,radiodata, "1");

        call.enqueue(new Callback<ResultBookPay>() {
            @Override
            public void onResponse(Call<ResultBookPay> call, Response<ResultBookPay> response) {
                arrayList= (ArrayList<Pay>) response.body().getPay();

                notifi();
                updateorder();

                Toast.makeText(Payment.this, "payment are process", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ResultBookPay> call, Throwable t) {
                Toast.makeText(Payment.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });




    }



    public void updateorder(){

        SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_id = preferences.getString("User_id",null);
        int radioId =radioGroup.getCheckedRadioButtonId();
        radioButton =findViewById(radioId);
        String radiodata =radioButton.getText().toString();

        APIInterface apiInterface1 =Appclient.getclient().create(APIInterface.class);
        Call<ResultBookPay> call1=apiInterface1.bogetpay( bookid, card_id , user_id,radiodata, "1");
        call1.enqueue(new Callback<ResultBookPay>() {
            @Override
            public void onResponse(Call<ResultBookPay> call, Response<ResultBookPay> response) {
                arrayList= (ArrayList<Pay>) response.body().getPay();
                payid =arrayList.get(0).getPaymentId()+"";

            }
            @Override
            public void onFailure(Call<ResultBookPay> call, Throwable t) {
                Toast.makeText(Payment.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
                String user_id = preferences.getString("User_id",null);

          SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
                String currentdata =data.format(new Date());
                SimpleDateFormat detail = new SimpleDateFormat("HH:mm:ss");
                String currentime =detail.format(new Date());

        APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
        Call<ResultorderPay> call=apiInterface.orpaydata(user_id,bookid,payid,"1",currentdata,currentime);
        call.enqueue(new Callback<ResultorderPay>() {
            @Override
            public void onResponse(Call<ResultorderPay> call, Response<ResultorderPay> response) {

            }

            @Override
            public void onFailure(Call<ResultorderPay> call, Throwable t) {
                Toast.makeText(Payment.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });

            }
        }, 5000);
    }

    public void notifi(){

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_name = preferences.getString("Username",null);
        String amo = sendpay.getText().toString();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Dear Customer "+user_name+", Thank you for shopping with us. Your transaction "+amo+" rupees is successful.")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Dear Customer "+user_name+", Thank you for shopping with us. Your transaction "+amo+" rupees is successful.")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);


    }
}