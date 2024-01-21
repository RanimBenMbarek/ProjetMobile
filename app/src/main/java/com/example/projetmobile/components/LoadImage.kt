package com.example.projetmobile.components

import ImageLinks
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.projetmobile.R
import com.skydoves.landscapist.coil.CoilImage

@Composable()
fun LoadImage(imageLinks : ImageLinks) {

    if (imageLinks != null) {
        var imageUrl = imageLinks.thumbnail
        if (!imageUrl.startsWith("https")) {
            // Insert "s" at position 4
            val urlBuilder = StringBuilder(imageUrl)
            urlBuilder.insert(4, "s")
            imageUrl = urlBuilder.toString()
        }
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
            imageModel = imageUrl,
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