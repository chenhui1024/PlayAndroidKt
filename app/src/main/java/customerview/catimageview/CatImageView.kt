package customerview.catimageview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.playandroid_kt.R

/**
 * @description
 * @author ch
 * @date 2022/2/12
 * @edit
 */
class CatImageView : View {

    private val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.cat);

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        when {
            widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST -> setMeasuredDimension(bitmap.width, bitmap.height)
            widthSpecMode == MeasureSpec.AT_MOST -> setMeasuredDimension(bitmap.width, heightSpecSize)
            heightSpecMode == MeasureSpec.AT_MOST -> setMeasuredDimension(widthSpecSize, bitmap.height)
            else -> setMeasuredDimension(widthSpecSize, heightSpecSize)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 0f, 0f, null)
    }

}