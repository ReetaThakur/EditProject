package com.masai.workmanagersample.customviews

import android.content.Context
import android.graphics.Canvas
import android.view.View

class DrawingView(context: Context?) : View(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}