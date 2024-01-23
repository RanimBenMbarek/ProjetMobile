package com.example.projetmobile

import ImageLinks
import Item
import android.annotation.SuppressLint
import androidx.compose.runtime.livedata.observeAsState
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class HomePageActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
                SampleAppNavGraph(bookViewModel = bookViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SampleAppNavGraph(
    bookViewModel : BookViewModel,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.HOME
    val navigationActions = remember(navController) { AppNavigationActions(navController) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Book App", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Home Page") },
                    selected = false,
                    onClick = {  navigationActions.navigateToHome()}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Search by Category") },
                    selected = false,
                    onClick = {  navigationActions.navigateToSearchByCategory() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Request a Book") },
                    selected = false,
                    onClick = {  navigationActions.navigateToRequestABook() }
                )
                // ...other drawer items
            }}
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = currentRoute) },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = { IconButton(onClick = {
                        coroutineScope.launch { drawerState.open() }
                    }, content = {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = null
                        )
                    })
                    }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer))
            }, modifier = Modifier
        ) {
            MyAppNavHost(bookViewModel=bookViewModel, navController = navController)
        }
    }
}
@Composable
fun MyAppNavHost(
    navController: NavHostController,
    startDestination: String = "home",
    bookViewModel: BookViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomePage(bookViewModel = bookViewModel)

        }
        composable("genre") {
            BookActivity(bookViewModel = bookViewModel)
        }

        composable("request") {
            BookRequestScreen()
        }
    }
}


@Composable
fun HomePage(bookViewModel: BookViewModel) {
    var popularBooks: List<Item>;
    var books: List<Item>;


    popularBooks= emptyList()
    books= emptyList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        TextFieldView()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Popular Books",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Use observeAsState to observe LiveData and update UI when data is received
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
        Log.d("API", popularBooks.toString())
        //LazyColumnFunction(popularBooks)


        //Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "List of Books",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        val booksState = bookViewModel.books.observeAsState()
        booksState.value?.let { booksResponse ->
            val allBooks = booksResponse.items
            if (allBooks.isNotEmpty()) {
                books = allBooks
            }
        }
        if (books.isNotEmpty()) {
            LazyColumnFunction(books)
        }

    }
}
@Composable
fun BookDetails(item: Item) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            if (item.volumeInfo.imageLinks != null) {
                val url: StringBuilder = StringBuilder(item.volumeInfo.imageLinks.thumbnail)
                url.insert(4, "s")

                CoilImage(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp), // Set the width as needed
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(100.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            )
                        }
                    },
                    imageModel = url.toString(),
                    contentScale = ContentScale.Fit,
                )
            } else {
                // Placeholder image
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 24.dp) // Adjust the padding values
            ) {
                // Title
                Text(
                    text = item.volumeInfo.title ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Authors
                val author = item.volumeInfo.authors?.getOrNull(0) ?: "Unknown Author"
                Text(
                    text = author,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price
                Text(
                    text = "Price: $110",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating Stars
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < 2) Color.Yellow else MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Rating Text
                    Text(
                        text = item.volumeInfo.averageRating?.toString() ?: "N/A",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        // Save Icon in Top Right
        Icon(
            painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
            contentDescription = "save",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .align(Alignment.TopEnd)
        )
    }
}
@Composable
fun Book(item: Item) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .height(190.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(2.dp)
    ) {
        if (item.volumeInfo.imageLinks != null) {
            val url: StringBuilder = StringBuilder(item.volumeInfo.imageLinks.thumbnail)
            url.insert(4, "s")
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TextFieldView() {
    var textState by remember { mutableStateOf(value = "") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(25.dp)) // Set the rounded corners for the Box
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            placeholder = {
                Text(text = "Search book")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
 fun LazyRowFunction(
    books: List<Item>,
    modifier: Modifier=Modifier
){
    LazyRow(modifier){
        if (books != null) {
            items(books) { book ->
                Book(book)
            }
        }
    }
}
@Composable
fun LoadingIcon() {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
@Composable
 fun LazyColumnFunction(
    books: List<Item>,
    modifier: Modifier=Modifier
){
    LazyColumn(modifier){
        if (books != null) {
            items(books) { book ->
                BookDetails(book)
            }
        }
    }
}