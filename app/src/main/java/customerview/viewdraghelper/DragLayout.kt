package customerview.viewdraghelper

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper

/**
 * @description
 * @author ch
 * @date 2022/3/1
 * @edit
 */
class DragLayout : LinearLayout {

    private val mDragger: ViewDragHelper

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    init {
        mDragger = ViewDragHelper.create(this, 1.0f, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

        })
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return ev?.let { mDragger.shouldInterceptTouchEvent(it) } ?: false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { mDragger.processTouchEvent(it) }
        return true
    }


}