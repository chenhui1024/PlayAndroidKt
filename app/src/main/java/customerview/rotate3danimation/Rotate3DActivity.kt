package customerview.rotate3danimation

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityRotate3dAnimationBinding

/**
 * @description
 * @author ch
 * @date 2022/2/8
 * @edit
 */
class Rotate3DActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Rotate3DActivity"
    }

    private lateinit var dataBinding: ActivityRotate3dAnimationBinding
    private lateinit var openAnimation: Rotate3DAnimation
    private lateinit var closeAnimation: Rotate3DAnimation

    private var open: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_rotate_3d_animation)

        dataBinding.btnOpen.setOnClickListener {

            dataBinding.imvCat.scrollBy(5, 5)

//            openAnimation.takeIf {
//                it.hasStarted() && !it.hasEnded()
//            }?.let {
//                return@setOnClickListener
//            }
//
//            closeAnimation.takeIf {
//                it.hasStarted() && !it.hasEnded()
//            }?.let {
//                return@setOnClickListener
//            }
//
//            if (open) {
//                dataBinding.content.startAnimation(closeAnimation)
//            } else {
//                dataBinding.content.startAnimation(openAnimation)
//            }
//
//            open = !open
        }

        initOpenAnimation()
        initCloseAnimation()

        val matrix = Matrix()

        Log.d(TAG, "matrix:${matrix.toShortString()}")
        matrix.preTranslate(100f, 100f)
        Log.d(TAG, "matrix:${matrix.toShortString()}")
        matrix.preTranslate(-100f, -100f)
        Log.d(TAG, "matrix:${matrix.toShortString()}")
    }

    private fun initOpenAnimation() {
        openAnimation = Rotate3DAnimation(0f, 90f, true).apply {
            duration = 500
            fillAfter = true
            interpolator = AccelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    dataBinding.imvCat.visibility = View.GONE
                    dataBinding.imvDog.visibility = View.VISIBLE
                    dataBinding.content.startAnimation(
                        Rotate3DAnimation(90f, 180f, false).apply {
                            duration = 500
                            fillAfter = true
                            interpolator = DecelerateInterpolator()
                        }
                    )
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
        }
    }

    private fun initCloseAnimation() {
        closeAnimation = Rotate3DAnimation(180f, 90f, true).apply {
            duration = 500
            fillAfter = true
            interpolator = AccelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    dataBinding.imvCat.visibility = View.VISIBLE
                    dataBinding.imvDog.visibility = View.GONE
                    dataBinding.content.startAnimation(
                        Rotate3DAnimation(90f, 0f, false).apply {
                            duration = 500
                            fillAfter = true
                            interpolator = DecelerateInterpolator()
                        }
                    )
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
        }
    }

}