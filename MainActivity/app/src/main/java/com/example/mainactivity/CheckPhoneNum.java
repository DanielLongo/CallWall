package com.example.mainactivity;

import android.os.AsyncTask;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class CheckPhoneNum {
    static private HtmlPage page;

    static class MyDownloadTask extends AsyncTask<Void,Void,Void>
    {
        public String turl;
        public HtmlPage tpage;
        protected void onPreExecute() {
            //display progress dialog.

        }

        @Override
        protected Void doInBackground(Void... params) {
            WebClient client = new WebClient();
            client.setJavaScriptEnabled (false);
            client.setCssEnabled (false);
            try {
                tpage = client.getPage(turl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }

    public static String generateURL(String phoneNumber) {
        return "https://ho-calledme.com/Number/" +  phoneNumber.substring (0,1) + "-" + phoneNumber.substring (1,4) + "-" + phoneNumber.substring (4,7) + "-" + phoneNumber.substring (7,11);

    }

    public static boolean testURL(String searchUrl) {
        /*WebClient client = new WebClient();
        client.setJavaScriptEnabled (false);
        client.setCssEnabled (false);*/
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
        try {
            CheckPhoneNum.MyDownloadTask task = new CheckPhoneNum.MyDownloadTask();
            task.turl = searchUrl;
            task.execute();
            page = task.tpage;
            //page = client.getPage(searchUrl);
        }
        catch(Exception e){
            e.printStackTrace();
            return true;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            HtmlElement spanPrice = ((HtmlElement) page.getFirstByXPath("//*[@id=\"NNForm\"]/div[4]/div[1]/div/div/div/div[2]/div[1]/div[2]"));
            String rating = spanPrice.asText();
            if (rating.equals("Dangerous")) return false;
            else if (rating.equals("Harassing")) return false;
            return true;
        } catch(Exception e) {
            return true;
        }
    }

    public static boolean checkPhoneNum(String phoneNum) {
        String url = generateURL (phoneNum);
        return testURL (url);
    }
}