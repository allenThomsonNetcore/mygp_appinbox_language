@file:OptIn(ExperimentalMaterial3Api::class)

package com.mygp.composeactivity.screens

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

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun CartScreen() {
    SmtCompose.screenName = "cart_screen"
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