package io.altalabs.androidbase.ui.films

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import io.altalabs.androidbase.*
import io.altalabs.androidbase.di.AppViewModelFactory
import io.altalabs.androidbase.ui.base.BaseActivity
import io.altalabs.androidbase.ui.films.detail.FilmDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FilmListActivity : BaseActivity() {
    private val itemClick: (FilmModel) -> Unit = {
        startActivity(FilmDetailActivity.intent(this, it))
    }
    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    private val filmAdapter = FilmAdapter(itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        val viewModel = viewModelProvider<FilmActivityViewModel>(appViewModelFactory)

        viewModel.getFilms()
        swip_refresh.setOnRefreshListener {
            /**
             * Get Recipe from Data Source
             */
            viewModel.getFilms()
        }


        viewModel.film.observe(this, Observer { it ->
            it?.let {
                when (it.dataState) {
                    DataState.LOADING -> swip_refresh.showRefreshing()
                    DataState.SUCCESS -> swip_refresh.hidefreshing()
                    DataState.ERROR -> swip_refresh.hidefreshing()

                }

                it.data?.let { it -> filmAdapter.addItems(it) }
                it.message?.let { it -> toast(it, this@FilmListActivity) }
            }
        })

    }

    /**
     * Views initialization
     */
    fun initViews() {

        with(filmsRecyclerView) {
            adapter = filmAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@FilmListActivity, 2)
        }
    }


}
