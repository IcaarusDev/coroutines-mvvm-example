package com.icaarusdev.coroutines_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icaarusdev.coroutines_mvvm.model.CountriesAdapter
import com.icaarusdev.coroutines_mvvm.viewmodel.MainViewModel
import com.icaarusdev.coroutines_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var countryListAdapter = CountriesAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.refresh()

        binding.recyclerView.apply{
            adapter = countryListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.countries.observe(this, {
            countryListAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
            binding.textView.visibility = View.GONE
        })


    }
}