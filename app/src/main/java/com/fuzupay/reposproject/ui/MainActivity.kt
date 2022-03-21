package com.fuzupay.reposproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.adapter.GitHubAdapter
import com.fuzupay.reposproject.viewmodel.MyViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter : GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createData()

        //swipe refresh
      swipeRefresh.setOnRefreshListener {
          initRecyclerView()
          createData()
      }
    }

    private fun initRecyclerView(){
        RecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = GitHubAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun createData(){
        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer{
            if(it != null){
                mainProgress.setVisibility(View.INVISIBLE)
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this@MainActivity,"Error getting data fromApi", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.makeApiCall()
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
                true
            }
            R.id.search -> {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                true
            }

            R.id.signout -> {
                signOut()
                true
            }
            R.id.menuRefresh ->{
                initRecyclerView()
                createData()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun signOut() {
        Firebase.auth.signOut()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }



}