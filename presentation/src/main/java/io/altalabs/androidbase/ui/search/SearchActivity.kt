package io.altalabs.androidbase.ui.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import io.altalabs.androidbase.*
import io.altalabs.androidbase.di.AppViewModelFactory
import io.altalabs.androidbase.ui.base.BaseActivity
import io.altalabs.androidbase.ui.character.CharacterDetailsActivity
import io.altalabs.androidbase.ui.films.FilmModel
import io.altalabs.androidbase.ui.films.detail.FilmDetailActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView.OnQueryTextListener {


    private val itemClick: (CharacterModel) -> Unit = {
        startActivity(CharacterDetailsActivity.intent(this, it))
    }

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory
    lateinit var viewModel: SearchActivityViewModel
    var query = ""
    val searchAdapter = SearchAdapter(itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.title = "Search"

        with(characterRecyclerView) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }

        //viewmodel
        viewModel = viewModelProvider<SearchActivityViewModel>(appViewModelFactory)

        query = intent.getStringExtra("query") ?: ""
        if (query.isNotEmpty()) {
            viewModel.searchForCharacter(query)
        }
        swip_refresh_search.setOnRefreshListener {
            empty_view.visibility = View.GONE
            viewModel.searchForCharacter(query)
        }
        viewModel.results.observe(this, Observer {
            it?.let {
                when (it.dataState) {
                    DataState.LOADING -> swip_refresh_search.showRefreshing()
                    DataState.SUCCESS -> swip_refresh_search.hidefreshing()
                    DataState.ERROR -> swip_refresh_search.hidefreshing()
                }

                it.data?.let {
                    if (it.isEmpty()) {
                        searchAdapter.clear()
                        empty_view.visibility = View.VISIBLE
                    } else {
                        empty_view.visibility = View.GONE
                        searchAdapter.addItems(it)
                    }
                }

                it.message?.let { toast(it, this) }
            }
        })
    }

    companion object {
        @JvmStatic
        fun intent(context: Context, query: String) = Intent(context, SearchActivity::class.java).apply {
            putExtra("query", query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_film_detail, menu)

        val searchItem = menu!!.findItem(R.id.searchBar)

        val searchView = searchItem.getActionView() as SearchView
        searchView.setQueryHint("Search Character")
        searchView.setOnQueryTextListener(this)
        searchView.setIconified(false)
        searchView.setQuery(query, false)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!!.isNotEmpty()) {
            viewModel.searchForCharacter(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}
