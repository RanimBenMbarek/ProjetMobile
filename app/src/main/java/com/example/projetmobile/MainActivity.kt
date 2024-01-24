package com.example.projetmobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


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
                .padding(30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "BookExplorer App",
                style = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#FF8c7ae6"))
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id =R.drawable.book),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 18.dp, start = 5.dp, end = 16.dp, top = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )



            Spacer(modifier = Modifier.height(50.dp))

            CustomTextField(
                text = email,
                placeholder = "Email",
                imageVector = Icons.Default.Email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(25.dp))

            CustomTextField(
                text = password,
                imageVector = Icons.Outlined.Lock,
                placeholder = "Password",
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password = it }
            )


            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    if (email == "gl4@gmail.com" && password == "0000") {
                        val intent = Intent(context, HomePageActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        showErrorDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
            ) {
                Text(text ="Login" ,style = TextStyle(
                        fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,

                ))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    imageVector: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color =  Color.Gray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(25.dp)
            )
            .height(60.dp)
            .background(
                color =  Color.White,
            ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor =  Color.Black
        ),
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = null)
        },
        placeholder = {
            Text(text = placeholder, color =  Color.Gray)
        },
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}