package com.example.projetmobile

import androidx.navigation.NavController

class AppNavigationActions(navController: NavController) {

    val navigateToHome: () -> Unit = {
        navController.navigate(AllDestinations.HOME)
    }

    val navigateToSearchByCategory: () -> Unit = {
        navController.navigate(AllDestinations.SEARCH_BY_CATEGORY)
    }

    val navigateToRequestABook: () -> Unit = {
        navController.navigate(AllDestinations.REQUEST_A_BOOK)
    }

}
