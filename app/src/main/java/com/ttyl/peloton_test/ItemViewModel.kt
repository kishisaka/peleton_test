package com.ttyl.peloton_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao,
                    private val viewContract: ViewContract,
                    private val dispatcher: CoroutineDispatcher): ViewModel() {

//    val itemDoneState: LiveData<Array<Item>> get() = _itemDoneState
//
//    private val _itemDoneState: MutableLiveData<Array<Item>> by lazy {
//        MutableLiveData<Array<Item>>()
//    }

    fun getData(itemCallback: ItemCallback) {
        viewContract.showProgressBar()
        viewContract.hideEmptyList()
        CoroutineScope(dispatcher).launch {
            itemDao.getItems(itemCallback)
//            val items = itemDao.getItems(callback)
////            withContext(Dispatchers.Main) {
////                items?.let {
////                    _itemDoneState.value = it
////                }
////            }
        }
    }

    fun getDataById(itemCallback: ItemCallback, id: Int) {
        viewContract.showProgressBar()
        viewContract.hideEmptyList()
        CoroutineScope(dispatcher).launch {
            itemDao.getItemsById(itemCallback, id)
//            val items = itemDao.getItems(callback)
////            withContext(Dispatchers.Main) {
////                items?.let {
////                    _itemDoneState.value = it
////                }
////            }
        }
    }

}