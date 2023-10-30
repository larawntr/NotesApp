package com.example.notesapp

class Validation {

    fun validateTitle(title: String): Boolean {
        return title.length in 3..50
    }

    fun validateText(description: String): Boolean {
        return description.length <= 120
    }
}
