package com.example.mainactivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.android.internal.telephony.ITelephony;

public class IncomingCallReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(final Context context, Intent intent) {
        ITelephony telephonyService;

        Bundle extras = intent.getExtras();

        ArrayList<String> numbers = MainActivity.numbers;
        boolean skip = false;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).equals(extras.getString("incoming_number"))) {
                Toast.makeText(context, "Spam", Toast.LENGTH_LONG).show();
                skip = true;
            }
        }
        if (skip) {
            return;
        }

        if (extras != null) {

            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            final String incomingNumber = extras.getString("incoming_number");

            /*Handler callActionHandler = new Handler();

            Runnable runRingingActivity = new Runnable() {
                @Override
                public void run() {
                    //Notice the intent, cos u will add intent filter for your class(CustomCallsReceiver)
                    Intent intentPhoneCall = new Intent("android.intent.action.ANSWER");
                    intentPhoneCall.putExtra("INCOMING_NUM", incomingNumber);
                    intentPhoneCall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intentPhoneCall);
                }
            };*/
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                /*Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                //am.restartPackage("com.jimmy.appToBeClosed");

                context.startActivity(startMain);*/
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                //increase the delay amount if problem occur something like -the screen didn't show up- that's the key about this method(the delay).
                //callActionHandler.postDelayed(runRingingActivity, 100);
                //Toast.makeText(context, "Possible Spam", Toast.LENGTH_LONG).show();
                /*DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();*/
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                //callActionHandler.removeCallbacks(runRingingActivity);
            }

        }

        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {

                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                MainActivity.lastRingerMode = audioManager.getRingerMode();

                /*TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Method m = tm.getClass().getDeclaredMethod("getITelephony");

                    m.setAccessible(true);
                    telephonyService = (ITelephony) m.invoke(tm);

                    if ((number != null)) {
                        telephonyService.answerRingingCall();
                        Toast.makeText(context, "Ending the call from: " + number, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                Toast.makeText(context, skip + "", Toast.LENGTH_LONG).show();
                if (skip) {
                    return;
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    try {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        Toast.makeText(context, "Possible Spam", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
                            Intent intent2 = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                            context.startActivity(intent2);
                        }
                    }
                } else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    //Toast.makeText(context, "Possible Spam", Toast.LENGTH_LONG).show();

                }

                //Toast.makeText(context, "Ring " + number, Toast.LENGTH_SHORT).show();

            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                //Toast.makeText(context, "Answered " + number, Toast.LENGTH_SHORT).show();
            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(MainActivity.lastRingerMode);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    try {
                        audioManager.setRingerMode(MainActivity.lastRingerMode);
                    } catch (Exception e) {
                        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
                            Intent intent2 = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                            context.startActivity(intent2);
                        }
                    }
                } else {
                    audioManager.setRingerMode(MainActivity.lastRingerMode);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}