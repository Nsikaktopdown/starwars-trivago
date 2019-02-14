package io.altalabs.androidbase.ui.films.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import io.altalabs.androidbase.R
import io.altalabs.androidbase.ui.films.FilmModel
import kotlinx.android.synthetic.main.activity_film_detail.*
import kotlinx.android.synthetic.main.content_film_detail.*

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val film = intent.getParcelableExtra("film") as? FilmModel
        if (film != null) {
            film_detail_title.text = film.title
            film_detail_release_date.text = film.release_date
            film_detail_openin_crawl.text = film.opening_crawl.replace("\r\n","" )
            film_detail_director.text = film.director
            film_detail_producer.text = film.producer
        }
        toolbar_layout.isTitleEnabled = false
    }

    companion object {
        @JvmStatic
        fun intent(context: Context, film: FilmModel) = Intent(context, FilmDetailActivity::class.java).apply {
            putExtra("film", film)
        }
    }
}
