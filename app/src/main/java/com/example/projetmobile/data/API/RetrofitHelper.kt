package com.example.projetmobile.data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl ="https://www.googleapis.com/books/v1/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService : BookAPI by lazy { retrofit.create(BookAPI::class.java) }

}