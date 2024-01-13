
package com.example.learnenglish.Apicategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultTence {

    @SerializedName("Tence")
    @Expose
    private List<Tence> tence;

    public List<Tence> getTence() {
        return tence;
    }

    public void setTence(List<Tence> tence) {
        this.tence = tence;
    }

}
