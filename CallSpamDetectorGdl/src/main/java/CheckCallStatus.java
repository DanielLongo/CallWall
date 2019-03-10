import org.apache.commons.io.IOUtils;
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