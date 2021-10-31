package com.onban.kauantapp.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.onban.kauantapp.R
import com.onban.kauantapp.common.adapter.MainListAdapter
import com.onban.kauantapp.common.adapter.StickyHeaderItemDecoration
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.data.ViewModelEvent
import com.onban.kauantapp.databinding.ActivityMainBinding
import com.onban.kauantapp.viewmodel.MainViewModel
import com.onban.network.data.CompanyEntity
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var adapter: MainListAdapter
    @Inject lateinit var viewModel: MainViewModel

    private val submitListCallback = Runnable {
        viewModel.setFetchEnable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initViews()
        initData()
        observeEvent()
    }

    override fun createBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun setBinding() {
        with(binding) {
            viewmodel = viewModel
            submitListCallback = this@MainActivity.submitListCallback
        }
    }

    override fun inject() {
        // Make Dagger instantiate @Inject fields in MainActivity
        (applicationContext as GlobalApp).appComponent.inject(this)
    }

    private fun initViews() {
        adapter = MainListAdapter()
        with(binding) {
            rcvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    rcvMain.adapter?.let { adapter ->
                        val lastVisibleItemPosition = (rcvMain.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        val lastItemPosition = adapter.itemCount - 1
                        if (lastVisibleItemPosition == lastItemPosition) {
                            viewModel.fetchNextNews()
                        }
                    }
                }
            })
            rcvMain.adapter = adapter
            rcvMain.addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
        }
    }

    private fun initData() {
        val companyEntity = intent.extras?.get("company") as? CompanyEntity
        companyEntity?.let {
            binding.tvMainTitle.text = getString(R.string.main_title, it.name)
            viewModel.setCompany(it.name)
            viewModel.fetchNextNews()
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

    private fun observeEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect {
                when (it) {
                    is ViewModelEvent.NetworkError -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}