package com.zinfosoftware.b2w.network;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Criado por almeidala em 24/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConexaoHttpTest {
    @Mock
    private Context mMockContext;

    @Test
    public void getUrl() throws Exception {
        ConexaoHttp conexaoHttp = new ConexaoHttp(mMockContext);
        String ret = conexaoHttp.getUrl("https://mystique-v1-americanas.b2w.io/mystique/nanookautocomplete?q=boneco&type=1&numsugestoes=7&numprods=10");
        assertEquals(true, (ret != null || !ret.isEmpty()));

    }

    @Test
    public void getImg() throws Exception {
        ConexaoHttp conexaoHttp = new ConexaoHttp(mMockContext);
        conexaoHttp.getImg("https://images-americanas.b2w.io/produtos/01/00/sku/29874/4/29874416_1GG.jpg");
    }

    @Test
    public void postUrl() throws Exception {

    }

}