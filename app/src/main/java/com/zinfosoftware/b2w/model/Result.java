package com.zinfosoftware.b2w.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Criado por almeidala em 23/02/2018.
 */

public class Result {

    @SerializedName("ruleId")
    @Expose
    private String ruleId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("annualCET")
    @Expose
    private Double annualCET;
    @SerializedName("interestRate")
    @Expose
    private Double interestRate;
    @SerializedName("interestAmount")
    @Expose
    private Double interestAmount;
    @SerializedName("taxedInterestRate")
    @Expose
    private Double taxedInterestRate;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getAnnualCET() {
        return annualCET;
    }

    public void setAnnualCET(Double annualCET) {
        this.annualCET = annualCET;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getTaxedInterestRate() {
        return taxedInterestRate;
    }

    public void setTaxedInterestRate(Double taxedInterestRate) {
        this.taxedInterestRate = taxedInterestRate;
    }

}
