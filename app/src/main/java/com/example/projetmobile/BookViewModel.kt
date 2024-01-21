package com.example.projetmobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {
    private val bookResponse = MutableLiveData<book>()
    var books : LiveData<book> = bookResponse
    init {
        getBooks();
    }

    private fun getBooks() {
        RetrofitHelper.retrofitService.getBook().enqueue(
            object : Callback<book> {
                override fun onResponse(
                    call: Call<book>,
                    response: Response<book>
                ) {
                    if (response.isSuccessful) {
                        bookResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<book>, t: Throwable) {
                    // Handle failure, if needed
                }
            }
        )
    }

/*
   fun changeCity(city : String) : String?{
       getWeather(city)
       weather = weatherReponse
       return weatherReponse.value?.name
   }
*/


}


