package com.onban.kauantapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.onban.kauantapp.R
import com.onban.kauantapp.common.adapter.SimilarNewsListAdapter
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.data.StockItem
import com.onban.kauantapp.data.ViewModelEvent
import com.onban.kauantapp.databinding.ActivityAnalysisBinding
import com.onban.kauantapp.viewmodel.AnalysisViewModel
import com.onban.network.data.CompanyData
import com.onban.network.data.NewsData
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class AnalysisActivity : BaseActivity<ActivityAnalysisBinding>() {

    private lateinit var adapter: SimilarNewsListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AnalysisViewModel by viewModels { viewModelFactory}

    private val submitListCallback = Runnable {
        viewModel.setFetchEnable()
    }

    val dummyStockList = MutableLiveData(
        listOf<StockItem>(
            StockItem(1.2f, "2020.09.09"),
            StockItem(0.2f, "2020.09.10"),
            StockItem(18.5f, "2020.09.11"),
            StockItem(-22.5f, "2020.09.12"),
            StockItem(5.5f, "2020.09.13"),
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        observeEvent()
        initViewModel()
        initAdapter()
        initViews()
    }

    override fun createBinding(): ActivityAnalysisBinding {
        return ActivityAnalysisBinding.inflate(layoutInflater)
    }

    override fun inject() {
        (applicationContext as GlobalApp).appComponent.inject(this)
    }

    private fun setBinding() {
        with(binding) {
            activity = this@AnalysisActivity
            viewModel = this@AnalysisActivity.viewModel
            submitListCallback = this@AnalysisActivity.submitListCallback
            tvAnalysisGraphTitle.text = getString(R.string.analysis_graph_title, "2020.09.11")
        }
    }

    private fun initViews() {
        setViewPager()
        setSimilarityProgress(55f)
        similarityProgressAnimationStart()
    }

    private fun initViewModel() {
        viewModel.setMainNews(intent.getSerializableExtra("newsData") as NewsData)
        viewModel.setCompany(intent.getSerializableExtra("company") as CompanyData)
        viewModel.fetchNextSimilarityNews()
    }

    private fun setViewPager() {
        with(binding) {
            vp2Analysis.adapter = adapter

            vp2Analysis.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            vp2Analysis.offscreenPageLimit = 2
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
            val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
            vp2Analysis.setPageTransformer { page, position ->
                val myOffset = position * -(2 * pageOffset + pageMargin)
                if (position < -1) {
                    page.translationX = -myOffset
                } else if (position <= 1) {
                    val scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f))
                    page.translationX = myOffset
                    page.scaleY = scaleFactor
                    page.alpha = scaleFactor
                } else {
                    page.alpha = 0f
                    page.translationX = myOffset
                }
            }
            vp2Analysis.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (adapter.itemCount - 1 == position) {
                        this@AnalysisActivity.viewModel.fetchNextSimilarityNews()
                    }
                    this@AnalysisActivity.viewModel.setSelectedSimilarNews(position)
                }
            })
        }
    }

    private fun initAdapter() {
        adapter = SimilarNewsListAdapter()
    }

    private fun setSimilarityProgress(percent: Float) {
        binding.cpvNews.resetCurrentPercentage()
        binding.cpvNews.setPercentage(percent)
    }

    private fun similarityProgressAnimationStart() {
        binding.cpvNews.animateProgress()
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