package ua.codeasylum.themovietestproject.base.transformation

import android.graphics.*
import com.squareup.picasso.Transformation

class RoundTransformation(
    val topRight: Float,
    val topLeft: Int,
    val bottomRight: Float,
    val bottomLeft: Int
) : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height
        val tempBitmap = Bitmap.createBitmap(source)
        if (tempBitmap != source)
            source.recycle()
        val bitmap = Bitmap.createBitmap(width, height, tempBitmap.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader =
            BitmapShader(tempBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true
        canvas.drawRoundRect(
            0F,
            0F,
            width.toFloat(),
            height.toFloat(),
            topRight,
            bottomRight,
            paint
        )
        tempBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "round"
    }
}