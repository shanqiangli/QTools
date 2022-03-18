package com.beautify.qtools.wifi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.beautify.qtools.wifi.listener.IWifiScanListener;

import java.lang.ref.WeakReference;

public class WifiScanReceiver extends BroadcastReceiver {
    private WeakReference<IWifiScanListener> listener;

    public WifiScanReceiver(IWifiScanListener listener) {
        this.listener = new WeakReference<>(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            if (null != listener && null != listener.get()) {
                listener.get().onScanResultsAvailabled();
            }
        }
    }
}
