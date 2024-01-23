package com.example.projetmobile

import Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage
import androidx.activity.viewModels

class BookCategories : ComponentActivity(){

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                BookActivity(bookViewModel)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

//displays list of books : result of search based on genre
@Composable
fun BookActivity(bookViewModel: BookViewModel) {
    var selectedGenre by remember { mutableStateOf("") }
    var isBookListVisible by remember { mutableStateOf(false) }
    var booksByGenre: List<Item> by remember { mutableStateOf(emptyList()) };

    booksByGenre = emptyList();

    val booksByGenreState = bookViewModel.searchBooks.observeAsState()
    booksByGenreState.value?.let { searchBooksResponse ->
        val books = searchBooksResponse.items
        if (books.isNotEmpty()) {
            booksByGenre = books
        }
    }

    Column(){
        Spacer(modifier = Modifier.height(50.dp))
        if (!isBookListVisible) {
        BookGenresList(onGenreSelected = { genre ->
            bookViewModel.searchBooks(genre)
            isBookListVisible = true
            selectedGenre=genre
        })
    } else {
        LazyColumnFunction(books = booksByGenre)
    }}

}

//displays a grid of books genres you can select from
@Composable
fun BookGenresList(onGenreSelected: (String) -> Unit) {
    val bookGenres = listOf(
        "Fiction" to "https://img.freepik.com/free-vector/flat-illustration-world-book-day-celebration_23-2150159661.jpg",
        "Languages" to "https://img.freepik.com/free-vector/flat-design-english-book-illustration_23-2149494018.jpg",
        "Romance" to "https://img.freepik.com/free-vector/big-isolated-cartoon-young-girl-boy-love-couple-sharing-caring-love-3d-illustration_1150-37540.jpg",
        "Fantasy" to "https://img.freepik.com/free-vector/fairy-tale-concept-with-book_23-2148463681.jpg",
        "Horror" to "https://img.freepik.com/free-vector/halloween-card-with-castle-pumpkins_1048-2971.jpg",
        "Children's" to "https://img.freepik.com/free-vector/world-book-day-illustrations-design_23-2148473201.jpg",
        "Poetry" to "https://img.freepik.com/free-vector/hand-drawn-flat-design-poetry-illustration_23-2149314745.jpg"
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(bookGenres) { (genre,imageUrl) ->
            BookGenreCard(genre = genre, imageUrl = imageUrl, onGenreSelected = onGenreSelected)
        }
    }
}

//the card used in BookGenresList
@Composable
fun BookGenreCard(genre: String, imageUrl: String, onGenreSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onGenreSelected(genre)
            },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Image above the text
            CoilImage(
                imageModel = imageUrl,
                loading = {
                    Box(
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Text below the image
            Text(
                text = genre,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}



