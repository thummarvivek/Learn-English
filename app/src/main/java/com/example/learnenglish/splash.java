package com.example.learnenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class splash extends AppCompatActivity {

    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 100;
    ConnectivityManager connectivityManager;

    private static int Screen=4000;

    private RewardedAd rewardedAd;


    ImageView imageView;
    Animation animation;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadred();

        imageView=findViewById(R.id.imageView01);
        animation= AnimationUtils.loadAnimation(this,R.anim.alpha);
        imageView.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                Boolean b = preferences.getBoolean("is_regi", false);
                if (b) {
                    if (rewardedAd != null) {
                        Activity activityContext = splash.this;
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
                        Intent intent = new Intent(splash.this, homepage.class);
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
                            Intent intent = new Intent(splash.this, homepage.class);
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
//                    Intent intent = new Intent(splash.this, homepage.class);
//                    startActivity(intent);
                    finish();
                } else {
                    if (rewardedAd != null) {
                        Activity activityContext = splash.this;
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
                        Intent intent = new Intent(splash.this, MainActivity.class);
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
                            Intent intent = new Intent(splash.this, MainActivity.class);
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
//                    Intent intent = new Intent(splash.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }
        },Screen);


        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Wellcome To Learn English")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Wellcome To Learn English")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);


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