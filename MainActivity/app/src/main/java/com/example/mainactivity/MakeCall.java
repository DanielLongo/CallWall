package com.example.mainactivity;

public class MakeCall {
    // Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC6f773e33d173baceaddd67ddc7f3d124";
    public static final String AUTH_TOKEN = "x";

    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Call call = Call.creator(
//                new com.twilio.type.PhoneNumber("+16505541750"),//to
//                new com.twilio.type.PhoneNumber("+15017122661"),//from
//                URI.create("http://demo.twilio.com/docs/voice.xml"))
//                .create((new TwilioRestClient.Builder("test", "test").build()));//*/
        /*Call call = Connect(context, connectOptions, new Call.Listener() {
            @Override
            public void onRinging(@NonNull Call call) {
                Log.d(TAG, "Ringing");
            }

            @Override
            public void onConnected(@NonNull final Call call) {
                Log.d(TAG, "Received onConnected " + call.getSid());
            }

            @Override
            public void onConnectFailure(@NonNull Call call, @NonNull CallException callException) {
                Log.d(TAG, "Received onConnectFailure with CallException: " + callException.getErrorCode()+ ":" + callException.getMessage());
            }

            @Override
            public void onDisconnected(@NonNull Call call, CallException callException) {
                if (callException != null) {
                    Log.d(TAG, "Received onDisconnected with CallException: " + callException.getMessage() + ": " + call.getSid());
                } else {
                    Log.d(TAG, "Received onDisconnected");
                }
            }
        });//*/

//        Log.v("asdf", call.getSid());
    }
}