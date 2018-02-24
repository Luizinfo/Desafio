package com.zinfosoftware.b2w.network;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Criado por almeidala em 24/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConexaoHttpTest {
    @Mock
    Context mMockContext;

    @Test
    public void getUrl() throws Exception {

        ConexaoHttp conexaoHttp = new ConexaoHttp(mMockContext);
        conexaoHttp.getUrl("");
    }

    @Test
    public void getImg() throws Exception {
    }

    @Test
    public void postUrl() throws Exception {
    }

}