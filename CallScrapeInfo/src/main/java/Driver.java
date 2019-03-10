import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.math.BigInteger;
import java.util.List;

class Driver {
    public static void main(String[] args) {
        String phoneNum = "16505461126";
        boolean callStatus = CheckCallStatus.checkCallStatus (phoneNum);
        boolean phoneNumcheck = CheckPhoneNum.checkPhoneNum (phoneNum);
        System.out.println ("call status " + callStatus );
        System.out.println ("phone number check " + phoneNumcheck );
    }
}
//class Driver {
//    public static void main(String[] args) {
////        String url = "https://who-calledme.com/Number/800-531-8722";
//        String num = "18005318722";
//        String url = (generateURL (num) );
//        System.out.println (testURL (url));
//    }
//    static private HtmlPage page;
//
//    public static String generateURL(String phoneNumber) {
//            return "https://who-calledme.com/Number/" +  phoneNumber.substring (0,1) + "-" + phoneNumber.substring (1,4) + "-" + phoneNumber.substring (4,7) + "-" + phoneNumber.substring (7,11);
//
//    }
//
//    public static boolean testURL(String searchUrl) {
//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//        try {
//            page = client.getPage(searchUrl);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return true;
//        }
//        HtmlElement spanPrice = ((HtmlElement) page.getFirstByXPath("//*[@id=\"NNForm\"]/div[4]/div[1]/div/div/div/div[2]/div[1]/div[2]")) ;
//        String rating = spanPrice.asText ();
//        if (rating.equals ("Dangerous")) return false;
//        else if (rating.equals ("Harassing")) return false;
//        return true;
//    }
//}