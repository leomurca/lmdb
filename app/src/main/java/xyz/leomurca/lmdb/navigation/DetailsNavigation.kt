package xyz.leomurca.lmdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import xyz.leomurca.lmdb.ui.details.DetailsScreen

private const val MOVIE_ID = "movieId"
private const val DETAILS_ROUTE_PREFIX = "details"
const val DETAILS_ROUTE = "$DETAILS_ROUTE_PREFIX/{$MOVIE_ID}"

fun NavController.navigateToDetails(movieId: Long) = navigate("$DETAILS_ROUTE_PREFIX/$movieId")

fun NavGraphBuilder.detailsScreen(onTapBack: () -> Unit) {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(MOVIE_ID) { type = NavType.LongType },
        ),
    ) {
        DetailsScreen(onTapBack = onTapBack)
    }
}
