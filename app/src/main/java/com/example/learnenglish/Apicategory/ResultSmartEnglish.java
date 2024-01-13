
package com.example.learnenglish.Apicategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultSmartEnglish {

    @SerializedName("SmartenglishList")
    @Expose
    private List<SmartEnglish1> smartenglishList;

    public List<SmartEnglish1> getSmartenglishList() {
        return smartenglishList;
    }

    public void setSmartenglishList(List<SmartEnglish1> smartenglishList) {
        this.smartenglishList = smartenglishList;
    }

}
