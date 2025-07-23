@file:OptIn(ExperimentalMaterial3Api::class)

package com.mygp.composeactivity.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.hansel.compose.SmtCompose
import io.hansel.compose.smtTag
import androidx.compose.ui.res.stringResource
import com.mygp.composeactivity.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import com.netcore.android.smartechappinbox.SmartechAppInbox
import com.netcore.android.smartechappinbox.network.listeners.SMTInboxCallback
import com.netcore.android.smartechappinbox.network.model.SMTInboxMessageData
import com.netcore.android.smartechappinbox.utility.SMTAppInboxMessageType
import com.netcore.android.smartechappinbox.utility.SMTAppInboxRequestBuilder
import com.netcore.android.smartechappinbox.utility.SMTInboxDataType
import java.lang.ref.WeakReference

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun CartScreen() {
    SmtCompose.screenName = "cart_screen"
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val builder = SMTAppInboxRequestBuilder.Builder(SMTInboxDataType.ALL)
            .setCallback(object : SMTInboxCallback {
                override fun onInboxFail() {
                    Log.d("APPINBOX PRINTING LOGS","FAIL CALLBACK ")

                }
                override fun onInboxProgress() {
                    Log.d("APPINBOX PRINTING LOGS","IN PROGRESS CALLBACK ")
                }
                override fun onInboxSuccess(messages: MutableList<SMTInboxMessageData>?) {

                    Log.d("APPINBOX PRINTING LOGS","MESSAGES ${messages} ")

                }
            })
            .setLimit(10).build()
        val smartechAppInbox = SmartechAppInbox.getInstance(WeakReference(context.applicationContext))
        smartechAppInbox.getAppInboxMessages(builder)

        val count = smartechAppInbox.getAppInboxMessageCount(SMTAppInboxMessageType.INBOX_MESSAGE)
        Log.d("APPINBOX PRINTING LOGS","COUNT ${count}")


        val messages = smartechAppInbox.getAppInboxMessages(SMTAppInboxMessageType.INBOX_MESSAGE)
        Log.d("APPINBOX PRINTING LOGS","MESSAGES FORM LOCAL ${messages}")
    }
    val items = List(7) { stringResource(R.string.cart_item, it + 1) }
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.cart)) }) }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(items) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .smtTag(screenName = "cart_screen", tag = "cart_item_$index")
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}