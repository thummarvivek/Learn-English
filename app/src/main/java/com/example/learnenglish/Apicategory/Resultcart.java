
package com.example.learnenglish.Apicategory;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Resultcart {

    @SerializedName("BookCart")
    @Expose
    private List<BookCart> bookCart;

    public List<BookCart> getBookCart() {
        return bookCart;
    }

    public void setBookCart(List<BookCart> bookCart) {
        this.bookCart = bookCart;
    }

}
