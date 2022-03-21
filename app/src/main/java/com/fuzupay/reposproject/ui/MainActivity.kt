package com.fuzupay.reposproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.adapter.GitHubAdapter
import com.fuzupay.reposproject.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter : GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toSearch.setOnClickListener {
            startActivity(Intent(this@MainActivity,SearchActivity::class.java))
        }
        initRecyclerView()
        createData()
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
}