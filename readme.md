# BookExplorer App

**Description:**

BookExplorer is an Android application that allows users to explore and discover books using [the Google Books API.](https://developers.google.com/books/docs/v1/using) The app is built using Jetpack Compose for the UI, Retrofit for network requests, and leverages the capabilities of the Google Books API to provide users with information about various books.

## Demo

[click here to watch demo](https://drive.google.com/file/d/1XDDk6MNVXgcBEM42AyHMa1OlArW8jF0J)

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Dependencies](#dependencies)
- [Decisions of Design](#decisions-of-design)


## Features

1. **Login Page:**
   - Allows users to log in and access the app.
   

2. **Home Page:**
   - Displays a list of popular books.
   - Allows users to search for books by title.


3. **By Category:**
   - Provides a categorized view of books.
   - Allows users to explore books based on different genres.  
   

4. **Book Details Display:**
   - Presents detailed information about a selected book.


5. **Request a Missing Book:**
   - Allows users to submit a request (email) to demand a missing book.


6. **Sidebar:**
   - Includes a sidebar for easy navigation to different sections of the app.
   
  
7. **Error Handling Screens:**:
   - Custom error handling screens have been implemented to provide users with clear feedback in case of network errors or API failures.


## Screenshots
| Page       | Login Page  : User logs in writing mail and password                            | Home Page : User explore list of all books and the popular ones.               | Search Page : User can search a book by writing its title in search bar.            | Book Details Page : User consults detailed information about a selected book      | No Matching Book Page : User doesn't find results for the typed title .                    | Request a Missing Book Page : User can send an email to request a book                      |
|------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| ScreenShot | ![Login Page](https://drive.google.com/uc?id=1lyw8DDFcTepZNw2j6MEI0CuYdBNJAFbo) | ![Home Page](https://drive.google.com/uc?id=1DmYyuRTZ7LKhXDsxM0HURUPEegMN556D) | ![Search Example](https://drive.google.com/uc?id=1RP6oHqy2t2GnQ2WwEVLZ3Rn09Lz_aKBS) | ![Book Details](https://drive.google.com/uc?id=1zN1aPhVgJRrhNsc_Fbe4q2-12vbxDWvO) | ![No Matching Book Page](https://drive.google.com/uc?id=1re1FiW8k9PsLnedXmsBGyX87_3Y6ZlnI) | ![Request a Missing Book](https://drive.google.com/uc?id=1IZuSu3G7LoAx3Qk2tG1HnTtd5DWi3THC) |

| Page       | Email Sent                                                                      | Email Received                                                                       | Category Page: Users select a genre to view a list of books in that category     | No Internet Error Page                                                             | Not Able to fetch data Error Page                                                | SideBar                                                                      |
|------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| ScreenShot | ![Email Sent](https://drive.google.com/uc?id=1DUEO_U83N6O4N-hyRw9-lsXADHbLIy_B) | ![Email Received ](https://drive.google.com/uc?id=1Ztr2HYsLUOczLtdf6gRCzCE2mrC5WZnw) | ![By Category](https://drive.google.com/uc?id=17rQDYypN52mJnw32O0lkqeBSAI5J1JKX) | ![network error](https://drive.google.com/uc?id=1BNfGQ1JwbthRl-Y9kT-_k-YDUPknnl07) | ![API failure](https://drive.google.com/uc?id=1b5zos2Xxn_qVq5RUVmcROXaZw2kSihN5) | ![SideBar](https://drive.google.com/uc?id=1RUY9ygj1fpNdd5Nqx0GsZ5g-1rb3Izba) |



## Dependencies

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Google Books API](https://developers.google.com/books)

## Decisions of Design

#### Jetpack Compose:

Jetpack Compose was chosen for its declarative UI approach, making it easier to build and maintain the UI components.

#### Retrofit:

Retrofit is used for network requests to interact with the Google Books API. It provides a clean and efficient way to handle API calls.

#### Google Books API:

The Google Books API is utilized to fetch book information, covers, and other details. It provides a vast database of books for a rich user experience.

#### Navigation Component (NavHost):

The Navigation component is used for navigating between different screens in a structured way. It simplifies the navigation flow and enhances the overall user experience.

####  MVVM Architecture

##### Model : Represents the data and business logic. In this context, it interacts with the Google Books API .  

##### ViewModel :    
- **BookViewModel**:  
    - Manages data for the UI, including popular books, categorized books, and book details. 
    - Handles user interactions, such as search queries and book requests.
- **NetworkErrorViewModel** :
  - Specialized ViewModel responsible for checking internet connectivity.
     
##### View : Represents the UI and displays data provided by the ViewModel

