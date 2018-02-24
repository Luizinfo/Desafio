package com.zinfosoftware.b2w;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.Produto;

import java.util.List;

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.MyViewHolder> {

    private static String TAG = "ProdAdapter";
    private Context mContext;
    private List<Produto> prodLista;

    public ProdAdapter(Context mContext, List<Produto> prodLista) {
        this.mContext = mContext;
        this.prodLista = prodLista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prod_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Produto produto = prodLista.get(position);

        holder.nome.setText(produto.getDescricao());

        holder.valor.setText("R$ " + produto.getPreco());
//        holder.parcelado.setText(produto.getInstallment().getResult().get(0).getQuantity().toString() +" x "+
//                produto.getInstallment().getResult().get(0).getValue().toString());
//        holder.avaliacao.setText("5");

        if (produto.getImagemBitmap() == null) {
            holder.imagem.setImageResource(R.drawable.teste);
        } else {
            holder.imagem.setImageBitmap(produto.getImagemBitmap());
        }
        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast("teste ok.");
                } catch (Exception e) {
                    Toast("Erro ao abrir detalhes.");
                    Logs.Erro(TAG, e.getMessage(), mContext);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return prodLista.size();
    }

    private void Toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, valor, parcelado, avaliacao;
        ImageView imagem;

        MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nome);
            valor = (TextView) view.findViewById(R.id.valor);
            parcelado = (TextView) view.findViewById(R.id.parcelado);
            imagem = (ImageView) view.findViewById(R.id.imagem);
            avaliacao = (TextView) view.findViewById(R.id.avaliacao);
        }

    }

}
