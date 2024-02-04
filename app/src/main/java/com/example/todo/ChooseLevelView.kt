package com.example.todo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.todo.R
import com.example.todo.data.model.Importance

class ChooseLevelView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var drawable: Drawable
    private var indentation: Int = 0
    private var item1: Rect = Rect()
    private var item2: Rect = Rect()
    private var item3: Rect = Rect()
    private var selectedRect: Int = 1

    fun getImportance(): Importance {
        return when(selectedRect){
            1 -> Importance.LOW
            2 -> Importance.MEDIUM
            3 -> Importance.HIGH
            else -> {
                Importance.LOW}
        }
    }

    fun setImportance(importance: Importance){
        selectedRect = when(importance){
            Importance.LOW -> 1
            Importance.MEDIUM -> 2
            Importance.HIGH -> 3
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        item1.set(0, 0, height, height)
        item2.set(height + indentation, 0, height * 2 + indentation, height)
        item3.set((height + indentation) * 2, 0, width, height)


        drawable = ContextCompat.getDrawable(context, R.drawable.one)!!
        if(selectedRect == 1)
            drawable.setTint(ContextCompat.getColor(context, R.color.md_theme_secondary))
        else
            drawable.setTint(ContextCompat.getColor(context,
                R.color.md_theme_surfaceContainerHighest
            ))
        drawable.bounds = item1
        drawable.draw(canvas)


        drawable = ContextCompat.getDrawable(context, R.drawable.two)!!
        if(selectedRect == 2)
            drawable.setTint(ContextCompat.getColor(context, R.color.md_theme_secondary))
        else
            drawable.setTint(ContextCompat.getColor(context,
                R.color.md_theme_surfaceContainerHighest
            ))
        drawable.bounds = item2
        drawable.draw(canvas)


        drawable = ContextCompat.getDrawable(context, R.drawable.three)!!
        if(selectedRect == 3)
            drawable.setTint(ContextCompat.getColor(context, R.color.md_theme_secondary))
        else
            drawable.setTint(ContextCompat.getColor(context,
                R.color.md_theme_surfaceContainerHighest
            ))
        drawable.bounds = item3
        drawable.draw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = 340
        val desiredHeight = 100

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var flag = false

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize

            MeasureSpec.AT_MOST -> {
                val itemsWidth = heightSize * 3
                val dividerWidth = heightSize / 5
                if (itemsWidth + dividerWidth * 2 < desiredWidth) {
                    indentation = dividerWidth
                    itemsWidth + dividerWidth
                } else {
                    flag = true
                    indentation = 20
                    desiredWidth
                }
            }

            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize

            MeasureSpec.AT_MOST -> {
                if (flag)
                    desiredHeight
                else if (heightSize < desiredHeight)
                    heightSize
                else
                    desiredHeight
            }

            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    private var touchedRect: Rect? = null //false - мимо, true - в цифру -> можно обрабатывать для ACTION_UP
    private var tempRect = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (item1.contains(x, y)) {
                    touchedRect = item1
                    tempRect = 1
                } else if (item2.contains(x, y)) {
                    touchedRect = item2
                    tempRect = 2
                } else if (item3.contains(x, y)) {
                    touchedRect = item3
                    tempRect = 3
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                if(touchedRect != null) {
                    if (touchedRect!!.contains(x, y)) {
                        selectedRect = tempRect
                        invalidate()
                    }
                    else{
                        touchedRect = null
                        tempRect = 0
                    }
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}