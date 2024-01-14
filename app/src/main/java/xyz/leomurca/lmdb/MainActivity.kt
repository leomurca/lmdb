package xyz.leomurca.lmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import xyz.leomurca.lmdb.ui.home.HomeScreen
import xyz.leomurca.lmdb.ui.home.HomeViewModel
import xyz.leomurca.lmdb.ui.theme.LMDBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LMDBTheme {
                HomeScreen(viewModel)
            }
        }
    }
}
