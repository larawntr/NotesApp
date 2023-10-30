package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.Screens.CreateNewNoteScreen
import com.example.notesapp.Screens.EditScreen
import com.example.notesapp.Screens.HomeScreen

// https://youtu.be/4gUeyNkGE3g?si=h63LAjM4Ygj5uNyN
/*
TODO
add arguments for navigation
 */

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            route = Screen.HomeScreen.route + "?id={id}?title={title}?description={description}?additionalInfo={additionalInfo}",
            arguments = listOf(
                navArgument("title") {
                    defaultValue = " "
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("description") {
                    defaultValue = " "
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("additionalInfo") {
                    defaultValue = " "
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("id") {
                    defaultValue = " "
                    type = NavType.StringType
                    nullable = true
                }
            )

        ) { entry ->
            HomeScreen(
                navController = navController,
                id = entry.arguments?.getString("id")!!,
                title = entry.arguments?.getString("title")!!,
                additionalInfo = entry.arguments?.getString("additionalInfo")!!,
                description = entry.arguments?.getString("description")!!
            )
        }
        composable(route = Screen.CreateNewNoteScreen.route) {
            CreateNewNoteScreen(navController = navController)
        }
        composable(route = Screen.EditScreen.route) {
            EditScreen(navController)
        }
    }
}
