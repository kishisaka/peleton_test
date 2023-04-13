package com.ttyl.peloton_test

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {
    @GET("/posts")
    fun getItems(): Call<Array<Item>>

    @GET("/posts")
    fun getItemsById(@Query("id")id: Int): Call<Array<Item>>
}