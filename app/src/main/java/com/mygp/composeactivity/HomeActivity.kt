package com.mygp.composeactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mygp.composeactivity.screens.HomeScreen
import com.mygp.composeactivity.ui.theme.MyGPStartActivityTheme

class HomeActivity : ComponentActivity() {
    private lateinit var profileLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle result from ProfileActivity if needed
            }
        }

        setContent {
            MyGPStartActivityTheme {
                HomeScreen(
                    onProfileClick = {
                        val intent = Intent(this, ProfileActivity::class.java)
                        profileLauncher.launch(intent)
                    },
                    onCartClick = {
                        val intent = Intent(this, CartActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}