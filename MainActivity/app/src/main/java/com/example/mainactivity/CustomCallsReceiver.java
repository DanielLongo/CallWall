package com.example.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class CustomCallsReceiver extends Activity {

    private String TAG = "CustomCallsReceiver";
    String incomingNumber, caller;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custome_calls_receiver);

        TextView number = (TextView) findViewById(R.id.number);
        number.setGravity(Gravity.CENTER);

        incomingNumber = getIntent().getExtras().getString("INCOMING_NUM");

        if (caller != null) {
            number.setText(incomingNumber);
        }

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //this.getBaseContext().startActivity(startMain);
    }
}