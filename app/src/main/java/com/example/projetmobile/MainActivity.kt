package com.example.projetmobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LoginPage()
            }

        }
    }

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun LoginPage() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showErrorDialog by remember { mutableStateOf(false) }

        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id =R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 18.dp, start = 5.dp, end = 16.dp, top = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // Handle the Next action if needed
                    }
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                //leadingIcon = { Icon(AutofillType.Password, "Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the Done action if needed
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email == "gl4@gmail.com" && password == "0000") {
                        // Redirect to HomePageActivity
                        val intent = Intent(context, HomePageActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        // Show error dialog or toast
                        showErrorDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Add error dialog or toast as needed
        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("Error") },
                text = { Text("Invalid email or password") },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
