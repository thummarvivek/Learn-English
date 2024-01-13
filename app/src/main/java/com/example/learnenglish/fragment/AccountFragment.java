package com.example.learnenglish.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.learnenglish.Adapters.RVAdapter;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.Booklist;
import com.example.learnenglish.Apicategory.Registration;
import com.example.learnenglish.Apicategory.Resultregistration;
import com.example.learnenglish.MainActivity;
import com.example.learnenglish.R;
import com.example.learnenglish.userProcess.edit_profile;
import com.example.learnenglish.userseen.searchbook;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class AccountFragment extends Fragment {

    TextView txt01,txtt1,txtt2,txtt3,txtt4,txtt5,imgbtn0001  ;
    Context view;




    ImageView txt02;
//    Toolbar toolbar;

    public AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//        toolbar = view.findViewById(R.id.toolbarprofile);
//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        appCompatActivity.setSupportActionBar(toolbar);
//        toolbar.setTitle("Har_Har_Mahadev");


        txt01 =view.findViewById(R.id.editpofilebtn);
        txt02 =view.findViewById(R.id.profiledp);
        txtt1 =view.findViewById(R.id.username001);
        txtt2 =view.findViewById(R.id.address001);
        txtt3 =view.findViewById(R.id.phoneno001);
        txtt4  =view.findViewById(R.id.email001);
        txtt5 =view.findViewById(R.id.Bio005);
        imgbtn0001 =view.findViewById(R.id.scu002);




        imgbtn0001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottomsheet b =new Bottomsheet();
                b.show(getActivity().getSupportFragmentManager(),"tag");
            }
        });

        txt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),edit_profile.class);
                startActivity(intent);
            }
        });


        SharedPreferences preferences = requireActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        String user_id = preferences.getString("User_id",null);
        String user_name = preferences.getString("Username",null);
        String Bio = preferences.getString("Bio",null);
        String Address = preferences.getString("Address",null);
        String Phone_no = preferences.getString("Phone_no",null);
        String Email = preferences.getString("Email",null);
        String Profilepicture = preferences.getString("Profilepicture",null);




        txtt1.setText(user_name);
        txtt2.setText(Address);
        txtt3.setText(Phone_no);
        txtt4.setText(Email);
        txtt5.setText(Bio);
        Picasso.get().load(Profilepicture).placeholder(R.drawable.profile_dp).into(txt02);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profiletool, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.profilemenu01){
            Bottomsheet b =new Bottomsheet();
            b.show(getActivity().getSupportFragmentManager(),"tag");
        }


        return super.onOptionsItemSelected(item);
    }
}



