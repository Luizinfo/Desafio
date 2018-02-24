package com.zinfosoftware.b2w;

public final class Controle {

    // Define Intent usada para enviar broadcast.
    public static final String BROADCAST_ACTION = "com.okibrasil.market.BROADCAST";
    // Defines the key for the status "extra" in an Intent
    public static final String DATA_STATUS = "com.okibrasil.market.STATUS";
    // Defines the key for the log "extra" in an Intent
    public static final String STATUS_LOG = "com.okibrasil.market.LOG";
    //STATUS DA APLICAÇÃO
    // LISTA baixada
    public static final int ARQUIVO_BAIXADO = 0;
    public static final int IMAGEM_BAIXADA = 1;
    public static final int DOWNLOAD_FINALIZADO = 2;
    public static final int FORCAR_DOWNLOAD = 3;
    //GERACAO DE LOGS
    public static final boolean LOG = true;
    public static final boolean DEBUG = true;
    public static final long ALARME_ATUALIZACAO = 20 * 60 * 1000;// 2hs //20mm
    public static final long ALARME_ALERTA = 5 * 60 * 1000; // 5mim
    public static final String PREFERENCIAS = "Pref_Market";
    //public static String SERVIDOR = "http://10.99.26.90:8000/wsFieldIC/";
    //public static String SERVIDOR = "http://10.99.27.12/wsFieldIC/";
    //public static String SERVIDOR = "http://192.168.1.12/wsFieldIC/";
    public static String SERVIDOR = "https://restql-server-api-v1-americanas.b2w.io/run-query/";
    //public static String SERVIDOR = "https://pda.okibrasil.com/sb0050/";
}
