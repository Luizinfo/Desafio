package com.zinfosoftware.b2w.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Criado por almeidala em 24/02/2018.
 */

public class ConsultaProdutos {

    @SerializedName("produtos")
    @Expose
    private List<Produto> produtos = null;
    @SerializedName("tproc")
    @Expose
    private Double tproc;
    @SerializedName("version")
    @Expose
    private String version;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Double getTproc() {
        return tproc;
    }

    public void setTproc(Double tproc) {
        this.tproc = tproc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
