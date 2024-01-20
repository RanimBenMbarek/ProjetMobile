package com.example.projetmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import book

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(){

        }

    }
}

@Preview(showBackground = true)
@Composable
fun BookDetails() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Adjusted Image to be in a rectangle shape
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp, 120.dp) // Adjust size for a rectangle shape
                .background(MaterialTheme.colorScheme.background),
            contentScale = ContentScale.Crop
        )

        // Spacer
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            // Title
            Text(text = "It Ends with Us", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // Spacer
            Spacer(modifier = Modifier.height(4.dp))

            // Author
            Text(text = "Ranim", fontSize = 16.sp)

            // Spacer
            Spacer(modifier = Modifier.height(4.dp))

            // Price
            Text(text = "Price: $11", fontSize = 14.sp)

            // Spacer
            Spacer(modifier = Modifier.height(4.dp))

            // Rating Stars
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(2.toInt()) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                // Rating Text
                Text(text = "$2", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Book() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.background),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "New Content Text",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldView() {
    val textState = remember { mutableStateOf(value = "") }
    val updatedTextState = rememberUpdatedState(textState.value)

    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        placeholder = {
            Text(text = "Search book")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        textStyle = TextStyle(color = Color.White, fontSize = 20.sp)
    )
}

@Composable
private fun LazyColumnFunction(
    books:List<book>,
    modifier: Modifier=Modifier
){
    LazyColumn(modifier){

    }

}