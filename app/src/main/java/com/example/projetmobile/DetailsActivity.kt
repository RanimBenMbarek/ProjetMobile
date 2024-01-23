package com.example.projetmobile

import ImageLinks
import IndustryIdentifier
import VolumeInfo
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
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
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 40.dp)
      ) {
            item {
                Box {
                    Column(
                        modifier = Modifier
                            .height(400.dp)
                            .width(450.dp)
                            .padding(bottom = 10.dp),
                          //  .background(color = colorResource(R.color.primary)),

                        horizontalAlignment = Alignment.CenterHorizontally


                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier= Modifier.padding(top = 5.dp)
                        ) {
                            if (book.imageLinks != null) {
                                CoilImage(
                                    loading = {
                                        Box(
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.align(Alignment.Center),
                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .height(300.dp)
                                        .padding(5.dp)
                                        .width(200.dp)
                                        .shadow(5.dp, shape = RoundedCornerShape(8.dp), clip=true,
                                            ambientColor=Color.Transparent ,
                                            spotColor= Color.LightGray)

                                        .border(1.dp, Color.Transparent, shape = RoundedCornerShape(16.dp)) // Add border
                                        .clip(RoundedCornerShape(8.dp)),

                                    imageModel = book.imageLinks.thumbnail,
                                    contentScale = ContentScale.Fit,
                                )
                            }
                            Column(
                                modifier = Modifier.padding(top = 15.dp),
                                verticalArrangement = Arrangement.Center

                            ) {
                                InfoTable(
                                    icon = Icons.Default.Star,
                                    title = "Rating",
                                    value = book.averageRating.toString(),
                                    color = null
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                InfoTable(
                                    icon = painterResource(id = R.drawable.baseline_article_24),
                                    title = "Pages ",
                                    value = book.pageCount.toString(),
                                    color = "#ba68c8"
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                InfoTable(
                                    icon = painterResource(id = R.drawable.baseline_g_translate_24),
                                    title = "Language",
                                    value = book.language.uppercase(),
                                    color = null,
                                )

                            }

                        }

                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp
                            )

                        )

                        val authors =
                            if (book.authors != null) book.authors.joinToString(
                                ","
                            ) else "Unknown"

                        Text(
                            text = "By $authors",

                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 20.sp,
                                color = Color(android.graphics.Color.parseColor("#231357"))),
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )


                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                       // .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    // Row with Description and Price
                    Column {
                        BookDescription(
                            title = "Description",
                            subtitle = "Published at: " + book.publishedDate,
                            description = book.description ?: "No Description Specified"
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        BookDescription(
                            title = "Categories",
                            subtitle = "",
                            description = book.categories.joinToString(", ") ?: "No Categories Specified"
                        )
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp)) // Adjust spacing as needed
                Button(
                    onClick = { /* Handle add to cart action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing between icon and text
                    Text("Add to Cart")
                }
            }

        }
    }
}

@Composable
fun <T> InfoTable(icon: T, title: String, value: String ,color :String?) where T : Any {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (icon) {
                is ImageVector -> Icon(imageVector = icon, contentDescription = null, tint = Color(android.graphics.Color.parseColor("#ffd700")))
                is Painter -> Icon(painter = icon, contentDescription = null , tint = Color(android.graphics.Color.parseColor(color?:"#3c9dff")))
                else -> error("Unsupported icon type")
            }

            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(2.dp),
        )
    }
}



@Composable
fun BookDescription(
    title: String,
    subtitle: String,
    description: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(

                color = colorResource(R.color.dark_grey),
                fontSize = 18.sp
            )
        )
    }
}




@Composable
@Preview(showBackground = true)
fun BookDetailScreenPreview() {
    DetailsActivityContent()
}







