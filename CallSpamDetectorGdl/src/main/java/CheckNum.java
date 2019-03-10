public class CheckNum {
    public static boolean checkNum(String number) {
        if (CheckPhoneNum.checkPhoneNum (number) && CheckCallStatus.checkCallStatus (number)) return true;
        return false;
    }

    public static void main(String[] args) {
        String num = "16505461126";
        System.out.println (checkNum (num));
    }
}
