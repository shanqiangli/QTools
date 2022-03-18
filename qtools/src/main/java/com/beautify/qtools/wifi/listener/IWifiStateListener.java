package com.beautify.qtools.wifi.listener;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;

public interface IWifiStateListener {
    void onWIFIEnabled();

    void onWIFIDisabled();
}
