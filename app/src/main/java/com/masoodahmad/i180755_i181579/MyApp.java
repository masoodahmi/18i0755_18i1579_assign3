package com.masoodahmad.i180755_i181579;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApp extends Application {

    private static final String ONESIGNAL_APP_ID = "1dedd920-bd5e-45d1-977c-1c6a1ad85eed";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
