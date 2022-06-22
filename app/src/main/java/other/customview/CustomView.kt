package other.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Scroller

/**
 * @description
 * @author ch
 * @date 2021/11/23
 * @edit
 */
class CustomView : View {

    private var mScroller: Scroller = Scroller(context)

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            (parent as View).scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

    fun smoothScrollTo(destX: Int, destY: Int) {
        val scrollX = scrollX
        val delta = destX - scrollX
        mScroller.startScroll(scrollX, 0, delta, 0, 2000)
        invalidate()
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
//        val typeArray = context?.obtainStyledAttributes(attrs, IntArray(1) { android.R.attr.layout_width })
        val typeArray = context?.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.layout_width))
        val width = typeArray?.getDimension(0, 0f)
        typeArray?.recycle()
    }

}