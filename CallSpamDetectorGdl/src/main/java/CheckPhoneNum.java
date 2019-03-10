import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CheckPhoneNum {
    static private HtmlPage page;
    public static String generateURL(String phoneNumber) {
        return "https://who-calledme.com/Number/" +  phoneNumber.substring (0,1) + "-" + phoneNumber.substring (1,4) + "-" + phoneNumber.substring (4,7) + "-" + phoneNumber.substring (7,11);

    }

    public static boolean testURL(String searchUrl) {
        WebClient client = new WebClient();
        client.setJavaScriptEnabled (false);
        client.setCssEnabled (false);
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
        try {
            page = client.getPage(searchUrl);
        }
        catch(Exception e){
            e.printStackTrace();
            return true;
        }
        HtmlElement spanPrice = ((HtmlElement) page.getFirstByXPath("//*[@id=\"NNForm\"]/div[4]/div[1]/div/div/div/div[2]/div[1]/div[2]")) ;
        String rating = spanPrice.asText ();
        if (rating.equals ("Dangerous")) return false;
        else if (rating.equals ("Harassing")) return false;
        return true;
    }

    public static boolean checkPhoneNum(String phoneNum) {
        String url = generateURL (phoneNum);
        return testURL (url);
    }
}