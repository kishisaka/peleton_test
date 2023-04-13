package com.ttyl.peloton_test

interface ViewContract {
    fun updateAdapter(items: Array<Item>)
    fun showProgressBar()
    fun dismissProgressBar()
    fun showEmptyList()
    fun hideEmptyList()
}