package com.onban.kauantapp.view

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.onban.kauantapp.R
import com.onban.kauantapp.common.adapter.AnalysisListAdapter
import com.onban.kauantapp.common.app.GlobalApp
import com.onban.kauantapp.common.view.BaseActivity
import com.onban.kauantapp.databinding.ActivityAnalysisBinding
import com.onban.network.data.CompanyEntity
import com.onban.network.data.NewsData

class AnalysisActivity : BaseActivity<ActivityAnalysisBinding>() {

    private lateinit var adapter: AnalysisListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initViews()
        adapter.submitList(getDummyData())
    }

    fun initViews() {
        with(binding) {
            newsData = intent.getSerializableExtra("newsData") as NewsData
            company = intent.getSerializableExtra("company") as CompanyEntity
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
        }
    }

    fun initAdapter() {
        adapter = AnalysisListAdapter()
    }

    override fun createBinding(): ActivityAnalysisBinding {
        return ActivityAnalysisBinding.inflate(layoutInflater)
    }

    override fun inject() {
        (applicationContext as GlobalApp).appComponent.inject(this)
    }
}
data class DummyData(
    val date: String,
    val title: String,
    val desc: String,
)
fun getDummyData(): List<DummyData> {
    return listOf(
        DummyData("2020.09.11", "이스트소프트 상빙ㄹ....", "내욘ㅇㄴㅇ런ㅇ래ㅑ너야ㅐ러ㅐㄴ얼뎌안아랴ㅓㅇ라ㅣㄴ이"),
        DummyData("2020.09.11", "이스트소프트 상빙ㄹ....", "내욘ㅇㄴㅇ런ㅇ래ㅑ너야ㅐ러ㅐㄴ얼뎌안아랴ㅓㅇ라ㅣㄴ이"),
        DummyData("2020.09.11", "이스트소프트 상빙ㄹ....", "내욘ㅇㄴㅇ런ㅇ래ㅑ너야ㅐ러ㅐㄴ얼뎌안아랴ㅓㅇ라ㅣㄴ이"),
        DummyData("2020.09.11", "이스트소프트 상빙ㄹ....", "내욘ㅇㄴㅇ런ㅇ래ㅑ너야ㅐ러ㅐㄴ얼뎌안아랴ㅓㅇ라ㅣㄴ이"),
    )
}