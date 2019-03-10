package com.example.mainactivity;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CheckCallStatus {
    private static String body;

    static class MyDownloadTask extends AsyncTask<Void,Void,Void>
    {
        public String turl;
        protected void onPreExecute() {
            //display progress dialog.

        }

        @Override
        protected Void doInBackground(Void... params) {
            URL url = null;
            try {
                url = new URL(turl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection con = null;
            try {
                con = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = null;
            try {
                in = con.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            try {
                turl = IOUtils.toString(in, encoding);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }

    public static boolean checkCallStatus(String phoneNum) {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
            /*URL url = new URL("https://dlongo.pythonanywhere.com/?phone_number=+" + phoneNum);

            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;*/
        MyDownloadTask task = new MyDownloadTask();
        task.turl = "https://dlongo.pythonanywhere.com/?phone_number=+" + phoneNum;
        task.execute();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        body = task.turl;//IOUtils.toString(in, encoding);
        if (body.equals("False")) return false;
        return true;
    }
}