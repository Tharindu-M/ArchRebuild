package app.example.archrebuild.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.example.archrebuild.ui.home.HomeRoute

object ArchRebuildDestinations {
    const val HOME_ROUTE = "home"
}

@Composable
fun ArchRebuildNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = ArchRebuildDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = ArchRebuildDestinations.HOME_ROUTE) {
            HomeRoute()
        }
    }
}
