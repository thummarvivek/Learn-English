package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.ResultClock;
import com.example.learnenglish.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import java.util.Locale;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clock extends AppCompatActivity {
    androidx.constraintlayout.widget.ConstraintLayout next;

    ProgressDialog progressDialog;
    TextView am01,ams001,pm01,pms001,tta1,tta2,tta3,tta4,tta5,tta6,tta7,tta8,tta9,tta10,tta11,tta12,tta13,tta14,tta15,tta16,tta17,tta18,tta19,tta20,tta21,tta22,tta23,ttb1,ttb2,ttb3,ttb4,ttb5,ttb6,ttb7,ttb8,ttb9,ttb10,ttb11,ttb12,ttb13,ttb14,ttb15,ttb16,ttb17,ttb18,ttb19,ttb20,ttb21,ttb22,ttb23,ttc1,ttc2,ttc3,ttc4,ttc5,ttc6,ttc7,ttc8,ttc9,ttc10,ttc11,ttc12,ttc13,ttc14,ttc15,ttc16,ttc17,ttc18,ttc19,ttc20,ttc21,ttc22,ttc23;
    private RewardedAd rewardedAd;
    SwipeListener swipeListener;
    ArrayList<com.example.learnenglish.Apicategory.Clock> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        next=findViewById(R.id.swipclo);
        swipeListener = new SwipeListener(next);
        arrayList =new ArrayList<>();

        progressDialog =new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Your screen are loading");
        progressDialog.show();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadred();

        am01 =findViewById(R.id.Amshow);
        pm01 =findViewById(R.id.Pmshow);
        ams001 =findViewById(R.id.ams);
        pms001 =findViewById(R.id.pms);
        tta1 =findViewById(R.id.tta1);
        tta2 =findViewById(R.id.tta2);
        tta3 =findViewById(R.id.tta3);
        tta4 =findViewById(R.id.tta4);
        tta5 =findViewById(R.id.tta5);
        tta6 =findViewById(R.id.tta6);
        tta7 =findViewById(R.id.tta7);
        tta8 =findViewById(R.id.tta8);
        tta9 =findViewById(R.id.tta9);
        tta10 =findViewById(R.id.tta10);
        tta11 =findViewById(R.id.tta11);
        tta12 =findViewById(R.id.tta12);
        tta13 =findViewById(R.id.tta13);
        tta14 =findViewById(R.id.tta14);
        tta15 =findViewById(R.id.tta15);
        tta16 =findViewById(R.id.tta16);
        tta17 =findViewById(R.id.tta17);
        tta18 =findViewById(R.id.tta18);
        tta19 =findViewById(R.id.tta19);
        tta20 =findViewById(R.id.tta20);
        tta21 =findViewById(R.id.tta21);
        tta22 =findViewById(R.id.tta22);
        tta23 =findViewById(R.id.tta23);
        ttb1 =findViewById(R.id.ttb1);
        ttb2 =findViewById(R.id.ttb2);
        ttb3 =findViewById(R.id.ttb3);
        ttb4 =findViewById(R.id.ttb4);
        ttb5 =findViewById(R.id.ttb5);
        ttb6 =findViewById(R.id.ttb6);
        ttb7 =findViewById(R.id.ttb7);
        ttb8 =findViewById(R.id.ttb8);
        ttb9 =findViewById(R.id.ttb9);
        ttb10 =findViewById(R.id.ttb10);
        ttb11 =findViewById(R.id.ttb11);
        ttb12 =findViewById(R.id.ttb12);
        ttb13 =findViewById(R.id.ttb13);
        ttb14 =findViewById(R.id.ttb14);
        ttb15 =findViewById(R.id.ttb15);
        ttb16 =findViewById(R.id.ttb16);
        ttb17 =findViewById(R.id.ttb17);
        ttb18 =findViewById(R.id.ttb18);
        ttb19 =findViewById(R.id.ttb19);
        ttb20 =findViewById(R.id.ttb20);
        ttb21 =findViewById(R.id.ttb21);
        ttb22 =findViewById(R.id.ttb22);
        ttb23 =findViewById(R.id.ttb23);
        ttc1 =findViewById(R.id.ttc1);
        ttc2 =findViewById(R.id.ttc2);
        ttc3 =findViewById(R.id.ttc3);
        ttc4 =findViewById(R.id.ttc4);
        ttc5 =findViewById(R.id.ttc5);
        ttc6 =findViewById(R.id.ttc6);
        ttc7 =findViewById(R.id.ttc7);
        ttc8 =findViewById(R.id.ttc8);
        ttc9 =findViewById(R.id.ttc9);
        ttc10 =findViewById(R.id.ttc10);
        ttc11 =findViewById(R.id.ttc11);
        ttc12 =findViewById(R.id.ttc12);
        ttc13 =findViewById(R.id.ttc13);
        ttc14 =findViewById(R.id.ttc14);
        ttc15 =findViewById(R.id.ttc15);
        ttc16 =findViewById(R.id.ttc16);
        ttc17 =findViewById(R.id.ttc17);
        ttc18 =findViewById(R.id.ttc18);
        ttc19 =findViewById(R.id.ttc19);
        ttc20 =findViewById(R.id.ttc20);
        ttc21 =findViewById(R.id.ttc21);
        ttc22 =findViewById(R.id.ttc22);
        ttc23 =findViewById(R.id.ttc23);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<ResultClock> call =apiInterface.getClock();
        call.enqueue(new Callback<ResultClock>() {
            @Override
            public void onResponse(Call<ResultClock> call, Response<ResultClock> response) {
                arrayList= (ArrayList) response.body().getClock();
                progressDialog.dismiss();

                am01.setText(arrayList.get(0).getAm());
                pm01.setText(arrayList.get(0).getPm());
                ams001.setText(arrayList.get(0).getClose());
                pms001.setText(arrayList.get(0).getClose());
                tta1.setText(arrayList.get(0).getTa1());
                tta2.setText(arrayList.get(0).getTa2());
                tta3.setText(arrayList.get(0).getTa3());
                tta4.setText(arrayList.get(0).getTa4());
                tta5.setText(arrayList.get(0).getTa5());
                tta6.setText(arrayList.get(0).getTa6());
                tta7.setText(arrayList.get(0).getTa7());
                tta8.setText(arrayList.get(0).getTa8());
                tta9.setText(arrayList.get(0).getTa9());
                tta10.setText(arrayList.get(0).getTa10());
                tta11.setText(arrayList.get(0).getTa11());
                tta12.setText(arrayList.get(0).getTa12());
                tta13.setText(arrayList.get(0).getTa13());
                tta14.setText(arrayList.get(0).getTa14());
                tta15.setText(arrayList.get(0).getTa15());
                tta16.setText(arrayList.get(0).getTa16());
                tta17.setText(arrayList.get(0).getTa17());
                tta18.setText(arrayList.get(0).getTa18());
                tta19.setText(arrayList.get(0).getTa19());
                tta20.setText(arrayList.get(0).getTa20());
                tta21.setText(arrayList.get(0).getTa21());
                tta22.setText(arrayList.get(0).getTa22());
                tta23.setText(arrayList.get(0).getTa23());
                ttb1.setText(arrayList.get(0).getIt());
                ttb2.setText(arrayList.get(0).getIt());
                ttb3.setText(arrayList.get(0).getIt());
                ttb4.setText(arrayList.get(0).getIt());
                ttb5.setText(arrayList.get(0).getIt());
                ttb6.setText(arrayList.get(0).getIt());
                ttb7.setText(arrayList.get(0).getIt());
                ttb8.setText(arrayList.get(0).getIt());
                ttb9.setText(arrayList.get(0).getIt());
                ttb10.setText(arrayList.get(0).getIt());
                ttb11.setText(arrayList.get(0).getIt());
                ttb12.setText(arrayList.get(0).getIt());
                ttb13.setText(arrayList.get(0).getIt());
                ttb14.setText(arrayList.get(0).getIt());
                ttb15.setText(arrayList.get(0).getIt());
                ttb16.setText(arrayList.get(0).getIt());
                ttb17.setText(arrayList.get(0).getIt());
                ttb18.setText(arrayList.get(0).getIt());
                ttb19.setText(arrayList.get(0).getIt());
                ttb20.setText(arrayList.get(0).getIt());
                ttb21.setText(arrayList.get(0).getIt());
                ttb22.setText(arrayList.get(0).getIt());
                ttb23.setText(arrayList.get(0).getIt());
                ttc1.setText(arrayList.get(0).getT1());
                ttc2.setText(arrayList.get(0).getT2());
                ttc3.setText(arrayList.get(0).getT3());
                ttc4.setText(arrayList.get(0).getT4());
                ttc5.setText(arrayList.get(0).getT5());
                ttc6.setText(arrayList.get(0).getT6());
                ttc7.setText(arrayList.get(0).getT7());
                ttc8.setText(arrayList.get(0).getT8());
                ttc9.setText(arrayList.get(0).getT9());
                ttc10.setText(arrayList.get(0).getT10());
                ttc11.setText(arrayList.get(0).getT11());
                ttc12.setText(arrayList.get(0).getT12());
                ttc13.setText(arrayList.get(0).getT13());
                ttc14.setText(arrayList.get(0).getT14());
                ttc15.setText(arrayList.get(0).getT15());
                ttc16.setText(arrayList.get(0).getT16());
                ttc17.setText(arrayList.get(0).getT17());
                ttc18.setText(arrayList.get(0).getT18());
                ttc19.setText(arrayList.get(0).getT19());
                ttc20.setText(arrayList.get(0).getT20());
                ttc21.setText(arrayList.get(0).getT21());
                ttc22.setText(arrayList.get(0).getT22());
                ttc23.setText(arrayList.get(0).getT23());

            }

            @Override
            public void onFailure(Call<ResultClock> call, Throwable t) {
                Toast.makeText(Clock.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.show();

            }
        });


    }
    private  class SwipeListener implements View.OnTouchListener{
        GestureDetector gestureDetector;

        SwipeListener(View view){
            int THRESHOLD = 100;
            int VELOCITY_THRESHOLD = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                    return super.onFling(e1, e2, velocityX, velocityY);
                    float xdiff = e2.getX() - e1.getX();
                    float ydiff = e2.getY() - e1.getY();

                    try {
                        //check condition
                        if(Math.abs(xdiff)> Math.abs(ydiff)){

                            if (Math.abs(xdiff) > THRESHOLD
                                    &&Math.abs(velocityX) >VELOCITY_THRESHOLD){

                                if (xdiff > 0){

                                    if (rewardedAd != null) {
                                        Activity activityContext = Clock.this;
                                        rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                            @Override
                                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                                // Handle the reward.
//                                                Log.d(TAG, "The user earned the reward.");
                                                int rewardAmount = rewardItem.getAmount();
                                                String rewardType = rewardItem.getType();
                                            }
                                        });
                                    } else {
//                                        Log.d(TAG, "The rewarded ad wasn't ready yet.");
                                        Toast.makeText(Clock.this, "left swipe", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Clock.this,Grammar.class);
                                        startActivity(intent);
                                    }
                                    rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                        @Override
                                        public void onAdClicked() {
                                            // Called when a click is recorded for an ad.
//                                            Log.d(TAG, "Ad was clicked.");
                                        }

                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            // Called when ad is dismissed.
                                            // Set the ad reference to null so you don't show the ad a second time.
//                                            Log.d(TAG, "Ad dismissed fullscreen content.");
                                            Intent intent=new Intent(Clock.this,Grammar.class);
                                            startActivity(intent);
                                            loadred();
//                                            rewardedAd = null;
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            // Called when ad fails to show.
//                                            Log.e(TAG, "Ad failed to show fullscreen content.");
                                            rewardedAd = null;
                                        }

                                        @Override
                                        public void onAdImpression() {
                                            // Called when an impression is recorded for an ad.
//                                            Log.d(TAG, "Ad recorded an impression.");
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            // Called when ad is shown.
//                                            Log.d(TAG, "Ad showed fullscreen content.");
                                        }
                                    });

                                    //swipe left


                                }else {

                                    //swipe right

                                    if (rewardedAd != null) {
                                        Activity activityContext = Clock.this;
                                        rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                            @Override
                                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                                // Handle the reward.
//                                                Log.d(TAG, "The user earned the reward.");
                                                int rewardAmount = rewardItem.getAmount();
                                                String rewardType = rewardItem.getType();
                                            }
                                        });
                                    } else {
//                                        Log.d(TAG, "The rewarded ad wasn't ready yet.");
                                        Toast.makeText(Clock.this, "swipe right", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Clock.this,Smartenglish.class);
                                        startActivity(intent);
                                    }
                                    rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                        @Override
                                        public void onAdClicked() {
                                            // Called when a click is recorded for an ad.
//                                            Log.d(TAG, "Ad was clicked.");
                                        }

                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            // Called when ad is dismissed.
                                            // Set the ad reference to null so you don't show the ad a second time.
//                                            Log.d(TAG, "Ad dismissed fullscreen content.");
                                            Intent intent=new Intent(Clock.this,Smartenglish.class);
                                            startActivity(intent);
                                            loadred();
//                                            rewardedAd = null;
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            // Called when ad fails to show.
//                                            Log.e(TAG, "Ad failed to show fullscreen content.");
                                            rewardedAd = null;
                                        }

                                        @Override
                                        public void onAdImpression() {
                                            // Called when an impression is recorded for an ad.
//                                            Log.d(TAG, "Ad recorded an impression.");
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            // Called when ad is shown.
//                                            Log.d(TAG, "Ad showed fullscreen content.");
                                        }
                                    });


                                }
                                return true;
                            }
                        }
                        else {
                            if (Math.abs(ydiff) > THRESHOLD
                                    &&Math.abs(velocityY) >VELOCITY_THRESHOLD){
                                if (ydiff > 0){

                                    //Swipe up
                                    Toast.makeText(Clock.this, "up working", Toast.LENGTH_SHORT).show();


                                }else {

                                    //Swipe down
                                    Toast.makeText(Clock.this, "down working", Toast.LENGTH_SHORT).show();

                                }
                                return true;

                            }

                        }

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);

        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
    public void loadred(){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-8480695512951753/4250166117",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
//                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }
}