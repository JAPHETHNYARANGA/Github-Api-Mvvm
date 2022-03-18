package com.fuzupay.reposproject.network

import com.fuzupay.reposproject.models.myGithubModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getDataFromApi(@Query("q") query:String):Call<myGithubModel>
}