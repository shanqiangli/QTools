package com.beautify.qtools.wifi.listener;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;

public interface INetworkListener {
    void onNetworkLost(Network network);

    void onNetworkAvailabled(Network network);

    void onNetworkStateChanged(NetworkInfo info);

    void onSupplicantStateChanged(SupplicantState state, int error);
}
