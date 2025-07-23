package com.mygp.composeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mygp.composeactivity.screens.CartScreen
import com.mygp.composeactivity.ui.theme.MyGPStartActivityTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGPStartActivityTheme {
                CartScreen()
            }
        }
    }
}