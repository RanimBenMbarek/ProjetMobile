package com.example.projetmobile.view.components

import Item
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetmobile.R
import com.example.projetmobile.view.activities.DetailsActivity
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookCard(item: Item) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(2.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("volume", item.volumeInfo)
                context.startActivity(intent)
            }
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
