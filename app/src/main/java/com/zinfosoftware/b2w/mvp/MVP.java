package com.zinfosoftware.b2w.mvp;

import android.content.Context;

import com.zinfosoftware.b2w.model.Produto;

import java.util.List;

/**
 * Criado por almeidala em 25/02/2018.
 */

public interface MVP {
    interface IView {
        void showToast(String mensagem);

        void carregarLista(List<Produto> lista);
    }

    interface IModel {
        void atualizar(String text);
    }

    interface IPresenter {
        void showToast(String mensagem);

        void setView(MVP.IView view);

        Context getContext();

        void carregarLista(List<Produto> lista);

        void atualizar(String text);

        int dpToPx(int dp);
    }
}
