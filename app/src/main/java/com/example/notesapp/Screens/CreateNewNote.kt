package com.example.notesapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.compose.ui.Alignment
import androidx.compose.runtime.setValue
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.Screen
import com.example.notesapp.Validation
import java.util.UUID


var newNote: Note = Note(" ", " ", " "," ")

@Composable
fun CreateNewNoteScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Create a new note",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(all = 25.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            NewTitleField()
            NewDescriptionField()
            NewAdditionalInfoField()
        }
        Column(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            DoneButton(navController)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTitleField() {
    var newTitle by remember { mutableStateOf("") }
    TextField(
        singleLine = true,
        label = { Text(text = "Your new note") },
        value = newTitle, onValueChange = { text ->
            newNote.title = text
            newTitle = text
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDescriptionField() {
    var newDescription by remember { mutableStateOf("") }
    TextField(
        singleLine = true,
        label = { Text(text = "Add a description") },
        value = newDescription, onValueChange = { text ->
            newNote.description = text
            newDescription = text
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAdditionalInfoField() {
    var newText by remember { mutableStateOf("") }
    TextField(
        singleLine = true,
        label = { Text(text = "Add a additional information") },
        value = newText, onValueChange = { text ->
            newNote.additionalInformation = text
            newText = text
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
            )
        }
    )
}

@Composable
fun DoneButton(navController: NavHostController) {
    FloatingActionButton(
        modifier = Modifier
            .padding(all = 40.dp),
        shape = CircleShape,
        onClick = {
            var validation = Validation()
            if (!validation.validateText(newNote.description)) {
                println("Description not correct!")
            }
            if (!validation.validateTitle(newNote.title)) {
                println("Title not correct!")
            } else {
                navController.navigate(Screen.HomeScreen.route +
                        "?id=" + UUID.randomUUID().toString() +
                        "?title=" + newNote.title +
                        "?description=" + newNote.description +
                        "?additionalInfo=" + newNote.additionalInformation )
            }

        },
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = "add new note"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CreateNewNoteScreenPreview() {
    NotesAppTheme {
        CreateNewNoteScreen(navController = rememberNavController())
    }
}