package com.example.mainactivity;

import android.os.StrictMode;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CheckCallStatus {
    private static String body;

    public static boolean checkCallStatus(String phoneNum) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL("https://dlongo.pythonanywhere.com/?phone_number=+" + phoneNum);
            Log.v("3", "" + url);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (IOException e) {
            return false;
        }
        if (body.equals("False")) return false;
        return true;
    }
}