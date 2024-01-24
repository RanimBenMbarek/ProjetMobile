package com.example.projetmobile

import VolumeInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {

    private val popularBooksResponse = MutableLiveData<book>()
    var popularBooks : LiveData<book> = popularBooksResponse

    private val searchBooksResponse = MutableLiveData<book>()
    var searchBooks : LiveData<book> = searchBooksResponse

    private val bookResponse = MutableLiveData<book>()
    var books : LiveData<book> = bookResponse

    private val volumeResponse = MutableLiveData<VolumeInfo>()
    var volume : LiveData<VolumeInfo> = volumeResponse

    private val _apiError = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiError


    init {
        getPopularBooks();
        getBooks();
    }

    private fun getPopularBooks() {
        RetrofitHelper.retrofitService.getPopularBooks().enqueue(
            object : Callback<book> {
                override fun onResponse(
                    call: Call<book>,
                    response: Response<book>
                ) {
                    if (response.isSuccessful) {
                        popularBooksResponse.value = response.body()
                    }else{

                    }
                }

                override fun onFailure(call: Call<book>, t: Throwable) {
                    // Handle failure, if needed
                }
            }
        )
    }

    fun searchBooks(searchWord:String) {
        RetrofitHelper.retrofitService.searchBooks(searchWord).enqueue(
            object : Callback<book> {
                override fun onResponse(
                    call: Call<book>,
                    response: Response<book>
                ) {
                    if (response.isSuccessful) {
                        searchBooksResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<book>, t: Throwable) {
                    // Handle failure, if needed
                }
            }
        )
    }

    private fun getBooks() {
        RetrofitHelper.retrofitService.getBooks().enqueue(
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

    private fun getVolumeById(volumeId:String) {
        RetrofitHelper.retrofitService.getVolumeById(volumeId).enqueue(
            object : Callback<VolumeInfo> {
                override fun onResponse(
                    call: Call<VolumeInfo>,
                    response: Response<VolumeInfo>
                ) {
                    if (response.isSuccessful) {
                        volumeResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<VolumeInfo>, t: Throwable) {
                    // Handle failure, if needed
                }


            }
        )
    }
}


