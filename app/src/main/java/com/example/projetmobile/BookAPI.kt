package com.example.projetmobile

import book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {
    @GET("volumes?q=war&langRestrict=en&key=AIzaSyAMgls3MVSdN3YVefr5cWDvEsY32T_bYpQ")
    fun getBook(@Query("q") search : String): Call<book>
}