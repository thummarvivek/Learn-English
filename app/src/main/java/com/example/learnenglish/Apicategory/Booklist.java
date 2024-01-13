
package com.example.learnenglish.Apicategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class Booklist {

        @SerializedName("Books_data")
        @Expose
        private List<Book> book =null;

        public List<Book> getBook() {
            return book;
        }

        public void setBook(List<Book> book) {
            this.book = book;
        }

    }

