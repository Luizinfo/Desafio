package com.zinfosoftware.b2w.model;

/**
 * Criado por almeidala em 23/02/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installment {
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}


