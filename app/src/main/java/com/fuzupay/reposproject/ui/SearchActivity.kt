package com.fuzupay.reposproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

   private lateinit var myRecyclerViewAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        toHome.setOnClickListener {
            startActivity(Intent(this@SearchActivity, MainActivity::class.java))
        }

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        RecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            myRecyclerViewAdapter = SearchAdapter()

            adapter = myRecyclerViewAdapter

            val decorate = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decorate)
        }
    }
    private fun createData(){
        val viewModel1 = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel1.getRecyclerListDataObserver().observe(this, Observer{
            if(it != null){
                myRecyclerViewAdapter.setListData(it.items)
                myRecyclerViewAdapter.notifyDataSetChanged()
            }else{
                android.widget.Toast.makeText(this@SearchActivity,"Error in getting data from Api",android.widget.Toast.LENGTH_LONG).show()
            }
        })


       btnSearch.setOnClickListener {
           viewModel1.makeApiCall(et_Search.text.toString())
       }
    }
}