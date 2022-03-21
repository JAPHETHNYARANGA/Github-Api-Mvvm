package com.fuzupay.reposproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.adapter.SearchAdapter
import com.fuzupay.reposproject.viewmodel.SearchViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

   private lateinit var searchRecyclerViewAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


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
                searchProgress.setVisibility(View.INVISIBLE)
                searchRecyclerViewAdapter.searchSetListData(it.items)
                searchRecyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this@SearchActivity,"Error in getting data", Toast.LENGTH_LONG).show()
                searchProgress.setVisibility(View.INVISIBLE)
            }
        })

        btnSearch.setOnClickListener {
            searchViewModel.makeSearchApiCall(et_Search.text.toString())
            et_Search.setText("")
            searchProgress.setVisibility(View.VISIBLE)
        }

    }

    //menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this@SearchActivity, MainActivity::class.java))
                true
            }
            R.id.search -> {
                true
            }

            R.id.signout -> {
                signOut()
                true
            }
            R.id.menuRefresh ->{
                searchInitRecyclerView()
                searchCreateData()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        Firebase.auth.signOut()
        startActivity(Intent(this@SearchActivity, LoginActivity::class.java))
        finish()
    }






}