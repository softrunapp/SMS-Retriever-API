package com.softrunapp.automaticsmsverificationapplication;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        AppSignatureHelper appSignature = new AppSignatureHelper(this);
        for (String signature : appSignature.getAppSignatures()) {
            Log.e(TAG, "onCreate: " + signature );
        }
    }
}
