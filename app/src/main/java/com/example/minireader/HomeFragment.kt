package com.example.minireader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var popularAdapter: PopularAdapter
    private lateinit var allAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val popularRecycler = view.findViewById<RecyclerView>(R.id.popularRecycler)
        val allRecycler = view.findViewById<RecyclerView>(R.id.homeRecycler)

        val repo = BookRepository(requireContext())
        val books = repo.loadBooks()

        val popularBooks = books.take(5)

        popularRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        popularAdapter = PopularAdapter(popularBooks) { book ->
            MyBooksStorage.add(requireContext(), book)
            Toast.makeText(requireContext(), "Added to Library", Toast.LENGTH_SHORT).show()
        }

        popularRecycler.adapter = popularAdapter

        allRecycler.layoutManager = LinearLayoutManager(requireContext())

        allAdapter = BookAdapter(books) { book ->
            MyBooksStorage.add(requireContext(), book)
            Toast.makeText(requireContext(), "Added to Library", Toast.LENGTH_SHORT).show()
        }

        allRecycler.adapter = allAdapter

        return view
    }
}
