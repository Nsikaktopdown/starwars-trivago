package io.altalabs.androidbase.ui.films.detail

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import io.altalabs.androidbase.DataState
import io.altalabs.androidbase.R
import io.altalabs.androidbase.di.AppViewModelFactory
import io.altalabs.androidbase.toast
import io.altalabs.androidbase.ui.base.BaseActivity
import io.altalabs.androidbase.ui.films.FilmModel
import io.altalabs.androidbase.viewModelProvider
import kotlinx.android.synthetic.main.activity_film_detail.*
import kotlinx.android.synthetic.main.content_film_detail.*
import javax.inject.Inject

class FilmDetailActivity : BaseActivity() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //viewmodel
        val viewModel = viewModelProvider<FilmDetailViewModel>(appViewModelFactory)

        val film = intent.getParcelableExtra("film") as? FilmModel
        if (film != null) {
            showDetails(film)
        }
        val film_id = intent.getIntExtra("film_id", 0)
        if(film_id != 0){
            viewModel.getFilm(film_id)
        }
        toolbar_layout.isTitleEnabled = false

        viewModel.film.observe(this, Observer {
            it?.let {
                when(it.dataState){
                    DataState.ERROR -> toast("Film not found", this@FilmDetailActivity)
                }

                it.data?.let {
                    showDetails(it)
                }
            }
        })
    }

    private  fun showDetails(film: FilmModel){
        film_detail_title.text = film.title
        film_detail_release_date.text = film.release_date
        film_detail_openin_crawl.text = film.opening_crawl.replace("\r\n", "")
        film_detail_director.text = film.director
        film_detail_producer.text = film.producer
    }
    companion object {
        @JvmStatic
        fun intent(context: Context, film: FilmModel) = Intent(context, FilmDetailActivity::class.java).apply {
            putExtra("film", film)
        }

        @JvmStatic
        fun intent(context: Context, film_id: Int) = Intent(context, FilmDetailActivity::class.java).apply {
            putExtra("film_id", film_id)
        }
    }
}
