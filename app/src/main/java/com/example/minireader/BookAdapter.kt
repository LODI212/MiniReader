package com.example.minireader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var items: List<Book>,
    private val onAddClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.bookImage)
        val title: TextView = view.findViewById(R.id.bookTitle)
        val desc: TextView = view.findViewById(R.id.bookDescription)
        val addBtn: TextView = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_with_button, parent, false)
        return BookViewHolder(v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title
        holder.desc.text = item.description

        val imageName =
            if (!item.image.isNullOrEmpty()) item.image else item.cover

        val resId = holder.img.context.resources.getIdentifier(
            imageName, "drawable", holder.img.context.packageName
        )

        holder.img.setImageResource(if (resId != 0) resId else R.drawable.placeholder)

        holder.addBtn.setOnClickListener {
            onAddClick(item)
        }
    }

    fun update(newItems: List<Book>) {
        items = newItems
        notifyDataSetChanged()
    }
}
