package com.example.minireader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PopularAdapter(
    private val items: List<Book>,
    private val onClick: (Book) -> Unit
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.popularImage)
        val title: TextView = view.findViewById(R.id.popularTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title

        val context = holder.itemView.context
        val resId = context.resources.getIdentifier(
            item.cover,
            "drawable",
            context.packageName
        )

        holder.img.setImageResource(if (resId != 0) resId else R.drawable.placeholder)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
