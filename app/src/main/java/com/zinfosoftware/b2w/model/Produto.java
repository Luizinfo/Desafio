package com.zinfosoftware.b2w.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Criado por almeidala em 24/02/2018.
 */

public class Produto {
    @SerializedName("imagem")
    @Expose
    private String imagem;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("de")
    @Expose
    private String de;
    @SerializedName("original_id")
    @Expose
    private String originalId;
    @SerializedName("id_cat")
    @Expose
    private String idCat;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("preco")
    @Expose
    private String preco;
    @SerializedName("disponivel")
    @Expose
    private String disponivel;

    private Bitmap ImagemBitmap;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public Bitmap getImagemBitmap() {
        return ImagemBitmap;
    }

    public void setImagemBitmap(Bitmap imagemBitmap) {
        ImagemBitmap = imagemBitmap;
    }
}
