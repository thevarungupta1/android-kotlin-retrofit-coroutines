package com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thevarungupta.kotlin_coroutines_retrofit_demo.R
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api.ApiHelper
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api.RetrofitBuilder
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.model.Post
import com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.base.ViewModelFactory
import com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.adapter.MainAdapter
import com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.viewmodel.MainViewModel
import com.thevarungupta.kotlin_coroutines_retrofit_demo.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this)
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )
        recycler_view.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getPosts().observe(this, Observer {
            it?.let {
                resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        recycler_view.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        resource.data?.let { list -> fillData(list) }
                    }
                    Status.ERROR -> {
                        recycler_view.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                        recycler_view.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun fillData(list: ArrayList<Post>){
        adapter.setData(list)
    }
}