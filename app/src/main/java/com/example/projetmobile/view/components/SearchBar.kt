package com.example.projetmobile.view.components

import Item
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetmobile.view.activities.filterBooksByTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    books: List<Item>,
    onSearch: (List<Item>) -> Unit,
    searchTextState: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        OutlinedTextField(
            value = searchTextState.value,
            onValueChange = {
                searchTextState.value = it
                val filteredBooks = filterBooksByTitle(it, books)
                onSearch(filteredBooks)
            },
            placeholder = {
                Text(text ="Search book")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),

            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}