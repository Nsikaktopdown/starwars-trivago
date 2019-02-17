package io.altalabs.androidbase.util

import android.net.ConnectivityManager
import io.altalabs.data.boilerplate.INetworkMonitor
import javax.inject.Inject

class NetworkMonitor @Inject constructor(val connectivityManager: ConnectivityManager): INetworkMonitor {

    override fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}