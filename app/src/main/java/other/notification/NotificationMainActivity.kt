package other.notification

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.playandroid_kt.R
import other.OtherMainActivity

/**
 * @description
 * @author ch
 * @date 2021/11/22
 * @edit
 */
class NotificationMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        findViewById<Button>(R.id.btn_normal).setOnClickListener(::notifyNormal)
        findViewById<Button>(R.id.btn_fold).setOnClickListener(::notifyFold)
        findViewById<Button>(R.id.btn_suspend).setOnClickListener(::notifySuspend)
    }

    fun notifyNormal(view: View) {
        val notificationBuilder = Notification.Builder(this)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        //悬挂式Notification
//        val hangIntent = Intent()
//        hangIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        hangIntent.setClass(this, OtherMainActivity::class.java)
//        val hangPendingINtent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = notificationBuilder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.abc_vector_test)
//            .setFullScreenIntent(hangPendingINtent, true)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
            .setAutoCancel(true)
            .setContentTitle("普通通知")
            .build()

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(100, notification)


        //设置自定义视图
//        val remoteViews = RemoteViews(packageName, R.layout.activity_demo)
//        notification.contentView = remoteViews
    }

    fun notifyFold(view: View) {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    fun notifySuspend(view: View) {

    }


}