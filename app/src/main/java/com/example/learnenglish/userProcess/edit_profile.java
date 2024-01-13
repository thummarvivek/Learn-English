package com.example.learnenglish.userProcess;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.learnenglish.Apicategory.APIInterface;
import com.example.learnenglish.Apicategory.Appclient;
import com.example.learnenglish.Apicategory.GetaddressIntentService;
import com.example.learnenglish.Apicategory.Image;
import com.example.learnenglish.Apicategory.Registration;
import com.example.learnenglish.Apicategory.ResultImage;
import com.example.learnenglish.Apicategory.Resultregistration;
import com.example.learnenglish.Path;
import com.example.learnenglish.R;
import com.example.learnenglish.homepage;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 103;
    private LocationAddressResultReceiver addressResultReceiver;
    private EditText txt3;

    ProgressBar progressBar ,progressBar2;


    private Location currentLocation;
    private LocationCallback locationCallback;

    public EditText txt1,txt2,txt4,txt5;
    public final int CAMERA_PIC_REQUEST = 100;

    ArrayList<Registration>arrayList;
    ArrayList<Image>arrayList2;

    String uridata;

    ImageView imageView;
    FloatingActionButton uploadbtn;
    ProgressDialog progressDialog;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        txt1=findViewById(R.id.username1);
        txt2=findViewById(R.id.bio);
        txt3=findViewById(R.id.Address2);
        txt4=findViewById(R.id.phone02);
        txt5=findViewById(R.id.mail02);
        btn=findViewById(R.id.profilebtn);
        progressBar =findViewById(R.id.editprogressBar);
        progressBar2 =findViewById(R.id.imgprogressBar);
        progressBar2.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        imageView = findViewById(R.id.profiledp);
        uploadbtn= findViewById(R.id.uploadbtn);
        uploadbtn.setVisibility(GONE);
        addressResultReceiver = new LocationAddressResultReceiver(new Handler());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            }
        };
        startLocationUpdates();
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        SharedPreferences Preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String user_id = Preferences.getString("User_id",null);
        String user_name = Preferences.getString("Username",null);
        String Bio = Preferences.getString("Bio", null);
        String Address = Preferences.getString("Address", null);
        String Phone_no = Preferences.getString("Phone_no", null);
        String Email = Preferences.getString("Email", null);

        txt1.setText(user_name);
        txt2.setText(Bio);
        txt3.setText(Address);
        txt4.setText(Phone_no);
        txt5.setText(Email);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    SharedPreferences Preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                      String user_id = Preferences.getString("User_id",null);
                      String user_name=txt1.getText().toString();
                      String Bio=txt2.getText().toString();
                      String Address=txt3.getText().toString();
                      String Phone_no=txt4.getText().toString();
                      String Email=txt5.getText().toString();

                      APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
                      Call<Resultregistration> call =apiInterface.updatereg(user_name,Email,Phone_no,Bio,Address,user_id);
                      call.enqueue(new Callback<Resultregistration>() {
                          @Override
                          public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {
                              Toast.makeText(edit_profile.this, "update successful", Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.VISIBLE);
                              btn.setVisibility(GONE);
                              arrayList=(ArrayList<Registration>)response.body().getRegistration();
                              SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                              SharedPreferences.Editor ed = sharedPreferences.edit();
                              ed.putString("User_id",arrayList.get(0).getUserId());
                              ed.putString("username",(String) arrayList.get(0).getUserName());
                              ed.putString("Bio", (String) arrayList.get(0).getBio());
                              ed.putString("Address", (String) arrayList.get(0).getAddress());
                              ed.putString("Phone_no", (String) arrayList.get(0).getPhoneno());
                              ed.putString("Email", (String) arrayList.get(0).getEmail());
                              ed.putBoolean("is_regi",true);
                              ed.apply();
                              notiy();
                              Intent intent=new Intent(edit_profile.this, homepage.class);
                              startActivity(intent);

                          }

                          @Override
                          public void onFailure(Call<Resultregistration> call, Throwable t) {
                              Toast.makeText(edit_profile.this, ""+t, Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(GONE);
                              btn.setVisibility(View.VISIBLE);

                          }
                      });

            }



        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                String user_id= sharedPreferences.getString("User_id",null);

                try {
                        File file = new File(uridata);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                        RequestBody uid=RequestBody.create(MediaType.parse("text/plain"),user_id);
                        Log.e("uploadlogerr"," "+user_id);
                        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                        Call<ResultImage> call = apiInterface.upload(fileupload,uid);
                        call.enqueue(new Callback<ResultImage>() {
                            @Override
                            public void onResponse(Call<ResultImage> call, Response<ResultImage> response) {
                                assert response.body() != null;
                                arrayList2=(ArrayList<Image>) response.body().getImages();
                                uploadbtn.setVisibility(View.GONE);
                                progressBar2.setVisibility(View.VISIBLE);
                                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed=preferences.edit();
                                ed.putString("Profilepicture", (String) arrayList2.get(0).getProfilePicture());
                                 ed.apply();
                                notimg();
//                                updateimgda();
                                Toast.makeText(edit_profile.this, "Your Profile is Upload", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResultImage> call, Throwable t) {
                                Toast.makeText(edit_profile.this, "Upload Fail"+t, Toast.LENGTH_SHORT).show();
                                uploadbtn.setVisibility(View.GONE);
                                progressBar2.setVisibility(GONE);
//                                updateimgda();
                                Log.e("Error",String.valueOf(t));

                            }
                        });


                }catch (Exception e){
                    Toast.makeText(edit_profile.this, "" + e, Toast.LENGTH_SHORT).show();
                    Log.e("errirore", String.valueOf(e));
                    uploadbtn.setVisibility(View.GONE);
                    progressBar2.setVisibility(GONE);
                    Toast.makeText(edit_profile.this, "ex : "+e, Toast.LENGTH_SHORT).show();


                }

            }
        });

    }

//    public void updateimgda(){
//        final  Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
//                String User_id= sharedPreferences.getString("User_id",null);
//                APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
//                Call<Resultregistration> call=apiInterface.updimg(User_id);
//                call.enqueue(new Callback<Resultregistration>() {
//                    @Override
//                    public void onResponse(Call<Resultregistration> call, Response<Resultregistration> response) {
//                        arrayList=(ArrayList<Registration>)response.body().getRegistration();
//                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor ed = sharedPreferences.edit();
//                        ed.putString("Profilepicture", (String) arrayList.get(0).getProfilePicture());
//                        ed.putBoolean("is_regi",true);
//                        Toast.makeText(edit_profile.this, ""+arrayList.get(0).getProfilePicture(), Toast.LENGTH_SHORT).show();
//                        ed.apply();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Resultregistration> call, Throwable t) {
//
//                    }
//                });
//            }
//        },2000);
//    }

    public void notiy(){
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
                    .setContentText("Edit Profile Are Updated")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Edit Profile Are Updated")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);

    }

    public void notimg(){
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
                        .setContentText("Profile Picture Are Updated")//this is notification message
                        .setChannelId(CHANNEL_ID)
                        .build();
                nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
            } else {
                notification = new Notification.Builder(this)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.drawable.smslogo)
                        .setSubText(currentDate) //set title of notification
                        .setContentText("Profile Picture Are Updated")//this is notification message
                        .build();


            }
            nm.notify(NOTIFICATION_ID, notification);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            uploadbtn.setVisibility(View.VISIBLE);

//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(uri, filePathColumn,null,null,null,null);
//            assert cursor != null;
//            cursor.moveToFirst();
//            int clumnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            uridata = cursor.getString(clumnIndex);
            uridata= Path.getPathFromUri(getApplicationContext(),uri);
            Log.e("IMGPATH",uridata);



        }
    }

    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            Intent  intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Your GPS is disabled! Would you like to enable it?")
//                    .setCancelable(false)
//                    .setPositiveButton("Enable GPS",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    Intent  intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                                    startActivity(intent);
//                                }
//                            }).setNegativeButton("Do nothing",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            });
//
//            AlertDialog alert = builder.create();
//            alert.show();
        }
        else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(200000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }
    @SuppressWarnings("MissingPermission")
    private void getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(edit_profile.this, "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, GetaddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
    int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission not granted, " + "restart the app if you want the feature", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == 0) {
                Log.d("Address", "Location null retrying");
                getAddress();
            }
            if (resultCode == 1) {
                Toast.makeText(edit_profile.this, "Address not found, ", Toast.LENGTH_SHORT).show();
            }
            String currentAdd = resultData.getString("address_result");
            showResults(currentAdd);
        }
    }
    private void showResults(String currentAdd) {
        txt3.setText(currentAdd);
    }
    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }
    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
