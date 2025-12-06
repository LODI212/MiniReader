package com.example.minireader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyBooksAdapter(
    private var items: MutableList<Book>   // ← ДОЛЖНО быть mutable
) : RecyclerView.Adapter<MyBooksAdapter.MyBookViewHolder>() {

    class MyBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.bookImage)
        val title: TextView = view.findViewById(R.id.bookTitle)
        val desc: TextView = view.findViewById(R.id.bookDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return MyBookViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyBookViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title
        holder.desc.text = item.description

        val ctx = holder.itemView.context
        val imgName = item.cover ?: item.image ?: ""

        val resId = ctx.resources.getIdentifier(imgName, "drawable", ctx.packageName)
        holder.img.setImageResource(if (resId != 0) resId else R.drawable.placeholder)
    }

    fun update(newItems: MutableList<Book>) {
        items = newItems
        notifyDataSetChanged()
    }
}
