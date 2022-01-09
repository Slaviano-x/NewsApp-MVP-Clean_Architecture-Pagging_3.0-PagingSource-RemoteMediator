package com.tyryshkin.newsapp.ui.web

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class WebPresenter(
    private val connectivityManager: ConnectivityManager
) {

    fun isOnline(): Boolean {

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}