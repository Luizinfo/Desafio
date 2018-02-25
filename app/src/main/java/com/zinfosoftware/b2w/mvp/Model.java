package com.zinfosoftware.b2w.mvp;

import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.network.TarefaInBackground;

/**
 * Criado por almeidala em 25/02/2018.
 */

public class Model implements MVP.IModel {
    private static String TAG = "Model";
    private MVP.IPresenter presenter;

    public Model(MVP.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void atualizar(String text) {
        try {
            TarefaInBackground tarefa = new TarefaInBackground(presenter);
            tarefa.execute(text);
        } catch (Exception e) {
            Logs.Erro(TAG, "(Atualizar) " + e.getMessage(), presenter.getContext());
            presenter.showToast("Erro: " + e.getMessage());
        }
    }
}
