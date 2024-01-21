package com.example.projetmobile

import ImageLinks
import IndustryIdentifier
import Item
import PanelizationSummary
import ReadingModes
import VolumeInfo
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailsActivityContent()
        }
    }
}

@Composable
fun DetailsActivityContent() {
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
        )

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

                   )
                   {

                       Column (modifier = Modifier
                           .background(color = Color.Green)
                           .fillMaxWidth()
                           .padding(16.dp)
                           .clip(shape = RoundedCornerShape(16.dp))) {
                           Image(
                               painter = painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder image, replace with your actual image
                               contentDescription = null,
                               modifier = Modifier
                                   .fillMaxWidth() // Adjusted to 70% of the box height
                                   .clip(shape = MaterialTheme.shapes.medium),

                           )

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
                                       //fontWeight = FontWeight.Bold,
                                       color = MaterialTheme.colorScheme.onPrimary,
                                       fontSize = 20.sp
                                   )
                               )
                               Spacer(modifier = Modifier.height(4.dp))
                               Text(
                                   text = book.subtitle,
                                   style = MaterialTheme.typography.titleSmall.copy(
                                       //fontWeight = FontWeight.Medium,
                                       color = MaterialTheme.colorScheme.onPrimary,fontSize = 15.sp
                                   )
                               )
                               Spacer(modifier = Modifier.height(4.dp))
                               Text(
                                   text = "By " + book.authors.joinToString(", "),
                                   style = MaterialTheme.typography.titleMedium.copy(
                                       color = MaterialTheme.colorScheme.onPrimary,
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
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp)

                            )
                            Text(
                                text = "Published at : ${book.publishedDate}",
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        // Volume Description
                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.bodyMedium.copy(

                                color = MaterialTheme.colorScheme.onSurface, // Change text color
                                fontSize = 18.sp // Use 'sp' for scaled pixels
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
