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

    lateinit var recyclerListData: MutableLiveData<myGithubModel>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver() : MutableLiveData<myGithubModel> {
        return recyclerListData
    }
    fun makeApiCall(input:String){
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.getDataFromApi(input)

        call.enqueue(object: Callback<myGithubModel> {
            override fun onResponse(call: Call<myGithubModel>, response: Response<myGithubModel>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<myGithubModel>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}