package com.fuzupay.reposproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.adapter.GitHubAdapter
import com.fuzupay.reposproject.adapter.SearchAdapter
import com.fuzupay.reposproject.viewmodel.MyViewModel
import com.fuzupay.reposproject.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

   private lateinit var searchRecyclerViewAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        toHome.setOnClickListener {
            startActivity(Intent(this@SearchActivity, MainActivity::class.java))
        }

        searchInitRecyclerView()
        searchCreateData()

    }

    private fun searchInitRecyclerView() {
        RecyclerViewSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            searchRecyclerViewAdapter = SearchAdapter()
            adapter = searchRecyclerViewAdapter

            val searchDecoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(searchDecoration)
        }
    }

    private fun searchCreateData(){
        val searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchViewModel.getSearchRecyclerListDataObserver().observe(this, Observer{
            if(it != null){
                searchRecyclerViewAdapter.searchSetListData(it.items)
                searchRecyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this@SearchActivity,"Error in getting data", Toast.LENGTH_LONG).show()
            }
        })

        btnSearch.setOnClickListener {
            searchViewModel.makeSearchApiCall(et_Search.text.toString())
        }
    }


}