package com.example.notesapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.Screen
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import java.util.UUID

data class Note(
    val id: String,
    var title: String,
    var description: String,
    var additionalInformation: String,
    var isDone: MutableState<Boolean> = mutableStateOf(false)
)


val note: Note = Note("ABC", ("Aufräumen"), ("ich muss aufräumen"), ("Nichts"))

var allNotes = mutableStateListOf<Note>(note)

fun addNewNote(newNote: Note){
    var noteExists = false
    for(note in allNotes){
        if(note.id == newNote.id){
            noteExists = true
        }
    }
    if(! noteExists){
        allNotes.add(newNote)
    }

}
@Composable
fun HomeScreen(
    id: String,
    navController: NavController,
    title: String,
    additionalInfo: String,
    description: String
) {
    // prevent from too many recompositions
    LaunchedEffect(Unit){
        if(title != null){
            var newNote = Note(id, title, description, additionalInfo)
            allNotes.add(newNote)
            addNewNote(newNote)
        }
    }
    println("Title: " + title)
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notes app",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(all = 35.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                ) {
                    NoteList(allNotes, navController)
                }
            }

        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 40.dp, vertical = 40.dp),
            shape = CircleShape,
            onClick = {
                println("Clicked button")
                navController.navigate(Screen.CreateNewNoteScreen.route)
            }) {
            Icon(
                Icons.Default.Add,
                contentDescription = "add new note"
            )
        }

    }
}


@Composable
fun NoteEntry(note: Note, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 4.dp)
            .clickable(onClick = {
                navController.navigate(Screen.EditScreen.route)
            }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = note.isDone.value, onCheckedChange = {
            note.isDone.value = !note.isDone.value
            println("checked")
        })
        Column(
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = note.title,
                fontSize = 20.sp
            )
            Text(
                text = note.description,
                modifier = Modifier.padding(all = 5.dp),
            )
        }
    }
}

@Composable
fun NoteList(allNotes: MutableList<Note>, navController: NavController) {
    LazyColumn {
        items(allNotes) { item ->
            NoteEntry(note = item, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NotesAppTheme {
        //HomeScreen(navController = rememberNavController())
    }
}