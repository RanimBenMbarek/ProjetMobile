package com.example.projetmobile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class BookRequestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSubmissionForm { title, authors, language, category, description,isbn ->
                sendEmail(title, authors, language, category, description,isbn)
            }
        }
    }

    fun sendEmail(title: String, authors: String, language: String, category: String, description: String,isbn: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("farahseddik500@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Missing Book Request")
            putExtra(Intent.EXTRA_TEXT, "Title: $title\nAuthors: $authors\nLanguage: $language\nCategory: $category\nDescription: $description\nISBN: $isbn")
        }
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}

@Composable
fun BookRequestScreen() {
    val context = LocalContext.current

    BookSubmissionForm { title, authors, language, category, description, isbn ->
        sendEmail(context, title, authors, language, category, description, isbn)
    }
}

private fun sendEmail(
    context: Context,
    title: String,
    authors: String,
    language: String,
    category: String,
    description: String,
    isbn: String
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("farahseddik500@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Missing Book Request")
        putExtra(Intent.EXTRA_TEXT, "Title: $title\nAuthors: $authors\nLanguage: $language\nCategory: $category\nDescription: $description\nISBN: $isbn")
    }
    context.startActivity(Intent.createChooser(intent, "Send email..."))
}


@Composable
fun BookSubmissionForm(onSubmit: (String, String, String, String, String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var authors by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Help us expand our book selection and Let us know what are we missing",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#FF8c7ae6"))
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(34.dp))

        RoundedTextField(value = title, onValueChange = { title = it }, label = "Title")
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextField(value = authors, onValueChange = { authors = it }, label = "Author(s)")
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextField(value = language, onValueChange = { language = it }, label = "Language")
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextField(value = category, onValueChange = { category = it }, label = "Category (Optional)")
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextField(value = description, onValueChange = { description = it }, label = "Description (Optional)")
        Spacer(modifier = Modifier.height(8.dp))
        RoundedTextField(value = isbn, onValueChange = { isbn = it }, label = "ISBN (Optional)")
        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = { onSubmit(title, authors, language, category, description, isbn) },
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(30.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Submit",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
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
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            //TextColor =  Color.Black
        ),
        visualTransformation = VisualTransformation.None,
        placeholder = {
            Text(text =label, color =  Color.Gray)
        },
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodyLarge

    )
}
@Preview(showBackground = true)
@Composable
fun PreviewBookSubmissionForm() {
    BookSubmissionForm { _, _, _, _, _,_ ->
        // Preview does not handle the submit action
    }
}