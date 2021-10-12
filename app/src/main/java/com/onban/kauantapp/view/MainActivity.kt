package com.onban.kauantapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onban.kauantapp.R
import com.onban.kauantapp.common.adapter.MainListAdapter
import com.onban.kauantapp.common.adapter.StickyHeaderItemDecoration
import com.onban.kauantapp.databinding.ActivityMainBinding
import com.onban.kauantapp.getData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }
    private fun initViews() {
        adapter = MainListAdapter()
        with(binding) {
            rcvMain.adapter = adapter
            rcvMain.addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
            adapter.submitList(getData())
        }
    }

    private fun getSectionCallback(): StickyHeaderItemDecoration.SectionCallback {
        return object : StickyHeaderItemDecoration.SectionCallback {
            override fun isHeader(position: Int): Boolean {
                return adapter.isHeader(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderView(list, position)
            }
        }
    }
}