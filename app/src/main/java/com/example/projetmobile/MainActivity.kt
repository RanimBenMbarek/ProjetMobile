package com.example.projetmobile

import ImageLinks
import Item
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.runtime.livedata.observeAsState
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage


class MainActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        var popularBooks: List<Item>;
        var books: List<Item>;

        super.onCreate(savedInstanceState)
        popularBooks= emptyList()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextFieldView()
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Popular Books",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                // Use observeAsState to observe LiveData and update UI when data is received
                val popularBooksState = bookViewModel.popularBooks.observeAsState()
                popularBooksState.value?.let { booksResponse ->
                    val books = booksResponse.items
                    if (books.isNotEmpty()) {
                        popularBooks = books
                    }
                }
                if (popularBooks.isNotEmpty()) {
                    LazyRowFunction(popularBooks)
                } else {
                    // If the list is empty, display some placeholder or alternative UI
                    //BookDetails()
                }
                Log.d("API", popularBooks.toString())
                //LazyColumnFunction(popularBooks)


                //Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "List of Books",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                val booksState = bookViewModel.popularBooks.observeAsState()
                booksState.value?.let { booksResponse ->
                    val books = booksResponse.items
                    if (books.isNotEmpty()) {
                        popularBooks = books
                    }
                }
                if (popularBooks.isNotEmpty()) {
                    LazyRowFunction(popularBooks)
                } else {
                    // If the list is empty, display some placeholder or alternative UI
                    //BookDetails()
                }

            }
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


@Composable()
fun LoadImage(imageLinks : ImageLinks) {

if (imageLinks != null) {
    val url: StringBuilder = StringBuilder(imageLinks.thumbnail)
    url.insert(4, "s")

    CoilImage(
        modifier = Modifier
            .size(100.dp, 200.dp),
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
        imageModel = url.toString(),
        contentScale = ContentScale.Fit,
    )
} else {
    Box(
        modifier = Modifier
            .size(100.dp, 200.dp),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp, 200.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            //colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary),
        )
    }

}
}

@Composable
fun Book(item: Item) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(300.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(5.dp)
    ) {
        if (item.volumeInfo.imageLinks != null) {
            val url: StringBuilder = StringBuilder(item.volumeInfo.imageLinks.thumbnail)
            url.insert(4, "s")

            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                },
                imageModel = url.toString(),
                contentScale = ContentScale.Fit,
            )
        } else {
            // Use a placeholder image or handle the case when imageLinks are null
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null, // Provide appropriate content description
            )
        }

        // Title
        Text(
            text = item.volumeInfo.title ?: "",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

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
    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
    modifier = Modifier.fillMaxWidth()
)
}


@Composable
private fun LazyColumnFunction(
books: List<Item>?,
modifier: Modifier = Modifier
) {
LazyColumn(modifier) {
    if (books != null) {
        items(books) { book ->
            Book(book)
        }
    }
}
}


@Composable
private fun LazyRowFunction(
books: List<Item>,
modifier: Modifier=Modifier
){
LazyRow(modifier){
    if (books != null) {
        items(books) { book ->
            Book(book)
        }
    }
}

}