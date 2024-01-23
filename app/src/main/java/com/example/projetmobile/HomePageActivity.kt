package com.example.projetmobile

import ImageLinks
import Item
import androidx.compose.runtime.livedata.observeAsState
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage


class HomePageActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        var popularBooks: List<Item>;
        var books: List<Item>;

        super.onCreate(savedInstanceState)
        popularBooks= emptyList()
        books= emptyList()
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
                    LoadingIcon()
                }
                Log.d("API", popularBooks.toString())
                //LazyColumnFunction(popularBooks)


                //Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "List of Books",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                val booksState = bookViewModel.books.observeAsState()
                booksState.value?.let { booksResponse ->
                    val allBooks = booksResponse.items
                    if (allBooks.isNotEmpty()) {
                        books = allBooks
                    }
                }
                if (books.isNotEmpty()) {
                    LazyColumnFunction(books)
                }

            }
        }

    }

}


@Composable
fun BookDetails(item: Item) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            if (item.volumeInfo.imageLinks != null) {
                val url: StringBuilder = StringBuilder(item.volumeInfo.imageLinks.thumbnail)
                url.insert(4, "s")

                CoilImage(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp), // Set the width as needed
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(100.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            )
                        }
                    },
                    imageModel = url.toString(),
                    contentScale = ContentScale.Fit,
                )
            } else {
                // Placeholder image
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 24.dp) // Adjust the padding values
            ) {
                // Title
                Text(
                    text = item.volumeInfo.title ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Authors
                val author = item.volumeInfo.authors?.getOrNull(0) ?: "Unknown Author"
                Text(
                    text = author,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price
                Text(
                    text = "Price: $110",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating Stars
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < 2) Color.Yellow else MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Rating Text
                    Text(
                        text = item.volumeInfo.averageRating?.toString() ?: "N/A",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        // Save Icon in Top Right
        Icon(
            painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
            contentDescription = "save",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .align(Alignment.TopEnd)
        )
    }
}
@Composable
fun Book(item: Item) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(2.dp)
    ) {
        if (item.volumeInfo.imageLinks != null) {
            val url: StringBuilder = StringBuilder(item.volumeInfo.imageLinks.thumbnail)
            url.insert(4, "s")
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
            )
        }
        Text(
            text = item.volumeInfo.title ?: "",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TextFieldView() {
    var textState by remember { mutableStateOf(value = "") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(25.dp)) // Set the rounded corners for the Box
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
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
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
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
@Composable
fun LoadingIcon() {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
@Composable
private fun LazyColumnFunction(
    books: List<Item>,
    modifier: Modifier=Modifier
){
    LazyColumn(modifier){
        if (books != null) {
            items(books) { book ->
                BookDetails(book)
            }
        }
    }
}