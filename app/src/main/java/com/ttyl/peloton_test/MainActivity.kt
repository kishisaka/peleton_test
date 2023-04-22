package com.ttyl.peloton_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttyl.peloton_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ViewContract {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var noItemsFound: TextView
    private lateinit var progressBar: ProgressBar
    private val adapter: ItemAdapter = ItemAdapter()
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        noItemsFound = binding.emptyList
        progressBar = binding.progressBar
        editText = binding.editTextTextPersonName
        button = binding.button
        recycler = binding.recycler
        recycler.layoutManager = linearLayoutManager
        recycler.adapter = adapter

        // create instance of view model
        val itemViewModel = ViewModelProvider(this)[(ItemViewModel::class.java)]
        val presenter = ItemPresenter(this, itemViewModel)

        // set observer to view model
        itemViewModel.itemDoneState.observe(this) { t ->
            presenter.showData(t)
        }

        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                presenter.getDataById(editText.text.toString())
            }
        })
        itemViewModel.getData()
    }

    override fun updateAdapter(items: Array<Item>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showEmptyList() {
        noItemsFound.visibility = View.VISIBLE
    }

    override fun hideEmptyList() {
        noItemsFound.visibility = View.GONE
    }

}