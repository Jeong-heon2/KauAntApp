package com.onban.kauantapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import com.onban.kauantapp.R
import com.onban.kauantapp.common.adapter.MainListAdapter
import com.onban.kauantapp.databinding.ActivityMainBinding
import com.onban.kauantapp.getData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }
    private fun initViews() {
        with(binding) {
            rcvMain.adapter = MainListAdapter()
            (rcvMain.adapter as MainListAdapter).submitList(getData())
        }
    }
}