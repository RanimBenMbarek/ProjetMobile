package com.example.projetmobile

import book
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {
    @GET("volumes?q=love&langRestrict=en&key=AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ")
    fun getPopularBooks(): Call<book>
    @GET("volumes?q=war&langRestrict=en&key=AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ")
    fun getBooks(): Call<book>
}