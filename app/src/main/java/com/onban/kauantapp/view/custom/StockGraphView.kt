package com.onban.kauantapp.view.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class StockGraphView : View {
    constructor(context: Context?) : super(context) {
        setWillNotDraw(false)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var stockItemList: List<StockItem> = listOf()

    fun updateGraph(newItems: List<StockItem>) {
        stockItemList = newItems
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {

        }
    }

    data class StockItem(
        val value: Float,
        val date: String,
    )
}