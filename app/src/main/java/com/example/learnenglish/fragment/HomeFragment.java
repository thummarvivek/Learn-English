package com.example.learnenglish.fragment;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.learnenglish.R;

import com.example.learnenglish.splash;
import com.example.learnenglish.userProcess.Myporshow;
import com.example.learnenglish.userseen.Clock;
import com.example.learnenglish.userseen.searchbook;
import com.example.learnenglish.userseen.SpeakingSkill;
import com.example.learnenglish.userseen.all_vocabulary_tence;
import com.example.learnenglish.userseen.Grammar;
import com.example.learnenglish.userseen.Smartenglish;
import com.example.learnenglish.userseen.Vocabulary;


public class HomeFragment extends Fragment {
    private static int Screen=4000;
    public static boolean isNetworkConnected = false;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,imgbtn ,orbtn ,inco2;
    ImageView img1,inco1;
    Button inco3;
    WifiManager wifiManager;

    Context context;

//    Toolbar toolbar;
//    ImageButton imgbtn;

    public HomeFragment() {


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        toolbar =view.findViewById(R.id.toolbar1);

        txt1 =view.findViewById(R.id.textView1);
        txt2 =view.findViewById(R.id.textView2);
        txt3 =view.findViewById(R.id.textView3);
        txt4 =view.findViewById(R.id.textView4);
        txt5 =view.findViewById(R.id.textView5);
        txt6 =view.findViewById(R.id.textView6);
        orbtn =view.findViewById(R.id.orocart01);
        imgbtn =view.findViewById(R.id.scu01);
        img1 =view.findViewById(R.id.imnati);
        inco1 =view.findViewById(R.id.intim);
        inco2 =view.findViewById(R.id.intext);
        inco3 =view.findViewById(R.id.intbtn);


        registerNetworkCallback();

        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);
        txt6.setVisibility(View.GONE);
        img1.setVisibility(View.GONE);
        inco1.setVisibility(View.VISIBLE);
        inco2.setVisibility(View.VISIBLE);
        inco3.setVisibility(View.VISIBLE);



//        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
//        appCompatActivity.setSupportActionBar(toolbar);
//        toolbar.setTitle("Learn English");

        inco3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {

            Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                startActivity(intent);

            }
        });


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), searchbook.class);
                startActivity(intent);

            }
        });

        orbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), Myporshow.class);
                startActivity(intent);
            }
        });


         txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), Vocabulary.class);
                startActivity(intent);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), all_vocabulary_tence.class);
                startActivity(intent);
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), Grammar.class);
                startActivity(intent);
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), Clock.class);
                startActivity(intent);
            }
        });
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), Smartenglish.class);
                startActivity(intent);
            }
        });
        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), SpeakingSkill.class);
                startActivity(intent);
            }
        });


        return  view;

    }
    public void registerNetworkCallback()
    {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                                                                       @Override
                                                                       public void onAvailable(Network network) {
//                                                                           Toast.makeText(getContext(), "Internet is Connected", Toast.LENGTH_SHORT).show();
                                                                           isNetworkConnected = true; // Global Static Variable
                                                                           txt1.setVisibility(View.VISIBLE);
                                                                           txt2.setVisibility(View.VISIBLE);
                                                                           txt3.setVisibility(View.VISIBLE);
                                                                           txt4.setVisibility(View.VISIBLE);
                                                                           txt5.setVisibility(View.VISIBLE);
                                                                           txt6.setVisibility(View.VISIBLE);
                                                                           img1.setVisibility(View.VISIBLE);
                                                                           inco1.setVisibility(View.GONE);
                                                                           inco2.setVisibility(View.GONE);
                                                                           inco3.setVisibility(View.GONE);
                                                                       }
                                                                       @Override
                                                                       public void onLost(Network network) {
                                                                           isNetworkConnected = false;
//                                                                           Toast.makeText(getContext(), "Internet is Not Connected ", Toast.LENGTH_SHORT).show();
                                                                           txt1.setVisibility(View.GONE);
                                                                           txt2.setVisibility(View.GONE);
                                                                           txt3.setVisibility(View.GONE);
                                                                           txt4.setVisibility(View.GONE);
                                                                           txt5.setVisibility(View.GONE);
                                                                           txt6.setVisibility(View.GONE);
                                                                           img1.setVisibility(View.GONE);
                                                                           inco1.setVisibility(View.VISIBLE);
                                                                           inco2.setVisibility(View.VISIBLE);
                                                                           inco3.setVisibility(View.VISIBLE);


                                                                       }
                                                                   }

                );
            }
            isNetworkConnected = false;
//            Toast.makeText(getContext(), "Internet is Not Connected ", Toast.LENGTH_SHORT).show();
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            txt3.setVisibility(View.GONE);
            txt4.setVisibility(View.GONE);
            txt5.setVisibility(View.GONE);
            txt6.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
            inco1.setVisibility(View.VISIBLE);
            inco2.setVisibility(View.VISIBLE);
            inco3.setVisibility(View.VISIBLE);




        }catch (Exception e){
            isNetworkConnected = false;
//            Toast.makeText(getContext(), "Internet is Not Connected ", Toast.LENGTH_SHORT).show();
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            txt3.setVisibility(View.GONE);
            txt4.setVisibility(View.GONE);
            txt5.setVisibility(View.GONE);
            txt6.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
            inco1.setVisibility(View.VISIBLE);
            inco2.setVisibility(View.VISIBLE);
            inco3.setVisibility(View.VISIBLE);



        }

    }













//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.search,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id=item.getItemId();
//
//        if (id==R.id.pro01){
//            Intent intent = new Intent(getActivity(),searchbook.class);
//            startActivity(intent);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}