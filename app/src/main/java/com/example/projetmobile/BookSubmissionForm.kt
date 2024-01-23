package com.example.projetmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BookRequestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSubmissionForm { title, authors, language, category, description,isbn ->
                sendEmail(title, authors, language, category, description,isbn)
            }
        }
    }

    private fun sendEmail(title: String, authors: String, language: String, category: String, description: String,isbn: String) {
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
fun BookSubmissionForm(onSubmit: (String, String, String, String, String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var authors by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp),
         verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Help us expand our book selection and Let us know what are we missing",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(34.dp))
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = authors,
            onValueChange = { authors = it },
            label = { Text("Author(s)") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = language,
            onValueChange = { language = it },
            label = { Text("Language") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category (Optional)") },
            modifier = Modifier
                    .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(22.dp))
        Button(onClick = { onSubmit(title, authors, language, category, description, isbn) }) {
            Text("Submit")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewBookSubmissionForm() {
    BookSubmissionForm { _, _, _, _, _,_ ->
        // Preview does not handle the submit action
    }
}
