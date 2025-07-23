package com.mygp.composeactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.mygp.composeactivity.data.UserPreferences
import com.mygp.composeactivity.navigation.AppNavHost
import com.mygp.composeactivity.navigation.Screen
import com.mygp.composeactivity.ui.theme.MyGPStartActivityTheme
import io.hansel.compose.SmtCompose
import io.hansel.hanselsdk.Hansel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import android.content.res.Configuration
import com.netcore.android.Smartech
import java.lang.ref.WeakReference
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isSmartechHandledDeeplink = Smartech.getInstance(WeakReference(this)).isDeepLinkFromSmartech(intent)
        if (!isSmartechHandledDeeplink) {
        //Handle deeplink on app side
        }
        userPreferences = UserPreferences(applicationContext)

        // Set locale from preferences before setContent
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val lang = prefs.getString("language", Locale.getDefault().language) ?: "en"
        setLocale(this, lang)

        Hansel.pairTestDevice(getIntent().getDataString())

        setContent {
            MyGPStartActivityTheme {
                var startDestination by remember { mutableStateOf<String?>(null) }
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    val isLoggedIn = userPreferences.isLoggedIn.first()
                    startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route
                }

                if (startDestination != null) {
                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination!!,
                        onLoginSuccess = {
                            lifecycleScope.launch {
                                userPreferences.setLoggedIn(true)
                            }
                        },
                        onProfileClick = { /* Not needed anymore, can be removed from AppNavHost */ }
                    )
                }
            }
        }
    }
}

fun setLocale(activity: Activity, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources = activity.resources
    val config = Configuration(resources.configuration)
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}