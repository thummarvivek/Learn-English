
package com.example.learnenglish.Apicategory;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultFeedback {

    @SerializedName("feed")
    @Expose
    private List<Feed> feed;

    public List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }

}
