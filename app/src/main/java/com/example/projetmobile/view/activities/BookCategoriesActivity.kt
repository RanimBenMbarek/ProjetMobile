package com.example.projetmobile.view.activities

import Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetmobile.view.components.BookGenreCard
import com.example.projetmobile.view.components.DetailedBookCard
import com.example.projetmobile.viewModel.BookViewModel
import com.example.projetmobile.viewModel.NetWorkErrorViewModel
import com.example.projetmobile.viewModel.NetWorkErrorViewModelFactory

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

@Composable
fun BookActivity(bookViewModel: BookViewModel) {
    var selectedGenre by remember { mutableStateOf("") }
    var isBookListVisible by remember { mutableStateOf(false) }
    var booksByGenre: List<Item> by remember { mutableStateOf(emptyList()) };

    val context = LocalContext.current
    val netWorkErrorViewModel: NetWorkErrorViewModel = viewModel(factory = NetWorkErrorViewModelFactory(context))
    val apiErrorState = bookViewModel.apiError.observeAsState()
    val connectionErrorState = netWorkErrorViewModel.connectionError.observeAsState()

    booksByGenre = emptyList();

    val booksByGenreState = bookViewModel.searchBooks.observeAsState()
    booksByGenreState.value?.let { searchBooksResponse ->
        val books = searchBooksResponse.items
        if (books.isNotEmpty()) {
            booksByGenre = books
        }
    }

    Column(){
        if (connectionErrorState.value == true) {
            NetworkErrorScreen(
                onRetry = {},
                modifier = Modifier.fillMaxSize()
            )
        } else if (apiErrorState.value != null) {
            ApiErrorScreen(
                errorMessage = apiErrorState.value!!,
                onRetry = {},
                modifier = Modifier.fillMaxSize()
            )
        } else {
        Spacer(modifier = Modifier.height(50.dp))

        if (!isBookListVisible) {
        BookGenresList(onGenreSelected = { genre ->
            bookViewModel.searchBooks(genre)
            isBookListVisible = true
            selectedGenre=genre
            })
        } else {
            LazyColumnFunction(books = booksByGenre,)
            }
        }
    }

}

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

@Composable
private fun LazyColumnFunction(
    books: List<Item>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        if (books.isNotEmpty()) {
            items(books) { book ->
                DetailedBookCard(book)
            }
        }
    }
}

