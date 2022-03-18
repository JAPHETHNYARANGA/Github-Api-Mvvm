package com.fuzupay.reposproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fuzupay.reposproject.models.myGithubModel
import com.fuzupay.reposproject.network.RetrofitInstance

class MyViewModel :ViewModel() {
    lateinit var recyclerListData: MutableLiveData<myGithubModel>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver() : MutableLiveData<myGithubModel>{
        return recyclerListData
    }
    fun makeApiCall(){
        val retroInstance = RetrofitInstance.getRetroInstance()
    }
}