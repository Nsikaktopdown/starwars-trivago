package io.altalabs.androidbase.ui.films

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import io.altalabs.androidbase.*
import io.altalabs.androidbase.di.AppViewModelFactory
import io.altalabs.androidbase.ui.base.BaseActivity
import io.altalabs.androidbase.ui.films.detail.FilmDetailActivity
import io.altalabs.androidbase.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FilmListActivity : BaseActivity(), SearchView.OnQueryTextListener {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_film_detail, menu)

        val searchItem = menu!!.findItem(R.id.searchBar)

        val searchView = searchItem.getActionView() as SearchView
        searchView.setQueryHint("Search Character")
        searchView.setOnQueryTextListener(this)
        searchView.setIconified(true)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       if(query!!.isNotEmpty()){
           startActivity(SearchActivity.intent(this, query))
       }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


}
