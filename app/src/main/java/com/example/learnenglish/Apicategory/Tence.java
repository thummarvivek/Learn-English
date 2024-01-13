
package com.example.learnenglish.Apicategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tence {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Verb")
    @Expose
    private String verb;
    @SerializedName("Translate")
    @Expose
    private String translate;
    @SerializedName("PastForm")
    @Expose
    private String pastForm;
    @SerializedName("PastParticiple")
    @Expose
    private String pastParticiple;
    @SerializedName("PresentParticiple")
    @Expose
    private String presentParticiple;
    @SerializedName("PresentTence")
    @Expose
    private String presentTence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getPastForm() {
        return pastForm;
    }

    public void setPastForm(String pastForm) {
        this.pastForm = pastForm;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public String getPresentParticiple() {
        return presentParticiple;
    }

    public void setPresentParticiple(String presentParticiple) {
        this.presentParticiple = presentParticiple;
    }

    public String getPresentTence() {
        return presentTence;
    }

    public void setPresentTence(String presentTence) {
        this.presentTence = presentTence;
    }

}
