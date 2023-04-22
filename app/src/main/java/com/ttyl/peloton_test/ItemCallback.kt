package com.ttyl.peloton_test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemCallback(private val itemDoneState: MutableLiveData<Array<Item>>): Callback<Array<Item>> {

    override fun onResponse(call: Call<Array<Item>>, response: Response<Array<Item>>) {
        response.body()?.let {
            itemDoneState.value = response.body()
        }
    }

    override fun onFailure(call: Call<Array<Item>>, t: Throwable) {
        Log.e("item callback failure", "error: ${t.localizedMessage}")
        itemDoneState.value = arrayOf()
    }
}