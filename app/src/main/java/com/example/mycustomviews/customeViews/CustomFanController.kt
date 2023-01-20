package com.example.mycustomviews.customeViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.mycustomviews.R
import java.lang.Math.*

enum class SpeedLevel(val labels : Int){
    ONE(R.string.one),
    TWO(R.string.two),
    THREE(R.string.three),
    FOUR(R.string.four),
    FIVE(R.string.five),
    SIX(R.string.six),
    SEVEN(R.string.seven),
    EIGHT(R.string.eight);

    fun next()= when(this){
        ONE -> TWO
        TWO -> THREE
        THREE -> FOUR
        FOUR -> FIVE
        FIVE -> SIX
        SIX -> SEVEN
        SEVEN -> EIGHT
        EIGHT -> ONE
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class CustomFanController @JvmOverloads constructor(context : Context,
                                                       attributes: AttributeSet? = null,
                                                       defStyleAttr: Int = 0
                                                       ) : View(context, attributes, defStyleAttr) {
    private var radius = 0.0f                   // Radius of the circle.
    private var fanSpeed = SpeedLevel.ONE         // The active selection.
    // position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 30.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    init {
        isClickable = true
    }

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }


    override fun performClick(): Boolean {
        if(super.performClick()) {
            fanSpeed = fanSpeed.next()

            contentDescription = resources.getString(fanSpeed.labels)

            invalidate()

            return true
        }
        return true
    }


    private fun PointF.computeXYForSpeed(pos: SpeedLevel, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = if (fanSpeed == SpeedLevel.ONE) Color.GRAY else if(fanSpeed == SpeedLevel.EIGHT) Color.RED else Color.GREEN

        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas?.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)

        // Draw the labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in SpeedLevel.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.labels)
            canvas?.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}