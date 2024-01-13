
package com.example.learnenglish.Apicategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderPayment {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("odate")
    @Expose
    private String odate;
    @SerializedName("otime")
    @Expose
    private String otime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

}
