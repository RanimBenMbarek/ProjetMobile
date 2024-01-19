package com.example.projetmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl ="https://www.googleapis.com/books/v1/"
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : BookAPI by lazy { retrofit.create(BookAPI::class.java) }

}