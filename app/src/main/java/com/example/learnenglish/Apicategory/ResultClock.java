
package com.example.learnenglish.Apicategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultClock {

    @SerializedName("Clock")
    @Expose
    private List<Clock> clock;

    public List<Clock> getClock() {
        return clock;
    }

    public void setClock(List<Clock> clock) {
        this.clock = clock;
    }

}
