package io.altalabs.androidbase.ui.character

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.substring
import android.view.View
import android.widget.AdapterView
import io.altalabs.androidbase.R
import io.altalabs.androidbase.ui.search.CharacterModel
import io.altalabs.androidbase.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_character_details.*
import android.widget.ArrayAdapter
import io.altalabs.androidbase.toast
import io.altalabs.androidbase.ui.films.detail.FilmDetailActivity


class CharacterDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(io.altalabs.androidbase.R.layout.activity_character_details)

        var character = intent.getParcelableExtra<CharacterModel>("character")

        supportActionBar?.title = "Character Detail"

        if (character != null) {
            character_detail_name.text = character.name
            character_detail_dob.text = character.birth_year
            character_detail_height.text = "${character.height} CM"
            setUpFilmListView(character.films)

        }
    }

    companion object {
        @JvmStatic
        fun intent(context: Context, character: CharacterModel) = Intent(context, CharacterDetailsActivity::class.java).apply {
            putExtra("character", character)
        }
    }


    private fun setUpFilmListView(films: List<String>) {
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, films)
        film_list.adapter = adapter

        film_list.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val item = films.get(position)
                val movie_id =  item.substring(item.lastIndexOf('/') -1).replace("/", "")
                startActivity(FilmDetailActivity.intent(applicationContext, movie_id.toInt()))
            }
        }
    }


}
