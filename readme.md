# BookExplorer App

**Description:**

BookExplorer is an Android application that allows users to explore and discover books using [the Google Books API.](https://developers.google.com/books/docs/v1/using) The app is built using Jetpack Compose for the UI, Retrofit for network requests, and leverages the capabilities of the Google Books API to provide users with information about various books.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
   - [Login Page](#login-page)
   - [Home Page](#home-page)
   - [By Category](#by-category)
   - [Book Details Page](#book-details-display)
   - [Request a Missing Book](#request-a-missing-book)
   - [Sidebar](#sidebar)
- [Dependencies](#dependencies)
- [Decisions of Design](#decisions-of-design)
- [Role of ViewModel](#role-of-viewmodel)

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
   - Allows users to submit a request (email) for a missing book.


6. **Sidebar:**
   - Includes a sidebar for easy navigation to different sections of the app.




## Screenshots

### Login Page
![Login Page](https://drive.google.com/uc?id=19yAVoDRRCZ8-0vwj0B_qXRCs_mzgRh6Q)


### Home Page
![Home Page](screenshots/home_page.png)

### By Category
![By Category](https://drive.google.com/uc?id=17rQDYypN52mJnw32O0lkqeBSAI5J1JKX)  ![ & List Of Books of the same category](https://drive.google.com/uc?id=1tAshC6IskRVpqhEwEzlw68uKiIhyua6Q)


### Book Details Display
![Book Details Display](https://drive.google.com/uc?id=1KvNbJu18Zov7joCW2IilH0CytxFLYpS7)


### Request a Missing Book
![Request a Missing Book](screenshots/request_book.png)

### Sidebar
![Sidebar](screenshots/sidebar.png)


## Dependencies

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Google Books API](https://developers.google.com/books)

## How to Build and Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/BookExplorer.git

Certainly! I'll add a section to the README to explain the role of the ViewModel (using the ViewModel-View-Model pattern) in the application:

markdown
Copy code
# BookExplorer App

**Description:**

BookExplorer is an Android application that allows users to explore and discover books using the Google Books API. The app is built using Jetpack Compose for the UI, Retrofit for network requests, and leverages the capabilities of the Google Books API to provide users with information about various books.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
   - [Home Page](#home-page)
   - [By Category](#by-category)
   - [Request a Missing Book](#request-a-missing-book)
   - [Sidebar](#sidebar)
   - [Login Page](#login-page)
   - [Book Details Display](#book-details-display)
- [Dependencies](#dependencies)
- [Decisions of Design](#decisions-of-design)
- [Role of ViewModel](#role-of-viewmodel)

## Features

1. **Home Page:**
   - Displays a list of popular books.
   - Allows users to search for books by title.

2. **By Category:**
   - Provides a categorized view of books.
   - Allows users to explore books based on different genres.

3. **Request a Missing Book:**
   - Allows users to submit a request for a missing book.

4. **Sidebar:**
   - Includes a sidebar for easy navigation to different sections of the app.

5. **Login Page:**
   - Allows users to log in and access personalized features.

6. **Book Details Display:**
   - Presents detailed information about a selected book.

## Screenshots

### Home Page
![Home Page](screenshots/home_page.png)

### By Category
![By Category](screenshots/by_category.png)

### Request a Missing Book
![Request a Missing Book](screenshots/request_book.png)

### Sidebar
![Sidebar](screenshots/sidebar.png)

### Login Page
![Login Page](screenshots/login_page.png)

### Book Details Display
![Book Details Display](screenshots/book_details.png)

## Dependencies

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Google Books API](https://developers.google.com/books)

##Decisions of Design

####Jetpack Compose:

Jetpack Compose was chosen for its declarative UI approach, making it easier to build and maintain the UI components.

####Retrofit:

Retrofit is used for network requests to interact with the Google Books API. It provides a clean and efficient way to handle API calls.

####Google Books API:

The Google Books API is utilized to fetch book information, covers, and other details. It provides a vast database of books for a rich user experience.
####Navigation Component (NavHost):

The Navigation component is used for navigating between different screens in a structured way. It simplifies the navigation flow and enhances the overall user experience.
####Error Handling Screens:

Custom error handling screens have been implemented to provide users with clear feedback in case of network errors or API failures.
###Role of ViewModel
The ViewModel (ViewModel-View-Model or VVM) plays a crucial role in managing the UI-related data and handling the communication between the UI and the underlying data sources.

In the BookExplorer app, the BookViewModel is responsible for:

- Fetching and holding data from the Google Books API.
- Managing the UI-related data, such as the list of popular books, categorized books, and book details.
- Handling user interactions, such as search queries and book requests.

