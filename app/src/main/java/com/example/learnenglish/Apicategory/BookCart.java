
package com.example.learnenglish.Apicategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BookCart {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
