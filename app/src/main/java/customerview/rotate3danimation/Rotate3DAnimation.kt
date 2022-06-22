package customerview.rotate3danimation

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * @description
 * @author ch
 * @date 2022/2/8
 * @edit
 */
class Rotate3DAnimation(private val fromDegrees: Float, private val endDegrees: Float, private val reverse: Boolean) : Animation() {

    private var centerX: Int = 0
    private var centerY: Int = 0
    private lateinit var camera: Camera
    private val depthZ = 400

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)

        centerX = width / 2
        centerY = height / 2
        camera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {

        val degrees = fromDegrees + (endDegrees - fromDegrees) * interpolatedTime

        camera.save()

//        if (interpolatedTime < 0.5) {
//            camera.translate(0f, 0f, 600 * interpolatedTime)
//        } else {
//            camera.translate(0f, 0f, (1 - interpolatedTime) * 600)
//        }
        camera.translate(0f, 0f, if (reverse) depthZ * interpolatedTime else (1 - interpolatedTime) * depthZ)

        val m = t!!.matrix
        camera.rotateY(degrees)
        camera.getMatrix(m)

        camera.restore()

        m.preTranslate(-centerX.toFloat(), -centerY.toFloat())
        m.postTranslate(centerX.toFloat(), centerY.toFloat())
        super.applyTransformation(interpolatedTime, t)
    }

}