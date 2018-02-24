package com.zinfosoftware.b2w.auxiliar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LibIC {

    public static String FormataDataWS(Date dt) {

        SimpleDateFormat postFormater1 = new SimpleDateFormat("yyyy-MM-dd");
        String parData = postFormater1.format(dt);
        SimpleDateFormat postFormater2 = new SimpleDateFormat("HH:mm:ss");
        parData = parData + "T" + postFormater2.format(dt);

        return parData;
    }

    public static Long ConverterDtParaLong(String data) {
        Date dtTemp;

        String[] parts = data.split("T");
        String[] dataArray = parts[0].split("-");
        String[] timeArray = parts[1].split(":");

        int mDay, mMonth, mYear, mHour, mMinute, mSecond;
        mDay = Integer.parseInt(dataArray[2].toString());
        mMonth = Integer.parseInt(dataArray[1].toString());
        mYear = Integer.parseInt(dataArray[0].toString());
        mHour = Integer.parseInt(timeArray[0].toString());
        mMinute = Integer.parseInt(timeArray[1].toString());
        mSecond = Integer.parseInt(timeArray[2].toString().substring(0, 2));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, mDay);
        c.set(Calendar.MONTH, mMonth - 1);
        c.set(Calendar.YEAR, mYear);
        c.set(Calendar.HOUR_OF_DAY, mHour);
        c.set(Calendar.MINUTE, mMinute);
        c.set(Calendar.SECOND, mSecond);
        c.set(Calendar.MILLISECOND, 0);

        dtTemp = c.getTime();

        Long lDate = dtTemp.getTime();
        return lDate;
    }

    public static String ConverterDtParaString(String data) {

        String[] parts = data.split("T");
        String[] dataArray = parts[0].split("-");
        String[] timeArray = parts[1].split(":");

        int mDay, mMonth, mYear, mHour, mMinute, mSecond;
        mDay = Integer.parseInt(dataArray[2].toString());
        mMonth = Integer.parseInt(dataArray[1].toString());
        mYear = Integer.parseInt(dataArray[0].toString());
        mHour = Integer.parseInt(timeArray[0].toString());
        mMinute = Integer.parseInt(timeArray[1].toString());
        mSecond = Integer.parseInt(timeArray[2].toString().substring(0, 2));

        return String.valueOf(mDay) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mYear) +
                " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute) + ":" + String.valueOf(mSecond);
    }

    public static Long ConverterDtParaLongNativo(String data) {
        Date dtTemp;

        String[] parts = data.split(" ");
        String[] dataArray = parts[0].split("/");
        String[] timeArray = parts[1].split(":");

        int mDay, mMonth, mYear, mHour, mMinute, mSecond;
        mDay = Integer.parseInt(dataArray[0].toString());
        mMonth = Integer.parseInt(dataArray[1].toString());
        mYear = Integer.parseInt(dataArray[2].toString());
        mHour = Integer.parseInt(timeArray[0].toString());
        mMinute = Integer.parseInt(timeArray[1].toString());
        mSecond = Integer.parseInt(timeArray[2].toString().substring(0, 2));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, mDay);
        c.set(Calendar.MONTH, mMonth - 1);
        c.set(Calendar.YEAR, mYear);
        c.set(Calendar.HOUR_OF_DAY, mHour);
        c.set(Calendar.MINUTE, mMinute);
        c.set(Calendar.SECOND, mSecond);
        c.set(Calendar.MILLISECOND, 0);

        dtTemp = c.getTime();

        Long lDate = dtTemp.getTime();
        return lDate;
    }

    public static String formatavalor(double val1) {
        DecimalFormat df = new DecimalFormat("###.###");

        String val = df.format(val1);

        return val;
    }

    public static String caracteresEspeciais(String str) {

        // Troca os caracteres especiais da string por ""
        String[] caracteresEspeciais = {"'", "|", "&", "#", ""};

        for (int i = 0; i < caracteresEspeciais.length; i++) {
            str = str.replaceAll(caracteresEspeciais[i], "");
        }

        // Troca os espa�os no inicio por ""
        str = str.replaceAll("^\\s+", "");
        // Troca os espa�os no in�cio por ""
        str = str.replaceAll("\\s+$", "");
        // Troca os espa�os duplicados, tabula��es e etc por  " "
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public static String quebraString(String s) {

        int indice_abre = s.indexOf('[');
        int indice_fecha = s.lastIndexOf(']');
        String conteudo = s.substring(indice_abre + 1, indice_fecha);
        return conteudo;
    }

    public static Long ConverterParaLong(String data) {
        String[] dataArray = data.split("/");
        int mDay, mMonth, mYear;
        mDay = Integer.parseInt(dataArray[0].toString());
        mMonth = Integer.parseInt(dataArray[1].toString());
        mYear = Integer.parseInt(dataArray[2].toString());

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, mDay);
        c.set(Calendar.MONTH, mMonth - 1);
        c.set(Calendar.YEAR, mYear);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Long lDate = c.getTimeInMillis();
        return lDate;
    }

    public static Bitmap convert(byte[] image) {
        try {
            //get it as a ByteArray
            byte[] imageByteArray = image;
            //convert it back to an image
            ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
            BitmapFactory.Options bfOptions = new BitmapFactory.Options();
            bfOptions.inDither = false;
            bfOptions.inInputShareable = true;
            bfOptions.inTempStorage = new byte[16 * 1024];
            Bitmap myImage = BitmapFactory.decodeStream(imageStream, null, bfOptions);

            return myImage;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("MissingPermission")
    public static String getIMEI(Context ct) {
        TelephonyManager tm = (TelephonyManager) ct.getSystemService(ct.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

}
