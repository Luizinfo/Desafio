package com.zinfosoftware.b2w.network;

import android.content.Context;
import android.graphics.Bitmap;

import com.zinfosoftware.b2w.model.ConsultaProd;
import com.zinfosoftware.b2w.model.ConsultaProdutos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaServicoTest {
    @Mock
    Context mMockContext;

    @Test
    public void getProdutos() throws Exception {
        ConsultaServico consultaServico = new ConsultaServico(mMockContext);
        ConsultaProdutos consultaProdutos = consultaServico.getProdutos("boneco", 10);
        assertNotEquals(null, consultaProdutos.getProdutos());
        assertEquals(10, consultaProdutos.getProdutos().size());
    }

    @Test
    public void getImagem() throws Exception {
        ConsultaServico consultaServico = new ConsultaServico(mMockContext);
        Bitmap imagem = consultaServico.getImagem("https://images-americanas.b2w.io/produtos/01/00/item/125580/9/125580926_1GG.jpg");
        //assertNotEquals(null, imagem);
    }

    @Test
    public void getProduto() throws Exception {
        ConsultaServico consultaServico = new ConsultaServico(mMockContext);
        ConsultaProd ret = consultaServico.getProduto("22986418");
        assertEquals("22986418", ret.getProduct().getResult().getId());

    }


}