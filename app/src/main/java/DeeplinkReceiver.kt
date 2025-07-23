
package com.mygp.composeactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.netcore.android.*

class DeeplinkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val bundleExtra = intent?.extras
            bundleExtra?.let {
                val deepLinkSource = it.getString(SMTBundleKeys.SMT_KEY_DEEPLINK_SOURCE)
                val deepLink = it.getString(SMTBundleKeys.SMT_KEY_DEEPLINK)
                val customPayload = it.getString(SMTBundleKeys.SMT_KEY_CUSTOM_PAYLOAD)
                if (deepLink != null && deepLink.isNotEmpty()) {
                    // handle deepLink
                }
                if (customPayload != null && customPayload.isNotEmpty()) {
                    // handle custom payload
                }
            }
        } catch (t: Throwable) {
            Log.e("DeeplinkReceiver", "Error occurred in deeplink:${t.localizedMessage}")
        }
    }
}
