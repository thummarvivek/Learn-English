
package com.example.learnenglish.Apicategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SmartEnglish1 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Sentence")
    @Expose
    private String sentence;
    @SerializedName("Translate")
    @Expose
    private String translate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

}
