package com.example.notesapp.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.ui.theme.NotesAppTheme


@Composable
fun EditScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Edit mode",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(all = 25.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    NotesAppTheme {
        EditScreen(navController = rememberNavController())
    }
}