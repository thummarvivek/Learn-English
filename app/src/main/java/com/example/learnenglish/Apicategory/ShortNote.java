
package com.example.learnenglish.Apicategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ShortNote {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("TextSecond")
    @Expose
    private String textSecond;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextSecond() {
        return textSecond;
    }

    public void setTextSecond(String textSecond) {
        this.textSecond = textSecond;
    }

}
