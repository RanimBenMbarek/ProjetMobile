package com.example.projetmobile

import VolumeInfo
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetmobile.components.LoadDetailsImage


class DetailsActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val volumeInfo = intent.getSerializableExtra("volume") as? VolumeInfo
            if (volumeInfo != null) {
                BookDetailScreen(volumeInfo)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookDetailScreen(book: VolumeInfo) {
    val context = LocalContext.current // Get the current context

    Scaffold(
        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = book.title ?: "Book Details",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            color = Color(android.graphics.Color.parseColor("#ba68c8"))
                        )
                    )

                },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, HomePageActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) {
        LazyColumn(

        ) {
            item {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                      //  .border(1.dp, Color(0xFFBB86FC), shape = RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier
                           // .background(Color(android.graphics.Color.parseColor("#e5d0ff")))
                            .height(380.dp)
                            .fillMaxWidth()
                            .padding(4.dp)
                            .padding(bottom = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally


                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 5.dp)
                        ) {
                            if (book.imageLinks != null) {
                                LoadDetailsImage(imageLink = book.imageLinks.thumbnail)
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



                        val authors =
                            if (book.authors != null) book.authors.joinToString(
                                ","
                            ) else "Unknown"

                        Text(
                            text = "By $authors",

                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 20.sp,
                                color = Color(android.graphics.Color.parseColor("#231357"))
                            ),
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )


                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .padding(top = 10.dp)
                ) {

                    Column {
                        val subtitle = if (book.publishedDate != null) {
                            "Published at: " + book.publishedDate
                        } else {
                            " "
                        }
                        BookDescription(
                            title = "Description",
                            subtitle = subtitle,
                            description = book.description ?: "No Description Specified"
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        BookDescription(
                            title = "Categories",
                            subtitle = "",
                            description = book.categories.joinToString(", ")
                                ?: "No Categories Specified"
                        )
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { },
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp) // Set the maximum height as needed
        ) {
            item {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = colorResource(R.color.dark_grey),
                        fontSize = 18.sp
                    ),
                    maxLines = 7
                )
            }
        }
    }
}




