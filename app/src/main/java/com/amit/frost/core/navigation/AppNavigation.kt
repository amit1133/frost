package com.amit.frost.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amit.frost.core.navigation.Routes.HOME_SCREEN
import com.amit.frost.core.navigation.Routes.SEARCH_SCREEN
import com.amit.frost.core.presentation.util.ObserveAsEvents
import com.amit.frost.core.presentation.util.toString
import com.amit.frost.home.presentation.WeatherInfoAction
import com.amit.frost.home.presentation.WeatherInfoEvent
import com.amit.frost.home.presentation.WeatherInfoScreen
import com.amit.frost.home.presentation.WeatherInfoViewModel
import com.amit.frost.search.presentation.SearchScreen
import com.amit.frost.search.presentation.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    weatherInfoViewModel: WeatherInfoViewModel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel()
) {

    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(HOME_SCREEN) {
            val state by weatherInfoViewModel.state.collectAsStateWithLifecycle()
            val context = LocalContext.current
            ObserveAsEvents(events = weatherInfoViewModel.events) { event ->
                when (event) {
                    is WeatherInfoEvent.Error -> {
                        Toast.makeText(
                            context, event.error.toString(context), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            WeatherInfoScreen(state = state,
                onAction = { action ->
                    weatherInfoViewModel.onAction(action)
                    when (action) {
                        is WeatherInfoAction.OnSearchClick -> {
                            navController.navigate(SEARCH_SCREEN)
                        }
                    }
                },
                onRequestPermission = { weatherInfoViewModel.updatePermissionStatus(it) },
                onFetchLocation = { weatherInfoViewModel.fetchLocation() })
        }
        composable(SEARCH_SCREEN) {
            val state by searchViewModel.state.collectAsStateWithLifecycle()

            SearchScreen(state = state,
                onItemClick = { cityData ->
                    searchViewModel.saveCityLatLon(
                        lat = cityData.lat, lon = cityData.lon
                    )
                    navController.popBackStack()
                },
                onNavBackClick = { navController.popBackStack() },
                onSearchQueryChange = searchViewModel::onSearchQueryChange
            )
        }


    }

}