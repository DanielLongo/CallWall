class Driver {
    public static void main(String[] args) {
        String phoneNum = "16505461126";
        boolean callStatus = CheckCallStatus.checkCallStatus (phoneNum);
        boolean phoneNumcheck = CheckPhoneNum.checkPhoneNum (phoneNum);
        System.out.println ("call status " + callStatus );
        System.out.println ("phone number check " + phoneNumcheck );
    }
}
