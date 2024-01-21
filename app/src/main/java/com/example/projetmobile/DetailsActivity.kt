package com.example.projetmobile

import ImageLinks
import IndustryIdentifier
import VolumeInfo
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage

class DetailsActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailsActivityContent()
        }
    }
}

@Composable
fun DetailsActivityContent() {


    // Use the MaterialTheme function with the content parameter
    MaterialTheme {
        val yourBook = VolumeInfo(
                allowAnonLogging = false,
        authors = listOf("David A. Vise", "Mark Malseed"),
        averageRating = 3.5,
        canonicalVolumeLink = "https://books.google.com/books/about/The_Google_story.html?id=zyTCAlFPjgYC",
        categories = listOf("Browsers (Computer programs)"), // Replace with the appropriate values
        contentVersion = "1.1.0.0.preview.2",
        description = "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, ...",
        imageLinks = ImageLinks(
            smallThumbnail = "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
            thumbnail = "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
        ),
        industryIdentifiers = listOf(
            IndustryIdentifier("ISBN_10", "055380457X"),
            IndustryIdentifier("ISBN_13", "9780553804577")
        ),
        infoLink = "https://books.google.com/books?id=zyTCAlFPjgYC&ie=ISO-8859-1&source=gbs_api",
        language = "en",
        maturityRating = "MATURE", // Replace with the appropriate value
        pageCount = 207,
        previewLink = "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
        printType = "BOOK",
        publishedDate = "2005-11-15",
        publisher = "Random House Digital, Inc.",
        ratingsCount = 136,

        subtitle = "Subtitle", // Replace with the appropriate value
        title = "The Google story"
        ) // Extracted the book creation to a separate function
        BookDetailScreen(yourBook)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookDetailScreen(book: VolumeInfo) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Your title content here */ },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle navigation back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                // Set the background color
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 50.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(400.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    ) {
                        if (book.imageLinks != null) {
                            CoilImage(
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
                                imageModel = book.imageLinks.thumbnail,
                                contentScale = ContentScale.Fit,
                            )
                        }

                        // Title and Authors
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = book.title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = book.subtitle ?: "", // Handle null subtitle
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 15.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "By " + book.authors.joinToString(", "),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }
            }

            // Bottom Box with Description, Price, and Volume Description
            item {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Row with Description and Price
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                            Text(
                                text = "Published at: ${book.publishedDate}",
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        // Volume Description
                        Text(
                            text = book.description ?: "", // Handle null description
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BookDetailScreenPreview() {
    DetailsActivityContent()
}







