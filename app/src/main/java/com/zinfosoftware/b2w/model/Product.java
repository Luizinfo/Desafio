package com.zinfosoftware.b2w.model;

/**
 * Criado por almeidala em 23/02/2018.
 */
import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("result")
    @Expose
    private Result_ result;

    private Bitmap imagem;

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Result_ getResult() {
        return result;
    }

    public void setResult(Result_ result) {
        this.result = result;
    }

    public Bitmap  getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap  imagem) {
        this.imagem = imagem;
    }
}
