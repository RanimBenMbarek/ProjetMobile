package com.example.projetmobile.view.activities

import Item
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetmobile.view.components.BookColumn
import com.example.projetmobile.view.components.LazyRowFunction
import com.example.projetmobile.view.components.LoadingIcon
import com.example.projetmobile.view.components.SearchBar
import com.example.projetmobile.viewModel.BookViewModel
import com.example.projetmobile.viewModel.NetWorkErrorViewModel
import com.example.projetmobile.viewModel.NetWorkErrorViewModelFactory


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePage(bookViewModel: BookViewModel, navController: NavHostController,) {
    var popularBooks: List<Item>;
    var books: List<Item>;
    popularBooks= emptyList()
    books= emptyList()

    val context = LocalContext.current
    val netWorkErrorViewModel: NetWorkErrorViewModel = viewModel(factory = NetWorkErrorViewModelFactory(context))
    val apiErrorState = bookViewModel.apiError.observeAsState()
    val connectionErrorState = netWorkErrorViewModel.connectionError.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                //end = 16.dp,
                top = 70.dp
            )
    ) {
        if (connectionErrorState.value == true) {
            NetworkErrorScreen(
                onRetry = {navController.navigate("Home")},
                modifier = Modifier.fillMaxSize()
            )
        } else if (apiErrorState.value != null) {
            ApiErrorScreen(
                errorMessage = apiErrorState.value!!,
                onRetry = {navController.navigate("Home")},
                modifier = Modifier.fillMaxSize()
            )
        } else {
            var displayedBooks by remember { mutableStateOf(books) }

            val searchTextState = remember { mutableStateOf("") }

            SearchBar(
                books = books,
                onSearch = { filteredBooks ->
                    displayedBooks = filteredBooks
                },
                searchTextState = searchTextState
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Popular Books",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

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
                    displayedBooks = filterBooksByTitle(searchTextState.value, books)
                }
            }

            BookColumn(displayedBooks, navController = navController)

        }
    }
}


fun filterBooksByTitle(title: String, books: List<Item>): List<Item> {
    return books.filter {
        it.volumeInfo.title?.contains(title, ignoreCase = true) == true
    }
}
