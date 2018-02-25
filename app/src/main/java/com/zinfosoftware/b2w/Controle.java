package com.zinfosoftware.b2w;

public final class Controle {

    // Define Intent usada para enviar broadcast.
    public static final String BROADCAST_ACTION = "com.zinfosoftware.b2w.BROADCAST";
    //GERACAO DE LOGS
    public static final boolean LOG = true;
    public static final boolean DEBUG = true;
    public static final long ALARME = 20 * 60 * 1000;// 2hs //20mm
    public static final String PREFERENCIAS = "Pref_b2w";
    public static String SERVIDOR_RESTQL = "https://restql-server-api-v1-americanas.b2w.io/run-query/";
    public static String SERVIDOR_MYSTIQUE = "https://mystique-v1-americanas.b2w.io/";
}
