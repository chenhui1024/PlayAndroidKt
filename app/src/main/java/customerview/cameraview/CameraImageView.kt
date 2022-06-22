package customerview.cameraview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Scroller
import androidx.appcompat.widget.AppCompatImageView
import com.example.playandroid_kt.R

/**
 * @description
 * @author ch
 * @date 2022/2/8
 * @edit
 */
class CameraImageView : AppCompatImageView {

    companion object {
        private const val TAG = "CameraImageView"
    }


    private var progress: Int = 0
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.cat)
    private val camera = Camera()
    private val mMatrix = Matrix()

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        postInvalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        //1
//        canvas?.save()
//        camera.save()
//
//        paint.alpha = 100
//        canvas?.drawBitmap(bitmap, 0f, 0f, paint)
////        camera.rotateX(progress.toFloat())
////        camera.translate(progress.toFloat(), 0f, 0f)
//        camera.applyToCanvas(canvas)
//        camera.restore()
//        super.onDraw(canvas)
//        canvas?.restore()

        //2
//        canvas?.save()
//        camera.save()
//
//        camera.rotateX(progress.toFloat())
//        camera.getMatrix(matrix)
//        //调整中心点
//        val centerX = width / 2
//        val centerY = height / 2
//        matrix.preTranslate(-centerX.toFloat(), -centerY.toFloat())
//        matrix.postTranslate(centerX.toFloat(), centerY.toFloat())
//
//        canvas?.setMatrix(matrix)
//        camera.restore()
//        super.onDraw(canvas)
//        canvas?.restore()


        //3.
//        canvas?.save()
//        camera.save()
//        val centerX = width / 2 / 72
//        val centerY = height / 2/ 72
//        camera.setLocation(centerX.toFloat(), -centerY.toFloat(), camera.locationZ)

        camera.save()
        val matrxi = Matrix()
        camera.translate(100f, 0f, 0f)
        camera.getMatrix(matrix)
        Log.d(TAG, "matrix:$matrxi")
        camera.restore()

    }


}