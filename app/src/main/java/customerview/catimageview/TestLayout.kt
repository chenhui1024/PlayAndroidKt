package customerview.catimageview

import android.content.Context
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * @description
 * @author ch
 * @date 2022/2/13
 * @edit
 */
class TestLayout : LinearLayout {

    constructor(context: Context) : super(context) {}

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        if (ev?.actionMasked == MotionEvent.ACTION_MOVE) {
//            if (自己需要这类点击事件) {
//                parent.requestDisallowInterceptTouchEvent(true)
//            } else {
//                parent.requestDisallowInterceptTouchEvent(false)
//            }
//        }
        return super.onInterceptTouchEvent(ev)
    }

}