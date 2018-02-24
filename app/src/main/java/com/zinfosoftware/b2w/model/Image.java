package com.zinfosoftware.b2w.model;

/**
 * Criado por almeidala em 23/02/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("big")
    @Expose
    private String big;
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("extraLarge")
    @Expose
    private String extraLarge;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }

}
