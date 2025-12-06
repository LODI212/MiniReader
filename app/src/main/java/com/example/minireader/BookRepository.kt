package com.example.minireader

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookRepository(private val context: Context) {

    fun loadBooks(): List<Book> {
        val json = context.assets.open("books.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Book>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun loadBookText(path: String): String {
        return context.assets.open("text/$path").bufferedReader().use { it.readText() }
    }


}
