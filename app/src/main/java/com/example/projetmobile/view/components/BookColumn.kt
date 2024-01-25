package com.example.projetmobile.view.components

import Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
 fun BookColumn(
    books: List<Item>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    LazyColumn(modifier) {
        if (books.isNotEmpty()) {
            items(books) { book ->
                DetailedBookCard(book)
            }
        } else {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No matching books found")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            navController.navigate("Request a missing book")
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Request a Book")
                    }
                }
            }
        }
    }
}
