
package com.example.learnenglish.Apicategory;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultShortnote {

    @SerializedName("ShortNotes")
    @Expose
    private List<ShortNote> shortNotes;

    public List<ShortNote> getShortNotes() {
        return shortNotes;
    }

    public void setShortNotes(List<ShortNote> shortNotes) {
        this.shortNotes = shortNotes;
    }

}
