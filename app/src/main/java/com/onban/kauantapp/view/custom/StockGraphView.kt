package com.onban.kauantapp.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.onban.kauantapp.R
import kotlin.math.abs

class StockGraphView : View {
    constructor(context: Context?) : super(context) {
        setWillNotDraw(false)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var stockItemList: List<StockItem> = listOf()
    private val graphBarColors = listOf(Color.RED, context.getColor(R.color.orange), Color.YELLOW, Color.GREEN, Color.BLUE)

    private val rect = Rect()
    private val zeroText = "0%"

    private val dotInterval = floatArrayOf(10f, 20f)
    private val phase = 0f
    private val centerLineStroke = 10f
    private val centerLinePaint = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = centerLineStroke
        strokeCap = Paint.Cap.ROUND
        pathEffect = DashPathEffect(dotInterval, phase)
    }

    private val zeroTextPaint = Paint().apply {
        color = Color.GRAY
        textSize = 40f
        getTextBounds(zeroText, 0, zeroText.length, rect)
    }

    private val barStroke = 40f
    private val graphBarPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = barStroke
        strokeCap = Paint.Cap.ROUND
    }

    fun updateGraph(newItems: List<StockItem>) {
        stockItemList = newItems
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawBars(it)
            drawGraphZeroText(it)
            drawCenterLine(it)
        }
    }

    private fun drawBars(c: Canvas) {
        var startX = measuredWidth * 0.15f
        val barMaxHeight = measuredHeight * 0.4f
        val maxValue = getMaxValue()
        val centerY = measuredHeight / 2f

        stockItemList.forEachIndexed { index, stockItem ->

            val barHeight = (barMaxHeight / (maxValue)) * abs(stockItem.value)
            val stopY = if (stockItem.value < 0) centerY + barHeight + centerLineStroke else centerY - barHeight - centerLineStroke
            val startY = if (stockItem.value < 0) centerY + centerLineStroke else centerY - centerLineStroke
            drawChartBar(c, startX, startY, stopY, graphBarColors[index])

            startX += measuredWidth * 0.1f
        }
    }

    private fun getMaxValue(): Float = stockItemList.maxOf { abs(it.value) }

    private fun drawChartBar(c: Canvas, startX: Float, startY: Float, stopY: Float, barColor: Int) {
        val barPaint =
            graphBarPaint.apply {
                color = barColor
            }
        c.drawLine(startX, startY,
            startX, stopY, barPaint)
    }

    private fun drawCenterLine(c: Canvas) {
        val path = Path().apply {
            moveTo(measuredWidth * 0.1f, measuredHeight / 2f)
            lineTo(measuredWidth * 0.6f, measuredHeight / 2f)
        }
        c.drawPath(path, centerLinePaint)
    }

    private fun drawGraphZeroText(c: Canvas) {
        c.drawText(zeroText, measuredWidth * 0.03f, measuredHeight / 2f + rect.height() / 2, zeroTextPaint)
    }

    data class StockItem(
        val value: Float,
        val date: String,
    )
}