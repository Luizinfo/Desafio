package com.zinfosoftware.b2w.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.Toast;

import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.Produto;
import  com.zinfosoftware.b2w.network.TarefaInBackground;

import java.util.List;

/**
 * Criado por almeidala em 25/02/2018.
 */

public class Presenter implements MVP.IPresenter {
    private MVP.IModel model;
    private MVP.IView view;

    public Presenter(){
        model = new Model( this );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast(mensagem);
    }

    @Override
    public void setView(MVP.IView view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void carregarLista(List<Produto> lista) {
        view.carregarLista(lista);
    }

    @Override
    public void atualizar(String text) {
        model.atualizar(text);
    }

    @Override
    public int dpToPx(int dp) {
        Resources r =  ((Activity)view).getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
