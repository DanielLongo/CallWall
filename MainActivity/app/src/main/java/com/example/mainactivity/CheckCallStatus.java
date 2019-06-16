package com.example.mainactivity;

import android.os.AsyncTask;
import android.util.Log;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CheckCallStatus {
    private static String body;
    static private HtmlPage page;

    static class MyDownloadTask extends AsyncTask<Void,Void,Void>
    {
        public String turl;
        public String turl2;
        public HtmlPage tpage;
        protected void onPreExecute() {
            //display progress dialog.

        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.v("num", turl);
            Log.v("num2", turl2);
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
                Log.v("6", turl);
            } catch (IOException e) {
                turl = "true";
                Log.v("6", "t");
                e.printStackTrace();
            }

            WebClient client = new WebClient();
            client.setJavaScriptEnabled (false);
            client.setCssEnabled (false);
            try {
                tpage = client.getPage(turl2);
            } catch (Exception e) {
                //e.printStackTrace();
                tpage = null;
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }

    public static String generateURL(String phoneNumber) {
        return "https://who-calledme.com/Number/" +  phoneNumber.substring (0,1) + "-" + phoneNumber.substring (1,4) + "-" + phoneNumber.substring (4,7) + "-" + phoneNumber.substring (7,11);
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
        task.turl2 = generateURL(phoneNum);
        page = task.tpage;
        task.execute();
        boolean a = false;
        boolean b = false;
        int attempts = 0;
        //while (a == false && attempts < 20) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            body = task.turl;//IOUtils.toString(in, encoding);
        System.out.println(body);
            if (body.equals("False")) a = false;
            else a = true;
            Log.v("6", body);
            //attempts++;
        //}

        try {
            HtmlElement spanPrice = ((HtmlElement) page.getFirstByXPath("//*[@id=\"NNForm\"]/div[4]/div[1]/div/div/div/div[2]/div[1]/div[2]"));
            String rating = spanPrice.asText();
            if (rating.equals("Dangerous")) b = false;
            else if (rating.equals("Harassing")) b = false;
            else b = true;
        } catch(Exception e) {
            b = true;
        }
        Log.v("a", ""+a);
        Log.v("b", ""+b);
        return a && b;
    }
}