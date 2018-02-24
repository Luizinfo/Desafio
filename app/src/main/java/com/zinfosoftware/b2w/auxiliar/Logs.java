package com.zinfosoftware.b2w.auxiliar;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.zinfosoftware.b2w.Controle;
import com.zinfosoftware.b2w.R;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logs {

    public static void Info(String TAG, String texto, Context ct) {
        try {
            if (Controle.LOG) {
                if (Controle.DEBUG) {
                    Log.i(TAG, texto);
                } else {
                    gravarLog(TAG, texto, ct);
                }
            }
        } catch (Exception e) {
            Erro("Logs", e.getMessage(), ct);
        }
    }

    public static void Erro(String TAG, String texto, Context ct) {

        try {
            if (Controle.DEBUG) {
                //grava em logcat
                Log.e(TAG, texto);
            } else {
                //gravar em arquivo
                gravarLog(TAG, texto, ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //EVIAR EMAIL.
        }

    }

    private static void gravarLog(String TAG, String texto, Context ct) {
        final File DATABASE_DIRECTORY = new File(Environment.getExternalStorageDirectory(), "IC");
        FileWriter fileWriter = null;

        try {
            // Obtém o estado do storage.
            String mediaState = Environment.getExternalStorageState();

            // Testa se ele está disponível.
            if (mediaState.equals(Environment.MEDIA_MOUNTED)) {
                //verifica de diretório existe se nao cria
                if (!DATABASE_DIRECTORY.exists()) {
                    boolean criado = DATABASE_DIRECTORY.mkdirs();
                    if (!criado)
                        throw new Exception("Diretorio nao criado!");
                }
                //fim

                //testa se arquivo existe.
                File file = new File(DATABASE_DIRECTORY, "LogsIC.txt");
                if (file.length() > 1048576) { //3145728 - para 3Mb
                    //apaga arquivo se for maior que 1Mb
                    file.delete();
                    // Cria o arquivo onde serão salvas as informações.
                    file = new File(DATABASE_DIRECTORY, "LogsIC.txt");
                }
                fileWriter = new FileWriter(file, true);

                Date dt = new Date();
                SimpleDateFormat postFormater1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String data = postFormater1.format(dt);
                String AppName = ct.getResources().getString(R.string.app_name);

                //fileWriter.append(data + " | " + AppName + " | " + TAG + " | " + texto + System.lineSeparator());
                fileWriter.write((data + " | " + AppName + " | " + TAG + " | " + texto));
                fileWriter.write(System.getProperty("line.separator"));
                // Escreve no arquivo.
                fileWriter.flush();

            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            // Fecha os recursos.
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
