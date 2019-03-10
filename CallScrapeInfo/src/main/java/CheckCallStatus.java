import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CheckCallStatus {
    private static String body;
    public static boolean checkCallStatus(String phoneNum) {
        try
            {
        URL url = new URL ("https://dlongo.pythonanywhere.com/?phone_number=+" + phoneNum);
        URLConnection con = url.openConnection ( );
        InputStream in = con.getInputStream ( );
        String encoding = con.getContentEncoding ( );
        encoding = encoding == null ? "UTF-8" : encoding;
        body = IOUtils.toString (in, encoding);
    }
    catch (IOException e) {
            return true;
    }
        if (body.equals ("False")) return false;
        return true;
    }
}
//    public static void main(String args[]) throws ClientProtocolException, IOException {
////        HttpPost post = new HttpPost("https://dlongo.pythonanywhere.com/");
////        NameValuePair[] data = {
////                new NameValuePair ("phone_number", "+16505461126")
////        };
////        post.setRequestBody(data);
////        InputStream in = post.getResponseBodyAsStream();
//
//        URL url = new URL("https://dlongo.pythonanywhere.com/?phone_number=+16505461126");
//        URLConnection con = url.openConnection();
//        InputStream in = con.getInputStream();
//        String encoding = con.getContentEncoding();
//        encoding = encoding == null ? "UTF-8" : encoding;
//        String body = IOUtils.toString(in, encoding);
//        System.out.println (body );
////        System.out.println(body);
////
////            CloseableHttpClient client = HttpClients.createDefault ( );
////            HttpPost httpPost = new HttpPost ("https://dlongo.pythonanywhere.com/");
////
////            String json = "{\"phone_number\": \"+16505461126\"}";
////            StringEntity entity = new StringEntity (json);
////            httpPost.setEntity (entity);
////            httpPost.setHeader ("Accept", "application/json");
////            httpPost.setHeader ("Content-type", "application/json");
////
////            CloseableHttpResponse response = client.execute (httpPost);
////            System.out.println (response.get );
////            client.close ( );
//
//    }

