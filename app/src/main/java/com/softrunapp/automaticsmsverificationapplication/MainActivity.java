package com.softrunapp.automaticsmsverificationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private AppSMSBroadcastReceiver appSMSBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsListener();
        initBroadCast();


    }

    private void initBroadCast() {
        intentFilter = new IntentFilter("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        appSMSBroadcastReceiver = new AppSMSBroadcastReceiver();
        appSMSBroadcastReceiver.setOnSmsReceiveListener(new AppSMSBroadcastReceiver.OnSmsReceiveListener() {
            @Override
            public void onReceive(String code) {
                Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void smsListener() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsRetriever();
    }



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(appSMSBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(appSMSBroadcastReceiver);
    }
}