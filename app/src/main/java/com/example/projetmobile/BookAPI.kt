package com.example.projetmobile

import VolumeInfo
import book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookAPI {
    @GET("volumes?q=love&langRestrict=en&key=AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ")
    fun getPopularBooks(): Call<book>

    @GET("volumes?q=generation&langRestrict=en&key=AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ")
    fun getBooks(): Call<book>

    @GET("volumes")
    fun searchBooks(
        @Query("q") search: String,
        @Query("langRestrict") langRestrict: String = "en",
        @Query("key") apiKey: String = "AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ"
    ): Call<book>
}