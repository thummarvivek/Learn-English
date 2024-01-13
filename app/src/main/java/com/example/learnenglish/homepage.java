package com.example.learnenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import com.example.learnenglish.Adapters.ViewpageAdapter;
import com.example.learnenglish.fragment.AccountFragment;
import com.example.learnenglish.fragment.CartFragment;
import com.example.learnenglish.fragment.HomeFragment;
import com.example.learnenglish.fragment.SellFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homepage extends AppCompatActivity {
    Vibrator vibrator;
    BottomNavigationView bv;
    AdView adView;


    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        bv = findViewById(R.id.Bnv);
        viewPager=findViewById(R.id.pager);
        ViewpageAdapter viewpageAdapter=new ViewpageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewpageAdapter);
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        adView =findViewById(R.id.adview01);


        MobileAds.initialize(this);
        AdRequest adRequest =new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home)  {
                    viewPager.setCurrentItem(0);
                    vibrator.vibrate(50);
                    loadFrag(new HomeFragment(),true);

                } else if (id == R.id.nav_sell) {
                    viewPager.setCurrentItem(1);
                    vibrator.vibrate(50);
                    loadFrag(new SellFragment(),false);

                } else if (id == R.id.nav_cart) {
                    viewPager.setCurrentItem(2);
                    vibrator.vibrate(50);
                    loadFrag(new CartFragment(),false);

                } else if (id == R.id.nav_account){
                    viewPager.setCurrentItem(3);
                    vibrator.vibrate(50);
                    loadFrag(new AccountFragment(),false);
                }
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                bv.getMenu().findItem(R.id.nav_home).setChecked(true);
                                break;
                            case 1:
                                bv.getMenu().findItem(R.id.nav_sell).setChecked(true);
                                break;
                            case 2:
                                bv.getMenu().findItem(R.id.nav_cart).setChecked(true);
                                break;
                            case 3:
                                bv.getMenu().findItem(R.id.nav_account).setChecked(true);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                return true;
            }
        });
        bv.setSelectedItemId(R.id.nav_home);
    }
    @SuppressLint("SuspiciousIndentation")
    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container,fragment);
        else
            ft.replace(R.id.container,fragment);
        ft.commit();

    }
}
