package com.zinfosoftware.b2w.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Criado por almeidala em 23/02/2018.
 */

public class ConsultaProd {
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("installment")
    @Expose
    private Installment installment;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }
}
