package com.example.githubtrendingrepoapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(value="repositories")
    fun getData(): Call<List<APIResponseObjectItem>>
}