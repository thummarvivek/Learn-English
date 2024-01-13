
package com.example.learnenglish.Apicategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Resultaence {

    @SerializedName("Aence")
    @Expose
    private List<Aence> aence;

    public List<Aence> getAence() {
        return aence;
    }

    public void setAence(List<Aence> aence) {
        this.aence = aence;
    }

}
