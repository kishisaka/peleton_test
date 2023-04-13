package com.ttyl.peloton_test

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ItemDao {
    private var itemService: ItemService
    companion object {
        const val HTTP_OK = 200
        const val URL = "https://jsonplaceholder.typicode.com/"
    }

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(object: Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val response = chain.proceed(request)
                    // log this to whatever observability platform you have
                    if (response.code() != HTTP_OK) {
                        Log.e("ItemDao Request", "${request.method()} " +
                                "${request.url()}, ${response.code()}, ${response.body()}")
                    }
                    return response
                }
            })
            .build()
        itemService = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(ItemService::class.java)
    }

    fun getItems(callBack: Callback<Array<Item>>) {
        return itemService.getItems().enqueue(callBack)
    }

    fun getItemsById(callBack: Callback<Array<Item>>, id: Int) {
        return itemService.getItemsById(id).enqueue(callBack)
    }
}