package com.example.minireader

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MyBooksStorage {

    private const val PREF_NAME = "my_books_pref"
    private const val KEY_LIST = "my_books_list"

    fun get(context: Context): MutableList<Book> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_LIST, "[]") ?: "[]"
        val type = object : TypeToken<MutableList<Book>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun add(context: Context, book: Book) {
        val list = get(context)

        if (list.any { it.id == book.id }) return

        list.add(book)
        save(context, list)
    }

    fun remove(context: Context, bookId: Int) {
        val list = get(context)
        val newList = list.filter { it.id != bookId }.toMutableList()
        save(context, newList)
    }

    fun clear(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LIST, "[]").apply()
    }

    private fun save(context: Context, list: MutableList<Book>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LIST, Gson().toJson(list)).apply()
    }
}
