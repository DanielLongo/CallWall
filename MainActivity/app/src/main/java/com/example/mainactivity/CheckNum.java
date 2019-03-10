package com.example.mainactivity;

import android.util.Log;

public class CheckNum {
    public static boolean checkNum(String number1) {
        String number = "16505461126";
        //
        boolean b = CheckCallStatus.checkCallStatus (number);
        boolean a = CheckPhoneNum.checkPhoneNum (number);
        Log.v("1", ""+a);
        Log.v("2", ""+b);
        if (a && b) return true;
        return false;
    }

    public static void main(String[] args) {
        String num = "16505461126";
        System.out.println (checkNum (num));
    }
}
