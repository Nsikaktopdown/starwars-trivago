package io.altalabs.androidbase.ui.search

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.altalabs.androidbase.R
import io.altalabs.androidbase.inflate
import io.altalabs.androidbase.ui.films.FilmModel
import kotlinx.android.synthetic.main.character_item.view.*
import kotlinx.android.synthetic.main.item_film_layout.view.*

class SearchAdapter constructor(private val itemClick: (CharacterModel) -> Unit) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var items = ArrayList<CharacterModel>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.character_item)) {

        fun bind(items: CharacterModel) {
            itemView.character_name.text = items.name
            itemView.character_dob.text = items.birth_year
            itemView.setOnClickListener {
                itemClick.invoke(items)
            }

        }

    }

    fun addItems(list: List<CharacterModel>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }
}