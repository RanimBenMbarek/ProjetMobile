package com.example.projetmobile.view.components

import Item
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LazyRowFunction(
    books: List<Item>,
    modifier: Modifier = Modifier
){
    LazyRow(modifier){
        if (books != null) {
            items(books) { book ->
                BookCard(book)
            }
        }
    }
}
