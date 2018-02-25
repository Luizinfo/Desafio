package com.zinfosoftware.b2w.network;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.ConsultaProdutos;
import com.zinfosoftware.b2w.model.Produto;
import com.zinfosoftware.b2w.mvp.MVP;

import java.util.List;

/**
 * Criado por almeidala em 25/02/2018.
 */

public class TarefaInBackground extends AsyncTask<String, Void, List<Produto>> {
    //variaveis
    private String TAG = "TarefaInBackground";
    private MVP.IPresenter presenter;
    private ProgressDialog Dialog;
    private String erro;

    public TarefaInBackground(MVP.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute() {
        try {
            Dialog = new ProgressDialog(presenter.getContext());
            Dialog.setMessage("Pesquisando Produtos...");
            Dialog.setCancelable(false);
            Dialog.show();
        } catch (Exception er) {

        }
        erro = "";
    }

    @Override
    protected List<Produto> doInBackground(String... params) {

        try {
            String busca = params[0];
            ConsultaServico consultaServico = new ConsultaServico(presenter.getContext());
            ConsultaProdutos ret = consultaServico.getProdutos(busca, 10);
            return ret.getProdutos();
        } catch (Exception er) {
            erro = "Erro ao receber dados!";
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Produto> lista) {

        try {
            Dialog.dismiss();
        } catch (Exception er) {
            Logs.Erro(TAG, er.getMessage(), presenter.getContext());
        }

        if (erro.equals("")) {
            if (lista == null || lista.size() < 1)
                presenter.showToast("Produto não localizado");
            presenter.carregarLista(lista);
        } else {
            presenter.showToast(erro);
            Logs.Erro(TAG, erro, presenter.getContext());
        }
    }

    @Override
    protected void onCancelled() {

        super.onCancelled();
    }
}
