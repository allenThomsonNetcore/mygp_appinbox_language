package com.mygp.composeactivity.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import io.hansel.compose.SmtCompose
import androidx.compose.ui.res.stringResource
import com.mygp.composeactivity.R

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit

) {
    SmtCompose.screenName = "login_screen"
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    fun checkLogin(): Boolean = username == "user" && password == "password"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.login), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(R.string.username)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (checkLogin()) {
                    onLoginSuccess()
                } else {
                    error = true
                }
            }) {
                Text(stringResource(R.string.login))
            }
            if (error) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.invalid_credentials), color = MaterialTheme.colorScheme.error)
            }
        }
    }
}