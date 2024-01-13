
package com.example.learnenglish.Apicategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pay {

    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
