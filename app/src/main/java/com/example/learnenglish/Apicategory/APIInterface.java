package com.example.learnenglish.Apicategory;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {
    @GET("/English_project/BuyBook/buybook.php")
    Call<Booklist> getall();

    @FormUrlEncoded
    @POST("/English_project/BuyBook/SearchBook.php")
    Call<Booklist> buybooksearch(@Field("search") String search);

    @FormUrlEncoded
    @POST("/English_project/BuyBook/select.php")
    Call<Booklist> selectbook(@Field("id") String name);


    @FormUrlEncoded
    @POST("/English_project/Cart/getcart.php")
    Call<Resultcart> selectcart(@Field("User_id") String User_id,
                                @Field("id") String category_id,
                                @Field("status") String status_id);


    @FormUrlEncoded
    @POST("/English_project/Cart/cartshow.php")
    Call<Reshowcart>showcart(@Field("User_id") String User_id);


    @FormUrlEncoded
    @POST("/English_project/Cart/Remove.php")
    Call<Reshowcart>removecart(@Field("User_id") String User_id,
                               @Field("cart_id") String cart_id);

    @FormUrlEncoded
    @POST("/English_project/Cart/ordercart.php")
    Call<Reshowcart>orcart(@Field("User_id") String User_id,
                           @Field("cart_id") String cart_id);



    @FormUrlEncoded
    @POST("/English_project/Payment/Payment.php")
    Call<ResultBookPay>bopayment(@Field("id") String id,
                                 @Field("cart_id") String cart_id,
                                 @Field("User_id") String User_id,
                                 @Field("payment_type") String payment_type,
                                 @Field("payment_status") String payment_status);


    @FormUrlEncoded
    @POST("/English_project/Payment/getpayid.php")
    Call<ResultBookPay>bogetpay(@Field("id") String id,
                                 @Field("cart_id") String cart_id,
                                 @Field("User_id") String User_id,
                                 @Field("payment_type") String payment_type,
                                 @Field("payment_status") String payment_status);


    @FormUrlEncoded
    @POST("/English_project/Payment/paymentorder.php")
    Call<ResultorderPay>orpaydata(@Field("User_id") String User_id,
                                  @Field("id") String id,
                                  @Field("payment_id") String payment_id,
                                  @Field("status") String status,
                                  @Field("odate") String odate,
                                  @Field("otime") String otime);


    @FormUrlEncoded
    @POST("/English_project/Order/ordershow.php")
    Call<Resultmyorder>myorders(@Field("User_id") String User_id);



    @GET("/English_project/Tence.php")
    Call<ResultTence> getTence();

    @GET("/English_project/AllTancedetailes/Clock.php")
    Call<ResultClock> getClock();

    @GET("/English_project/Shortnote.php")
    Call<ResultShortnote> getShortnote();

    @GET("/English_project/AllTancedetailes/AllTance.php")
    Call<Resultaence> getspt();


    @GET("/English_project/SmartEnglish.php")
    Call<ResultSmartEnglish> getSmartEng();


    @FormUrlEncoded
    @POST("/English_project/REGISTRATION/Registration.php")
    Call<Resultregistration>Register_Data(@Field("User_name") String userName,
                                          @Field("Email") String email,
                                          @Field("Password") String password);



    @FormUrlEncoded
    @POST("/English_project/REGISTRATION/RegiUpdate.php")
    Call<Resultregistration>updatereg(@Field("User_name") String userName,
                             @Field("Email") String email,
                             @Field("Phoneno") String phone_no,
                             @Field("Bio") String bio,
                             @Field("Address") String address,
                             @Field("User_id") String uid);
    @FormUrlEncoded
    @POST("/English_project/REGISTRATION/Login.php")
    Call<Resultregistration>login(@Field("User_name") String userName,
                                  @Field("Password") String password);


    @FormUrlEncoded
    @POST("/English_project/REGISTRATION/ForgetPassword.php")
    Call<Resultregistration>forget(@Field("Phoneno") String Phoneno);


    @FormUrlEncoded
    @POST("/English_project/REGISTRATION/ResetPassword.php")
    Call<Resultregistration> resetpassword(@Field("User_id") String userid,
                                           @Field("Password") String password);


    @FormUrlEncoded
    @POST("/English_project/Feedback/feedback.php")
    Call<ResultFeedback> feedback(@Field("User_id") String User_id,
                                  @Field("Feedback_comments") String feedbackComments,
                                  @Field("Feedback_rating") String feedbackRating);


    @Multipart
    @POST("/English_project/Profilepic.php")
    Call<ResultImage> upload(@Part MultipartBody.Part image,
                             @Part("User_id")RequestBody uid);



    @Multipart
    @POST("/English_project/REGISTRATION/getupimg.php")
    Call<Resultregistration> updimg(@Field("User_id") String User_id);





}

