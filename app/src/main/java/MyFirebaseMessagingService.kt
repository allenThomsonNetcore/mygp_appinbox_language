
package com.mygp.composeactivity

import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import com.netcore.android.smartechpush.SmartPush
import java.lang.ref.WeakReference

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
         if(remoteMessage.getData().containsKey("smtSrc")){
             SmartPush.getInstance(WeakReference(applicationContext)).handleRemotePushNotification(remoteMessage)
        }
     }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        SmartPush.getInstance(WeakReference(applicationContext)).setDevicePushToken(token)
    }
}
