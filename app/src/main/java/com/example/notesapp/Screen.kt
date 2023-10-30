package com.example.notesapp

sealed class Screen (val route: String){
    object HomeScreen: Screen("homeScreen")
    object CreateNewNoteScreen: Screen("createNewNoteScreen")
    object EditScreen: Screen("detailScreen")
}