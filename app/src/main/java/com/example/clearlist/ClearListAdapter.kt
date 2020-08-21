package com.example.clearlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ClearListAdapter(
    private val items: List<Pair<String, String>>,
    private val listener: (pair: Pair<String, String>) -> Unit
) : RecyclerView.Adapter<ClearListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal fun bind(position: Int) {

            val pair = items[position]
            itemView.findViewById<AppCompatTextView>(R.id.tvwTitle).text = pair.first
            itemView.findViewById<AppCompatTextView>(R.id.tvwSubtitle).text = pair.second
            itemView.setOnClickListener { listener(pair) }

        }

    }
}
