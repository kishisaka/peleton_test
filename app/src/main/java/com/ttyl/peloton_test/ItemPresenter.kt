package com.ttyl.peloton_test

import java.lang.Exception

class ItemPresenter(private val view: ViewContract,
                    private val itemViewModel: ItemViewModel) {

    fun getInitialData() {
        view.showProgressBar()
        view.hideEmptyList()
        itemViewModel.getData()
    }

    fun showData(items: Array<Item>) {
        view.updateAdapter(items)
        if (items.isEmpty()) {
            view.showEmptyList()
        } else {
            view.hideEmptyList()
        }
        view.dismissProgressBar()
    }

    fun getDataById(id: String) {
        try {
            view.showProgressBar()
            view.hideEmptyList()
            itemViewModel.getDataById(Integer.parseInt(id))
        } catch (e: Exception) {
            // will happen when id cannot be parsed into an Int
            view.showEmptyList()
        }
    }
}