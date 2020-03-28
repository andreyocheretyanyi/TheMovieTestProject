package ua.codeasylum.themovietestproject.view.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

class CustomActionBar : Toolbar {

    private val listener: WaveView.InvalidateListener = object : WaveView.InvalidateListener {
        override fun onInvalidate() {
            invalidate()
        }
    }

    constructor(context: Context) : super(context) {
        waveView = WaveView(context)
    }

    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs) {
        waveView = WaveView(context, attrs)
    }

    constructor (
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        waveView = WaveView(context, attrs, defStyleAttr)
    }


    private var waveView: WaveView

    init {
        setWillNotDraw(false)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        waveView.invalidateListener = listener
        waveView.initStuff(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        waveView.draw(canvas)

    }
}