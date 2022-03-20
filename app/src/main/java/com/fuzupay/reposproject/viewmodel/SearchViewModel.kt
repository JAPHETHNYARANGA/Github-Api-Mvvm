package com.fuzupay.reposproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fuzupay.reposproject.models.myGithubModel
import com.fuzupay.reposproject.network.RetrofitInstance
import com.fuzupay.reposproject.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel :ViewModel() {
    lateinit var searchRecyclerListData: MutableLiveData<myGithubModel>

    init{
        searchRecyclerListData = MutableLiveData()
    }

    fun getSearchRecyclerListDataObserver() :MutableLiveData<myGithubModel>{
        return searchRecyclerListData
    }
    fun makeSearchApiCall(input:String){
        val searchRetroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val searchCall = searchRetroInstance.getDataFromApi(input)

        searchCall.enqueue(object : Callback<myGithubModel>{
            override fun onResponse(call: Call<myGithubModel>, response: Response<myGithubModel>) {
                if(response.isSuccessful){
                    searchRecyclerListData.postValue(response.body())
                }else{
                    searchRecyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<myGithubModel>, t: Throwable) {
               searchRecyclerListData.postValue(null)
            }
        })
    }
}