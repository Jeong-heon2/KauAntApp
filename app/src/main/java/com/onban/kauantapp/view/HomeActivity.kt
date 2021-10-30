package com.onban.kauantapp.view

import android.os.Bundle
import android.os.PersistableBundle
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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
        }
    }
}