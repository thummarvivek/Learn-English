
package com.example.learnenglish.Apicategory;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reshowcart {

    @SerializedName("CartBook")
    @Expose
    private List<CartBook> cartBook;

    public List<CartBook> getCartBook() {
        return cartBook;
    }

    public void setCartBook(List<CartBook> cartBook) {
        this.cartBook = cartBook;
    }

}
