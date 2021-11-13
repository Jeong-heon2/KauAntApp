package com.onban.kauantapp.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.onban.kauantapp.R
import com.onban.kauantapp.data.StockItem
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

    private val barStroke = 30f
    private val graphBarPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = barStroke
        strokeCap = Paint.Cap.ROUND
    }

    private val dotRadius = 10f
    private val dotPaint = Paint().apply{
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val metaTextPaint = Paint().apply {
        color = Color.WHITE
        textSize = 40f
    }

    fun updateGraph(newItems: List<StockItem>) {
        stockItemList = newItems
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            drawTitle(it)
            drawBars(it)
            drawGraphZeroText(it)
            drawCenterLine(it)
            drawMetaDataList(it)
        }
    }

    private fun drawBars(c: Canvas) {
        var startX = getBarsStartX()
        val barMaxHeight = measuredHeight * 0.33f
        val maxValue = getMaxValue()
        val centerY = measuredHeight / 2f

        stockItemList.forEachIndexed { index, stockItem ->

            val barHeight = (barMaxHeight / (maxValue)) * abs(stockItem.value)
            val stopY = if (stockItem.value < 0) centerY + barHeight + centerLineStroke else centerY - barHeight - centerLineStroke
            val startY = if (stockItem.value < 0) centerY + centerLineStroke else centerY - centerLineStroke
            drawChartBar(c, startX, startY, stopY, graphBarColors[index])

            startX += getBarInterval()
        }
    }

    private fun getBarsStartX(): Float = measuredWidth * 0.2f

    private fun getBarInterval(): Float = measuredWidth * 0.1f

    private fun getMaxValue(): Float = stockItemList.maxOf { abs(it.value) }

    private fun drawChartBar(c: Canvas, startX: Float, startY: Float, stopY: Float, barColor: Int) {
        val barPaint =
            graphBarPaint.apply {
                color = barColor
            }
        c.drawLine(startX, startY,
            startX, stopY, barPaint)
    }

    private fun drawMetaDataList(c: Canvas) {
        val startX = measuredWidth * 0.75f
        var startY = measuredHeight * 0.2f
        stockItemList.forEachIndexed { index, stockItem ->
            drawMetaData(c, startX, startY, stockItem.value, graphBarColors[index])
            startY += measuredHeight * 0.15f
        }
    }

    private fun drawTitle(c: Canvas) {
        if (stockItemList.isNotEmpty()) {
            val titleRect = Rect()
            val date = stockItemList[stockItemList.size / 2].date
            metaTextPaint.getTextBounds(date, 0, date.length, titleRect)
            val startX = getBarsStartX() + getBarInterval() * 2 - titleRect.width() / 2
            val startY = context.resources.displayMetrics.density * 20 // 10dp
            c.drawText(
                date,
                startX,
                startY,
                metaTextPaint
            )
        }
    }

    private fun drawMetaData(c: Canvas, startX: Float, startY: Float, value: Float, dotColor: Int) {
        val dotPaint =
            dotPaint.apply {
                color = dotColor
            }
        c.drawCircle(startX, startY, dotRadius, dotPaint)

        val metaTextRect = Rect()
        val text = if (value >= 0 ) "+$value%" else "$value%"
        metaTextPaint.getTextBounds(text, 0, text.length, metaTextRect)
        c.drawText(
            text,
            startX + dotRadius * 3,
            startY + metaTextRect.height() * 0.5f,
            metaTextPaint
        )
    }

    private fun drawCenterLine(c: Canvas) {
        val path = Path().apply {
            moveTo(measuredWidth * 0.15f, measuredHeight / 2f)
            lineTo(measuredWidth * 0.65f, measuredHeight / 2f)
        }
        c.drawPath(path, centerLinePaint)
    }

    private fun drawGraphZeroText(c: Canvas) {
        c.drawText(zeroText, measuredWidth * 0.05f, measuredHeight / 2f + rect.height() / 2, zeroTextPaint)
    }
}