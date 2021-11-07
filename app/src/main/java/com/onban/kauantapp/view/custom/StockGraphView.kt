package com.onban.kauantapp.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class StockGraphView : View {
    constructor(context: Context?) : super(context) {
        setWillNotDraw(false)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var stockItemList: List<StockItem> = listOf()

    private val rect = Rect()
    private val zeroText = "0%"

    private val centerLinePaint = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 5f
        strokeCap = Paint.Cap.ROUND
    }
    private val zeroTextPaint = Paint().apply {
        color = Color.DKGRAY
        textSize = 30f
        getTextBounds(zeroText, 0, zeroText.length, rect)
    }

    fun updateGraph(newItems: List<StockItem>) {
        stockItemList = newItems
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawGraphZeroText(it)
            drawCenterLine(it)
        }
    }

    private fun drawCenterLine(c: Canvas) {
        c.drawLine(measuredWidth * 0.1f, measuredHeight / 2f,
            measuredWidth * 0.7f, measuredHeight / 2f, centerLinePaint)
    }

    private fun drawGraphZeroText(c: Canvas) {
        c.drawText(zeroText, measuredWidth * 0.03f, measuredHeight / 2f + rect.height() / 2, zeroTextPaint)
    }

    data class StockItem(
        val value: Float,
        val date: String,
    )
}