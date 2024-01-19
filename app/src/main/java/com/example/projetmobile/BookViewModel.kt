package com.example.projetmobile

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {
    private val bookResponse = MutableLiveData<Item>()
    var weather : LiveData<Item> = bookResponse
/*
   init {
       getWeather("Tunis")
   }

private fun getBook(city : String){
       RetrofitHelper.retrofitService.getBook(city).enqueue(
           object : Callback<bookResponse>{
               override fun onResponse(
                   call: Call<weatherResponse>,
                   response: Response<weatherResponse>
               ) {
                   if(response.isSuccessful){
                       weatherReponse.value = response.body()
                   }
               }

               override fun onFailure(call: Call<weatherResponse>, t: Throwable) {

               }

           }
       )
   }

   fun changeCity(city : String) : String?{
       getWeather(city)
       weather = weatherReponse
       return weatherReponse.value?.name
   }


 */
}