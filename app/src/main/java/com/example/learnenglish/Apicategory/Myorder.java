
package com.example.learnenglish.Apicategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Myorder {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("Book_edition")
    @Expose
    private String bookEdition;
    @SerializedName("Book_price")
    @Expose
    private String bookPrice;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order_id")
    @Expose
    private String orderId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
