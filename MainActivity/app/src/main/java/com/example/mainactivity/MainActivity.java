package com.example.mainactivity;

import android.Manifest;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_PHONE_STATE = 1;
    public static int lastRingerMode = 0;
    public static ArrayList<String> numbers;
    public static boolean on = true;
    public static int verified = 2;

    public static final String ACCOUNT_SID = "AC6f773e33d173baceaddd67ddc7f3d124";
    public static final String AUTH_TOKEN = "x";

    public void updateTextView(final String s) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.verified);
                tv.setText("" + s);
            }
        });
    }

    public void Toggle(View view) {
        on = !on;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IncomingCallReceiver.thisActivity = MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Call call = Call.creator(
                new com.twilio.type.PhoneNumber("+16505541750"),//to
                new com.twilio.type.PhoneNumber("+15017122661"),//from
                URI.create("http://demo.twilio.com/docs/voice.xml"))
                .create();

        Log.v("asdf", call.getSid());

        class VerifiedCounter extends BroadcastReceiver {
            @Override
            public void onReceive(final Context context, Intent intent) {
                if (!MainActivity.on) {
                    return;
                }
                Log.v("test", "" + MainActivity.verified);
                updateTextView(MainActivity.verified + "");
            }
        }

        new VerifiedCounter();


        final Context context = this.getApplicationContext();

        //Toast.makeText(this, "Started the app", Toast.LENGTH_SHORT).show();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS, Manifest.permission.INTERNET};
                requestPermissions(permissions, PERMISSION_REQUEST_READ_PHONE_STATE);
            }
            try {
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setRingerMode(audioManager.getRingerMode());
            } catch (Exception e) {
                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
                    Intent intent2 = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    context.startActivity(intent2);
                }
            }
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_PHONE_STATE);
            }
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, PERMISSION_REQUEST_READ_PHONE_STATE);
            }

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_READ_PHONE_STATE);
            }

        }

        MainActivity.numbers = MainActivity.getContactList(this.getApplicationContext());

        ContentObserver mObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                MainActivity.numbers = MainActivity.getContactList(context);
            }
        };


        this.getContentResolver().registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, mObserver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permission granted: " + PERMISSION_REQUEST_READ_PHONE_STATE, Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Permission NOT granted: " + PERMISSION_REQUEST_READ_PHONE_STATE, Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }

    public static ArrayList<String> getContactList(Context context) {
        ArrayList<String> numbers = new ArrayList<String>();

        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numbers.add(phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return numbers;
    }

}
