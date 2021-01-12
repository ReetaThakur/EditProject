package com.xyz.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class DoodleView @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet?, defStyleAttr: Int =0
) : View(context, attrs, defStyleAttr,0) {

    private var paint: Paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f,0f,500f,600f,paint)
    }
}
