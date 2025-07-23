package com.mygp.composeactivity.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.hansel.compose.SmtCompose
import io.hansel.compose.smtTag
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.res.stringResource
import com.mygp.composeactivity.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.mygp.composeactivity.data.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.res.Configuration
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit
) {
    SmtCompose.screenName = "home_screen"
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    var language by rememberSaveable { mutableStateOf(Locale.getDefault().language) }

    // Load saved language preference
    LaunchedEffect(Unit) {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val lang = prefs.getString("language", Locale.getDefault().language) ?: "en"
        language = lang
        setLocale(context, lang)
    }

    fun toggleLanguage() {
        val newLang = if (language == "en") "bn" else "en"
        language = newLang
        setLocale(context, newLang)
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("language", newLang).apply()
        (context as? Activity)?.recreate()
    }

    val items = List(10) { context.getString(R.string.home_item, it + 1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home)) },
                actions = {
                    TextButton(
                        onClick = onProfileClick,
                        modifier = Modifier.smtTag(screenName = "home_screen", tag = "profile_button")
                    ) { Text(stringResource(R.string.profile)) }
                    TextButton(
                        onClick = onCartClick,
                        modifier = Modifier.smtTag(screenName = "home_screen", tag = "cart_button")
                    ) { Text(stringResource(R.string.cart)) }
                    TextButton(
                        onClick = { toggleLanguage() },
                        modifier = Modifier.smtTag(screenName = "home_screen", tag = "lang_toggle_button")
                    ) {
                        Text(
                            if (language == "en") stringResource(R.string.language_toggle)
                            else stringResource(R.string.language_toggle_bn)
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(items) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .smtTag(screenName = "home_screen", tag = "home_item_$index")
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

fun setLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources = context.resources
    val config = Configuration(resources.configuration)
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}