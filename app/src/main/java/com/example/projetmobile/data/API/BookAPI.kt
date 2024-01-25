package com.example.projetmobile.data.API

import book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {
    companion object {
        private const val key = "AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ"
    }

    @GET("volumes?q=love&langRestrict=en&key=$key")
    fun getPopularBooks(): Call<book>

    @GET("volumes?q=generation&langRestrict=en&key=$key")
    fun getBooks(): Call<book>

    @GET("volumes")
    fun searchBooks(
        @Query("q") search: String,
        @Query("langRestrict") langRestrict: String = "en",
        @Query("key") apiKey: String = key
    ): Call<book>
}

