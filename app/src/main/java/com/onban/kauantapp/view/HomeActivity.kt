package com.onban.kauantapp.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onban.kauantapp.common.adapter.HomeListAdapter
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.databinding.ActivityHomeBinding
import com.onban.kauantapp.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private lateinit var homeListAdapter: HomeListAdapter

    @Inject lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initViews()
        initData()
    }

    private fun setBinding() {
        with(binding) {
            viewModel = homeViewModel
        }
    }

    override fun createBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun inject() {
        (applicationContext as GlobalApp).appComponent.inject(this)
    }

    private fun initViews() {
        with(binding) {
            rcvHome.setHasFixedSize(true)
            rcvHome.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            homeListAdapter = HomeListAdapter {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                intent.putExtra("company", it)
                startActivity(intent)
            }
            rcvHome.adapter = homeListAdapter
        }
    }

    private fun initData() {
        homeViewModel.fetchCompanyList()
    }
}