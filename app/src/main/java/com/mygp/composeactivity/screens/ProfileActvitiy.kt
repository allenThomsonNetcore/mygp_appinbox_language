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
fun ProfileScreen() {
    val items = List(5) { stringResource(R.string.profile_item, it + 1) }
    SmtCompose.screenName = "profile_screen"
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.profile)) }) }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(items) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .smtTag(screenName = "profile_screen", tag = "profile_item_$index")
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