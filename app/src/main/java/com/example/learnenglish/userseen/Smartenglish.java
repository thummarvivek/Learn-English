package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.example.learnenglish.Adapters.RvAdapter3;
import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.ResultSmartEnglish;
import com.example.learnenglish.Apicategory.SmartEnglish1;
import com.example.learnenglish.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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



public class Smartenglish extends AppCompatActivity {


    androidx.constraintlayout.widget.ConstraintLayout next;

    ArrayList<SmartEnglish1> arrayList;

    SwipeRefreshLayout swipeLayout;

    ProgressDialog progressDialog;

    RecyclerView recyclerView;

    private RewardedAd rewardedAd;

    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartenglish);

        next =findViewById(R.id.swip3);
        swipeLayout = findViewById(R.id.swipeContainer3);

        swipeListener = new SwipeListener(next);

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





        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getApplicationContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<SmartEnglish1>();
        recyclerView=findViewById(R.id.rv3);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);

        Refresh();

    }

    public void Refresh(){
        APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
        Call<ResultSmartEnglish> call=apiInterface.getSmartEng();
        call.enqueue(new Callback<ResultSmartEnglish>() {
            @Override
            public void onResponse(Call<ResultSmartEnglish> call, Response<ResultSmartEnglish> response) {
                arrayList= (ArrayList<SmartEnglish1>) response.body().getSmartenglishList();
                progressDialog.dismiss();
                Log.e("check", "onResponse: "+arrayList.get(0).getSentence());
                Log.e("check", "onResponse: "+arrayList.get(0).getTranslate());
                RvAdapter3 rvAdapter=new RvAdapter3(Smartenglish.this, arrayList);
                recyclerView.setAdapter(rvAdapter);

            }

            @Override
            public void onFailure(Call<ResultSmartEnglish> call, Throwable t) {
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

                                    //swipe left
                                    if (rewardedAd != null) {
                                        Activity activityContext = Smartenglish.this;
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
                                        Toast.makeText(Smartenglish.this, "left swipe", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Smartenglish.this,Clock.class);
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
                                            Intent intent=new Intent(Smartenglish.this,Clock.class);
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

                                }else {

                                    //swipe right
                                    if (rewardedAd != null) {
                                        Activity activityContext = Smartenglish.this;
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
                                        Toast.makeText(Smartenglish.this, "swipe right", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Smartenglish.this,SpeakingSkill.class);
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
                                            Intent intent=new Intent(Smartenglish.this,SpeakingSkill.class);
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
                                    Toast.makeText(Smartenglish.this, "up working", Toast.LENGTH_SHORT).show();


                                }else {

                                    //Swipe down
                                    Toast.makeText(Smartenglish.this, "down working", Toast.LENGTH_SHORT).show();

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