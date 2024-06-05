package xyz.leomurca.lmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.leomurca.lmdb.navigation.HOME_ROUTE
import xyz.leomurca.lmdb.navigation.detailsScreen
import xyz.leomurca.lmdb.navigation.homeScreen
import xyz.leomurca.lmdb.navigation.navigateToDetails
import xyz.leomurca.lmdb.ui.theme.LMDBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LMDBTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HOME_ROUTE) {
                    homeScreen { movieId -> navController.navigateToDetails(movieId) }
                    detailsScreen { navController.popBackStack() }
                }
            }
        }
    }
}
