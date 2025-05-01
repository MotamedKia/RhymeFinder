package com.example.rhymefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.RhymeFinderTheme
import com.example.rhymefinder.screens.NavGraphs
import com.example.rhymefinder.screens.destinations.AllWordsDestination
import com.example.rhymefinder.screens.destinations.HomeScreenDestination
import com.example.rhymefinder.screens.destinations.SavedScreenDestination
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                RhymeFinderTheme {
                    val navController = rememberNavController()
                    val currentBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = currentBackStackEntry?.destination?.route
                    val title = when (currentRoute) {
                        HomeScreenDestination.route -> "خانه"
                        SavedScreenDestination.route -> "ذخیره شده ها"
                        AllWordsDestination.route -> "جستجو"
                        else -> ""
                    }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(
                                        title,
                                        modifier = Modifier.padding(top = 6.dp),
                                        fontFamily = FontFamily(Font(R.font.onvan_font)),
                                        fontSize = 32.sp,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                        }, bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = currentRoute == SavedScreenDestination.route,
                                    onClick = { navController.navigate(SavedScreenDestination.route) },
                                    icon = { Icon(Icons.Default.Person, "") },
                                    label = {
                                        Text(
                                            "ذخیره شده ها",
                                            fontFamily = FontFamily(Font(R.font.vazirmatn_medium))
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = currentRoute == HomeScreenDestination.route,
                                    onClick = { navController.navigate(HomeScreenDestination.route) },
                                    icon = { Icon(Icons.Default.Home, "") },
                                    label = {
                                        Text(
                                            "خانه",
                                            fontFamily = FontFamily(Font(R.font.vazirmatn_medium))
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = currentRoute == AllWordsDestination.route,
                                    onClick = { navController.navigate(AllWordsDestination.route) },
                                    icon = { Icon(Icons.Default.Search, "") },
                                    label = {
                                        Text(
                                            "جستجو",
                                            fontFamily = FontFamily(Font(R.font.vazirmatn_medium))
                                        )
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
                        val context = LocalContext.current
                        val activity = LocalActivity.current
                        Hawk.init(context).build()
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                        BackHandler {
                            if (currentRoute == HomeScreenDestination.route) {
                                activity?.finish()
                            } else {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}