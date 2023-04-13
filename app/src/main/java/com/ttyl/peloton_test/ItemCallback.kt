package com.ttyl.peloton_test

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemCallback(private val viewContract: ViewContract): Callback<Array<Item>> {
    override fun onResponse(call: Call<Array<Item>>, response: Response<Array<Item>>) {
        response.body()?.let {
            println("count: ${it.size}")
            viewContract.updateAdapter(it)
            viewContract.dismissProgressBar()
        }
    }

    override fun onFailure(call: Call<Array<Item>>, t: Throwable) {
        Log.e("item callback failure", "error: ${t.localizedMessage}")
        viewContract.updateAdapter(arrayOf())
        viewContract.showEmptyList()
        viewContract.dismissProgressBar()
    }
}