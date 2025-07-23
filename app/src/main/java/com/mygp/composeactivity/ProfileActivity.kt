package com.mygp.composeactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mygp.composeactivity.screens.ProfileScreen
import com.mygp.composeactivity.ui.theme.MyGPStartActivityTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGPStartActivityTheme {
                ProfileScreen()
            }
        }
    }

    // If you want to return a result:
    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        val resultIntent = Intent()
        // Add any data to resultIntent if needed
        setResult(Activity.RESULT_OK, resultIntent)
        super.onBackPressed()
    }
}