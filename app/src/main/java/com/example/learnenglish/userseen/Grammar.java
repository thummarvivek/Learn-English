package com.example.learnenglish.userseen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import java.util.Locale;

public class Grammar extends AppCompatActivity {

    androidx.constraintlayout.widget.ConstraintLayout next;

    private RewardedAd rewardedAd;
    TextToSpeech ttos;
    String st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12;

    ImageView ima1,ima2,ima3;

    TextView prst1,prst2,prst3,prst4,pst1,pst2,pst3,pst4,fst1,fst2,fst3,fst4;

    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        next=findViewById(R.id.swip2);
        swipeListener = new SwipeListener(next);

        st1   ="Present simple Tense";
        st2   ="Present Continuous Tense";
        st3   ="Present Perfect Tense";
        st4   ="Present Perfect Continuous Tense";
        st5   ="Past Simple Tense";
        st6   ="Past Continuous Tense";
        st7   ="Past Perfect Tense";
        st8   ="Past Perfect Continuous Tense";
        st9   ="Future Simple Tense";
        st10  ="Future Continuous Tense";
        st11  ="Future Perfect Tense";
        st12  ="Future Perfect Continuous Tense";

        prst1 =findViewById(R.id.present01);
        prst2 =findViewById(R.id.present02);
        prst3 =findViewById(R.id.present03);
        prst4 =findViewById(R.id.present04);
        pst1 =findViewById(R.id.past01);
        pst2 =findViewById(R.id.past02);
        pst3 =findViewById(R.id.past03);
        pst4 =findViewById(R.id.past04);
        fst1 =findViewById(R.id.future01);
        fst2 =findViewById(R.id.future02);
        fst3 =findViewById(R.id.future03);
        fst4 =findViewById(R.id.future04);
        ima1 =findViewById(R.id.ttxx02);
        ima2 =findViewById(R.id.ttxx03);
        ima3 =findViewById(R.id.ttxx04);

        ttos=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!=TextToSpeech.ERROR){
                    ttos.setLanguage(Locale.UK);
                }
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadred();

        prst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               st1 =prst1.getText().toString();
                ttos.speak(st1,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this,Simpleprt.class);
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
                        Intent intent=new Intent(Grammar.this,Simpleprt.class);
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
                //close admob

            }
        });

        prst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st2 =prst2.getText().toString();
                ttos.speak(st2,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Simplepct.class);
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
                        Intent intent=new Intent(Grammar.this, Simplepct.class);
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
//close admob

            }
        });

        prst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st3 =prst3.getText().toString();
                ttos.speak(st3,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Ppt.class);
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
                        Intent intent=new Intent(Grammar.this, Ppt.class);
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
//close admob
            }
        });

        prst4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st4 =prst4.getText().toString();
                ttos.speak(st4,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Ppct.class);
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
                        Intent intent=new Intent(Grammar.this, Ppct.class);
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
//close admob

            }
        });

        pst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st5 =pst1.getText().toString();
                ttos.speak(st5,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Past.class);
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
                        Intent intent=new Intent(Grammar.this, Past.class);
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
//close admob

            }
        });

        pst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st6 =pst2.getText().toString();
                ttos.speak(st6,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Pact.class);
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
                        Intent intent=new Intent(Grammar.this, Pact.class);
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
//close admob

            }
        });

        pst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st7 =pst3.getText().toString();
                ttos.speak(st7,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Papt.class);
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
                        Intent intent=new Intent(Grammar.this, Papt.class);
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
//close admob

            }
        });

        pst4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st8 =pst4.getText().toString();
                ttos.speak(st8,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Papct.class);
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
                        Intent intent=new Intent(Grammar.this, Papct.class);
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
//close admob

            }
        });

        fst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st9 =fst1.getText().toString();
                ttos.speak(st9,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Fst.class);
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
                        Intent intent=new Intent(Grammar.this, Fst.class);
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
//close admob

            }
        });

        fst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st10 =fst2.getText().toString();
                ttos.speak(st10,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Fct.class);
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
                        Intent intent=new Intent(Grammar.this, Fct.class);
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
//close admob

            }
        });

        fst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st11 =fst3.getText().toString();
                ttos.speak(st11,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Fpt.class);
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
                        Intent intent=new Intent(Grammar.this, Fpt.class);
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
//close admob
            }
        });

        fst4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st12 =fst4.getText().toString();
                ttos.speak(st12,TextToSpeech.QUEUE_FLUSH,null);
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Fpct.class);
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
                        Intent intent=new Intent(Grammar.this, Fpct.class);
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
//close admob

            }
        });

        ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this,Simpleprt.class);
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
                        Intent intent=new Intent(Grammar.this,Simpleprt.class);
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
//close admob
            }
        });

        ima2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Past.class);
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
                        Intent intent=new Intent(Grammar.this, Past.class);
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
//close admob
            }
        });

        ima3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = Grammar.this;
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
                    Intent intent=new Intent(Grammar.this, Fst.class);
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
                        Intent intent=new Intent(Grammar.this, Fst.class);
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
//close admob
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
                                        Activity activityContext = Grammar.this;
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
                                        Toast.makeText(Grammar.this, "left swipe", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Grammar.this,all_vocabulary_tence.class);
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
                                            Intent intent=new Intent(Grammar.this,all_vocabulary_tence.class);
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

                                    if (rewardedAd != null) {
                                        Activity activityContext = Grammar.this;
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
                                        Toast.makeText(Grammar.this, "swipe right", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Grammar.this,Clock.class);
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
                                            Intent intent=new Intent(Grammar.this,Clock.class);
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

                                    //swipe right

                                }
                                return true;
                            }
                        }
                        else {
                            if (Math.abs(ydiff) > THRESHOLD
                                    &&Math.abs(velocityY) >VELOCITY_THRESHOLD){
                                if (ydiff > 0){

                                    //Swipe up
                                    Toast.makeText(Grammar.this, "up working", Toast.LENGTH_SHORT).show();


                                }else {

                                    //Swipe down
                                    Toast.makeText(Grammar.this, "down working", Toast.LENGTH_SHORT).show();

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