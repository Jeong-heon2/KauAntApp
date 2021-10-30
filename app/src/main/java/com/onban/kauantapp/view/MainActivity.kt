package com.onban.kauantapp.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onban.kauantapp.common.adapter.MainListAdapter
import com.onban.kauantapp.common.adapter.StickyHeaderItemDecoration
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.databinding.ActivityMainBinding
import com.onban.kauantapp.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var adapter: MainListAdapter
    @Inject lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initViews()
    }

    override fun createBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun setBinding() {
        binding.viewmodel = viewmodel
    }

    override fun inject() {
        // Make Dagger instantiate @Inject fields in MainActivity
        (applicationContext as GlobalApp).appComponent.inject(this)
    }

    private fun initViews() {
        adapter = MainListAdapter()
        with(binding) {
            rcvMain.adapter = adapter
            rcvMain.addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
        }
        initData()
    }

    private fun initData() {
        viewmodel.fetchNextNews()
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