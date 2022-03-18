package com.beautify.qtools.wifi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.beautify.qtools.wifi.listener.IWifiSignalListener;

import java.lang.ref.WeakReference;



public class WifiSignalReceiver extends BroadcastReceiver {
    private WeakReference<IWifiSignalListener> listener;

    public WifiSignalReceiver(IWifiSignalListener listener) {
        this.listener = new WeakReference<>(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
            int rssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, 0);
            if (null != listener && null != listener.get()) {
                listener.get().onWifiSignalChanged(rssi);
            }
        }
    }
}
