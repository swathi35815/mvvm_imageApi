package com.example.mvvmrepopattern.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrepopattern.R
import com.example.mvvmrepopattern.databinding.ActivityMainBinding
import com.example.mvvmrepopattern.model.data.ImageData
import com.example.mvvmrepopattern.viewmodel.FetchDataViewModel

class MainActivity : AppCompatActivity(), FetchDataInterface {
    lateinit var mainXml : ActivityMainBinding
    private lateinit var fetchDataViewModel:FetchDataViewModel
    private var myAdapter = ImageAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainXml = DataBindingUtil.setContentView(this,R.layout.activity_main)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fetchDataViewModel = ViewModelProvider(this).get(FetchDataViewModel::class.java)
        mainXml.layoutViewmodel = fetchDataViewModel
        fetchDataViewModel.fetchDataListener = this

        mainXml.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mainXml.recyclerView.adapter = myAdapter
    }

    override fun onFetching() {
        mainXml.buttonFetchData.visibility = View.INVISIBLE
        mainXml.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(success: LiveData<ArrayList<ImageData>>) {
        success.observe(this, Observer {
            mainXml.buttonFetchData.visibility = View.INVISIBLE
            mainXml.progressBar.visibility = View.INVISIBLE
            mainXml.recyclerView.visibility = View.VISIBLE
            myAdapter.newImageData(success.value?: arrayListOf())
            Log.i("myTag", success.value.toString())
        })
    }

    override fun onFailure(message: String) {
        mainXml.progressBar.visibility = View.INVISIBLE
        mainXml.buttonFetchData.visibility = View.VISIBLE
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }

}