package com.onban.kauantapp.view

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onban.kauantapp.common.adapter.HomeListAdapter
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.databinding.ActivityHomeBinding
import com.onban.network.data.CompanyEntity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private lateinit var homeListAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
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
            homeListAdapter = HomeListAdapter()
            rcvHome.adapter = homeListAdapter
            homeListAdapter.submitList(listOf(
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#000000", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#000000", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
                CompanyEntity("이스트소프트", "EST", "#184cf5", "#FFFFFF"),
            ))

        }
    }
}