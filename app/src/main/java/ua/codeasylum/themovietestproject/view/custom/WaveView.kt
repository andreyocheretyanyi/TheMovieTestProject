package ua.codeasylum.themovietestproject.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.EndlessFloatValueAnimator
import kotlin.math.PI
import kotlin.math.sin


class WaveView : View {


    interface InvalidateListener {
        fun onInvalidate()
    }

    var invalidateListener: InvalidateListener? = null

    constructor(context: Context) : super(context)
    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor (
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    private lateinit var heightAnimator: ValueAnimator
    private val path = Path()
    var diff = 0f
    private val gradientMatrix = Matrix()
    private var verticalMid = 0.0f
    private var h = 0
    private var w = 0
    private var matrixXOffset = 0f


    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 5f
    }

    private var colors = intArrayOf(
        ContextCompat.getColor(context, R.color.endG),
        ContextCompat.getColor(context, R.color.startG),
        ContextCompat.getColor(context, R.color.middleG)

    )
    private val amplitude = 20f
    private lateinit var gradient: LinearGradient

    private val func: (Float, Int, Float, Float, Float, Float, Boolean) -> PointF =
        { ampl, x, width, offset, funcCount, ferq, reverse ->
            PointF(
                x + offset,
                ampl * sin(ferq * (x / width * funcCount) + if (reverse) -diff else diff)
            )
        }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        gradientMatrix.setTranslate(matrixXOffset, -matrixXOffset)
        gradient.setLocalMatrix(gradientMatrix)
        paint.shader = gradient


        for (i in 0..0) {
            path.reset()
            when (i) {
                0 -> drawSinus(canvas)
                1 -> drawCubicTo(canvas)
            }

        }

        if (!heightAnimator.isRunning) {
            heightAnimator.start()
        }

    }

    private fun drawCubicTo(canvas: Canvas?) {
        val initX = w * 0.5f
        val initY =
            func(
                amplitude,
                initX.toInt(),
                w.toFloat(),
                0F,
                2f,
                PI.toFloat(),
                false
            ).y + verticalMid
        val secondPoint =
            func(amplitude, (w * 0.55f).toInt(), w.toFloat(), 0F, 2f, PI.toFloat(), true)
        val thirdPoint = func(amplitude, w, w.toFloat(), 0F, 2f, PI.toFloat(), true)
        path.moveTo(initX, initY)
        path.cubicTo(
            w.toFloat(),
            initY,
            secondPoint.x,
            secondPoint.y,
            thirdPoint.x,
            thirdPoint.y
        )
        path.lineTo(w.toFloat(), h.toFloat());
        path.lineTo(0F, h.toFloat());
        path.close()
        canvas!!.drawPath(path, paint)
    }

    private fun drawSinus(canvas: Canvas?) {
        val matrix = Matrix()
        matrix.setRotate(180f, w / 2f, h / 2f)

        for (x in 0..w step 5) {
            val point = func(amplitude, x, w.toFloat(), 0f, 2f, PI.toFloat(), false)
            if (x == 0) {
                path.moveTo(point.x, point.y + verticalMid)
            } else {

                path.lineTo(point.x, point.y + verticalMid)
            }

        }
        path.lineTo(w.toFloat(), h.toFloat());
        path.lineTo(0F, h.toFloat());
        path.transform(matrix)
        path.close()
        canvas!!.drawPath(path, paint)

    }

    fun initStuff(w: Int, h: Int) {
        verticalMid = amplitude
        this.w = w
        this.h = h
        gradient = LinearGradient(
            0f,
            200f,
            this.w.toFloat(),
            200f,
            colors,
            null,
            Shader.TileMode.MIRROR
        )


        heightAnimator = EndlessFloatValueAnimator().apply {
            animDuration = 15000
            animInterpolator = LinearInterpolator()
            setValues(0f, 1f)
            cycleEndListener = object : EndlessFloatValueAnimator.CycleEndListener {
                override fun onCycleEnd() {
                    diff = 0f
                    invalidateListener?.onInvalidate() ?: invalidate()
                }
            }

            valueUpdateListener = object : EndlessFloatValueAnimator.ValueUpdateListener {
                override fun onValueUpdated(value: Float) {
                    diff = value * 50
                    matrixXOffset = value * w * 2
                    invalidateListener?.onInvalidate() ?: invalidate()
                }
            }

        }


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initStuff(width, height)

    }


}