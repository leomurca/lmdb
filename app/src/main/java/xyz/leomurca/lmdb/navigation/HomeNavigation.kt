package xyz.leomurca.lmdb.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.leomurca.lmdb.ui.home.HomeScreen

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(onTapMovie: (movieId: Long) -> Unit) {
    composable(
        route = HOME_ROUTE,
    ) {
        HomeScreen(onTapMovie = onTapMovie)
    }
}
