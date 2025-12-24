package com.practicum.playlistmaker.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlistmaker.ui.SearchScreen
import com.practicum.playlistmaker.ui.main.MainScreen
import com.practicum.playlistmaker.ui.settings.SettingsScreen

@Composable
fun PlaylistHost(navController: NavHostController = rememberNavController()) {

    // Навигационные методы, которые будут передаваться в экраны
    fun navigateToSearch() {
        navController.navigate(Screen.SEARCH.name)
    }

    fun navigateToSettings() {
        navController.navigate(Screen.SETTINGS.name)
    }

    fun navigateToMain() {
        navController.navigate(Screen.MAIN.name) {
            // при необходимости - поведение backstack
        }
    }

    NavHost(navController = navController, startDestination = Screen.MAIN.name) {
        composable(Screen.MAIN.name) {
            MainScreen(
                onSearchClick = { navigateToSearch() },
                onPlaylistsClick = { /* TODO: реализовать позже */ },
                onFavoritesClick = { /* TODO: реализовать позже */ },
                onSettingsClick = { navigateToSettings() }
            )
        }

        composable(Screen.SEARCH.name) {
            SearchScreen(
                onBack = { navigateToMain() }
            )
        }

        composable(Screen.SETTINGS.name) {
            SettingsScreen(
                onBack = { navigateToMain() }
            )
        }
    }
}
