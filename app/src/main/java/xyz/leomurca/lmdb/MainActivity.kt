package xyz.leomurca.lmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import xyz.leomurca.lmdb.ui.details.DetailsScreen
import xyz.leomurca.lmdb.ui.home.HomeScreen
import xyz.leomurca.lmdb.ui.theme.LMDBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LMDBTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = "home") {
                        HomeScreen(
                            navigateToDetails = { movieId -> navController.navigate("details/$movieId") },
                        )
                    }
                    composable(
                        route = "details/{movieId}",
                        arguments = listOf(
                            navArgument("movieId") { type = NavType.LongType },
                        ),
                    ) {
                        DetailsScreen()
                    }
                }
            }
        }
    }
}
