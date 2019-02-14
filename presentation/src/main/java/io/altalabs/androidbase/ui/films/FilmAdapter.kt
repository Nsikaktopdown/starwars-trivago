package io.altalabs.androidbase.ui.films

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.altalabs.androidbase.R
import io.altalabs.androidbase.inflate
import kotlinx.android.synthetic.main.item_film_layout.view.*
import kotlinx.android.synthetic.main.item_recipe.view.*

class FilmAdapter constructor(private val itemClick: (FilmModel) -> Unit) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var items = ArrayList<FilmModel>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_film_layout)) {

        fun bind(items: FilmModel) {
         itemView.film_name_txt.text = items.title
            itemView.film_director_txt.text = items.director
            itemView.film_release_date_txt.text = items.release_date
            itemView.setOnClickListener { itemClick.invoke(items) }

        }

    }

    fun addItems(list: List<FilmModel>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}