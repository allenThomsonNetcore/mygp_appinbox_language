package com.mygp.composeactivity

import android.app.Application
import android.content.IntentFilter
import android.os.Build
import java.lang.ref.WeakReference
import com.netcore.android.Smartech

class MyApplicationClass :Application() {
    override fun onCreate() {
        super.onCreate()
        val deeplinkReceiver = DeeplinkReceiver()
        val filter = IntentFilter("com.smartech.EVENT_PN_INBOX_CLICK")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            registerReceiver(deeplinkReceiver, filter, RECEIVER_EXPORTED)
        } else {
            registerReceiver(deeplinkReceiver, filter)
        }

        Smartech.getInstance(WeakReference(applicationContext)).initializeSdk(this)
        Smartech.getInstance(WeakReference(applicationContext)).setDebugLevel(9)
    }
}