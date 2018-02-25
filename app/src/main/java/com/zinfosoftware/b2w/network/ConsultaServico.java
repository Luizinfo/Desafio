package com.zinfosoftware.b2w.network;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.zinfosoftware.b2w.Controle;
import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.ConsultaProd;
import com.zinfosoftware.b2w.model.ConsultaProdutos;
import com.zinfosoftware.b2w.model.Produto;

public class ConsultaServico {

    private Context ct;
    private String TAG = "ConsultaServico";

    public ConsultaServico(Context ct) {
        this.ct = ct;
    }

    public ConsultaProd getProduto(String id) throws Exception {

        //VARIAVEIS
        ConsultaProd consultaProd;
        String url = Controle.SERVIDOR_RESTQL + "catalogo/product-without-promotion/4?id=" + id +
                "&offerLimit=1&opn=&tags=prebf%2A%7CSUL_SUDESTE_CENTRO%7Clivros_prevenda";
        ConexaoHttp obj = new ConexaoHttp(ct);
        Gson gson = new Gson(); // gson original
        //implementando o gson com alteracao na forma de serialize e deserialize do tipo Date.
        //Gson gson = GsonHelper.createWebApiGson();
        String retorno;
        try {
            retorno = obj.getUrl(url);
            //retorno = mock.retorno;
            consultaProd = gson.fromJson(retorno, ConsultaProd.class);

            if (consultaProd != null)
                consultaProd.getProduct().setImagem(getImagem(consultaProd.getProduct().getResult().getImages().get(0).getMedium()));

        } catch (Exception e) {
            Logs.Erro(TAG, e.getMessage(), ct);
            throw e;
        }

        return consultaProd;
    }

    public ConsultaProdutos getProdutos(String descricao, int qtd) throws Exception {

        //VARIAVEIS
        ConsultaProdutos consultaProdutos;
        String url = Controle.SERVIDOR_MYSTIQUE + "mystique/nanookautocomplete?q=" + descricao +
                "&type=1&numsugestoes=2&numprods=" + qtd;
        ConexaoHttp obj = new ConexaoHttp(ct);
        Gson gson = new Gson();
        String retorno;
        try {
            retorno = obj.getUrl(url);
            consultaProdutos = gson.fromJson(retorno, ConsultaProdutos.class);

            for (Produto prod : consultaProdutos.getProdutos()
                    ) {
                prod.setImagemBitmap(getImagem(prod.getImagem()));
            }
        } catch (Exception e) {
            Logs.Erro(TAG, e.getMessage(), ct);
            throw e;
        }

        return consultaProdutos;
    }

    public Bitmap getImagem(String url) throws Exception {

        //VARIAVEIS
        ConexaoHttp obj = new ConexaoHttp(ct);
        Bitmap retArquivo;

        try {
            retArquivo = obj.getImg(url);
        } catch (Exception e) {
            Logs.Erro(TAG, e.getMessage(), ct);
            throw e;
        }

        return retArquivo;
    }
}
