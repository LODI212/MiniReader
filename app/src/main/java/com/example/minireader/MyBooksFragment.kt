package com.example.minireader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyBooksFragment : Fragment() {

    private lateinit var adapter: MyBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_mybooks, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.myBooksRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val clearBtn = view.findViewById<View>(R.id.clearButton)

        var savedBooks = MyBooksStorage.get(requireContext())

        adapter = MyBooksAdapter(savedBooks)
        recycler.adapter = adapter

        clearBtn.setOnClickListener {
            MyBooksStorage.clear(requireContext())

            savedBooks = mutableListOf()             // очищаем локальный список

            adapter.update(savedBooks)               // обновляем UI

            Toast.makeText(requireContext(), "Library cleared", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.update(MyBooksStorage.get(requireContext()))
    }
}

