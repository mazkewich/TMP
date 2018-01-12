package photomap.com.richard.photomap.presentation.category

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CheckBox

/**
 * @author richard on 1/12/18.
 */

class CategoryCheckBox @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0): CheckBox(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()

    var color = Color.GREEN
     get set

    override fun onDraw(canvas: Canvas?) {
        //super.onDraw(canvas)
        paint.color = color
        paint.strokeWidth = 10f
        if (isChecked) {
            paint.style = Paint.Style.FILL
            canvas?.drawCircle((width / 2).toFloat(), (width / 2).toFloat(), (width / 2).toFloat(), paint)
        } else {
            paint.style = Paint.Style.STROKE
            canvas?.drawCircle((width / 2).toFloat(), (width / 2).toFloat(), (width / 2 - 5).toFloat(), paint)
        }

    }

}