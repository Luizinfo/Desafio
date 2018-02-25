package com.zinfosoftware.b2w.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zinfosoftware.b2w.auxiliar.Logs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class ConexaoHttp {

    private Context ct;
    private String TAG = "ConexaoHttp";

    ConexaoHttp(Context ct) {
        this.ct = ct;
    }

    String getUrl(String Url) throws Exception {
        String dados = "";
        InputStream stream = null;
        HttpURLConnection objUrlConnection = null;

        try {

            URL url = new URL(Url);
            objUrlConnection = (HttpURLConnection) url.openConnection();
            objUrlConnection.setConnectTimeout(10000);
            objUrlConnection.setRequestMethod("GET");
            objUrlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            objUrlConnection.setRequestProperty("Security", "Origin: https://www.americanas.com.br");

            objUrlConnection.connect();

            if (objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
                    objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_OK
                    && objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                //ler erro
                stream = objUrlConnection.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                StringBuffer sb = new StringBuffer();
                String linha = "";

                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                dados = sb.toString();
                br.close();
                throw new RuntimeException("HTTP error code : "
                        + objUrlConnection.getResponseCode() + " Causa: " + dados);
            }

            stream = objUrlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuffer sb = new StringBuffer();
            String linha = "";

            while ((linha = br.readLine()) != null) {
                sb.append(linha);
            }
            dados = sb.toString();
            br.close();
        } catch (Exception er) {
            Logs.Erro(TAG, er.getMessage(), ct);
            throw er;

        } finally {
            if (stream != null)
                stream.close();
            if (objUrlConnection != null)
                objUrlConnection.disconnect();
        }

        return dados;

    }

    Bitmap getImg(String Url) throws Exception {
        Bitmap img = null;
        String dados = "";
        InputStream stream = null;
        HttpURLConnection objUrlConnection = null;

        try {

            URL url = new URL(Url);
            objUrlConnection = (HttpURLConnection) url.openConnection();
            objUrlConnection.setConnectTimeout(10000);
            objUrlConnection.setRequestMethod("GET");
            objUrlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            objUrlConnection.setRequestProperty("Security", "Origin: https://www.americanas.com.br");

            objUrlConnection.connect();

            if (objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
                    objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_OK
                    && objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                //ler erro
                stream = objUrlConnection.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                StringBuffer sb = new StringBuffer();
                String linha = "";

                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                dados = sb.toString();
                br.close();
                throw new RuntimeException("HTTP error code : "
                        + objUrlConnection.getResponseCode() + " Causa: " + dados);
            }

            stream = objUrlConnection.getInputStream();
            img = BitmapFactory.decodeStream(stream);
        } catch (Exception er) {
            Logs.Erro(TAG, er.getMessage(), ct);
            throw er;

        } finally {
            if (stream != null)
                stream.close();
            if (objUrlConnection != null)
                objUrlConnection.disconnect();
        }

        return img;

    }

    String postUrl(String Url, String json) throws Exception {
        String dados = "";
        InputStream stream = null;
        HttpURLConnection objUrlConnection = null;

        try {
            URL url = new URL(Url);
            objUrlConnection = (HttpURLConnection) url.openConnection();
            objUrlConnection.setConnectTimeout(10000);
            objUrlConnection.setRequestMethod("POST");
            objUrlConnection.setDoOutput(true);
            objUrlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            objUrlConnection.connect();

            OutputStream os = objUrlConnection.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            if (objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
                    objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_OK
                    && objUrlConnection.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                //ler erro
                stream = objUrlConnection.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                StringBuffer sb = new StringBuffer();
                String linha = "";

                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                dados = sb.toString();
                br.close();
                throw new RuntimeException("HTTP error code : "
                        + objUrlConnection.getResponseCode() + " Causa: " + dados);
            }

            stream = objUrlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuffer sb = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                sb.append(linha);
            }
            dados = sb.toString();
            br.close();
        } catch (Exception e) {
            Logs.Erro(TAG, e.getMessage(), ct);
            throw e;
        } finally {
            if (stream != null)
                stream.close();
            if (objUrlConnection != null)
                objUrlConnection.disconnect();
        }

        return dados;

    }
}

