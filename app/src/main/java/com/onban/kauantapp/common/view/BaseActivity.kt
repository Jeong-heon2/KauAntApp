package com.onban.kauantapp.common.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB: ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VDB
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        inject()
        super.onCreate(savedInstanceState, persistentState)
        initBinding()
    }

    private fun initBinding() {
        binding = createBinding()
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    protected abstract fun createBinding(): VDB

    protected abstract fun inject()
}