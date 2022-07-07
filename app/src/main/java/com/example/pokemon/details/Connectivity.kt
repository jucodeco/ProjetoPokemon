package com.example.pokemon.details

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData



class Connectivity internal constructor(
    private val connectivityManager: ConnectivityManager
) : LiveData<Boolean>() {

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    constructor(application: Application) : this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
    )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(CONNECTED)
        }

        override fun onLost(network: Network) {
            postValue(DISCONNECTED)
        }
    }

    override fun onActive() {
        super.onActive()
        notifyNetworkStatus()
        registerNetworkCallback()
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun notifyNetworkStatus() {
        if (connectivityManager.deviceHasInternetConnection()) {
            this.postValue(CONNECTED)
        } else {
            this.postValue(DISCONNECTED)
        }
    }

    @SuppressLint("MissingPermission")
    private fun registerNetworkCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }

    @SuppressLint("MissingPermission")
    private fun ConnectivityManager.deviceHasInternetConnection(): Boolean {
        val hasInternetConnection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) this.activeNetwork != null
            else this.isActiveNetworkMetered?.and(true)

        return hasInternetConnection
    }

    private companion object {
        const val CONNECTED = true
        const val DISCONNECTED = false
    }
}

