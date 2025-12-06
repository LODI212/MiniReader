package com.example.minireader

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {

    private lateinit var adapter: BookAdapter
    private lateinit var books: List<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val input = view.findViewById<EditText>(R.id.searchInput)
        val recycler = view.findViewById<RecyclerView>(R.id.searchRecycler)

        recycler.layoutManager = LinearLayoutManager(requireContext())

        val repo = BookRepository(requireContext())
        books = repo.loadBooks()

        adapter = BookAdapter(books.toMutableList()) { book ->
            MyBooksStorage.add(requireContext(), book)
            Toast.makeText(requireContext(), "Added to My Library", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = adapter

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val q = s.toString().trim().lowercase()

                val filtered = books.filter {
                    it.title.lowercase().contains(q)
                }

                adapter.update(filtered)
            }
        })

        return view
    }
}
