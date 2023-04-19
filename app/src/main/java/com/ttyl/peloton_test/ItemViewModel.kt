package com.ttyl.peloton_test

import androidx.lifecycle.*
import kotlinx.coroutines.*

class ItemViewModel: ViewModel() {

    val itemDoneState: LiveData<Array<Item>> get() = _itemDoneState

    private val dao = ItemDao()
    private val _itemDoneState: MutableLiveData<Array<Item>> by lazy {
        MutableLiveData<Array<Item>>()
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val callBack = ItemCallback(_itemDoneState)
            dao.getItems(callBack)
        }
    }

    fun getDataById(id: Int) {
        val callBack = ItemCallback(_itemDoneState)
        CoroutineScope(Dispatchers.IO).launch {
            dao.getItemsById(callBack, id)
        }
    }

}