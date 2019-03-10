package com.example.mainactivity;

import android.app.Activity;
import android.os.Bundle;

public class CustomCallsReceiver extends Activity {

    private String TAG = "CustomCallsReceiver";
    String incomingNumber, caller;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.custome_calls_receiver);

        TextView number = (TextView) findViewById(R.id.number);
        number.setGravity(Gravity.CENTER);

        incomingNumber = getIntent().getExtras().getString("INCOMING_NUM");

        if (caller != null) {
            number.setText(incomingNumber);
        }

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //this.getBaseContext().startActivity(startMain);*/

        /*AlertDialog alertDialog = new AlertDialog.Builder(CustomCallsReceiver.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/
        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, 0)
                .setContentTitle("test")
                .setContentText("hi")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);*/
        throw new RuntimeException("This is a crash");


    }
}