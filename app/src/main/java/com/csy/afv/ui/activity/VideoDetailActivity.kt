package com.csy.afv.ui.activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_video_detail.*
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import com.csy.afv.R
import com.csy.afv.mvp.model.bean.VideoBean
import com.csy.afv.utils.LogUtil.logE
import com.csy.afv.utils.ImageLoadUtils
import com.csy.afv.utils.LogUtil.logI
import com.csy.afv.utils.ObjectSaveUtils
import com.csy.afv.utils.SharedPreUtils
import com.csy.afv.utils.showToast
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import zlc.season.rxdownload2.RxDownload
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException

/**
 * @Author:CSY
 * @Date:
 * @Description: 视频播放界面，所有界面的点击视频事件都跳转到这个activity里面
 */
class VideoDetailActivity : AppCompatActivity() {
    companion object {
        var MSG_IMAGE_LOADED = 101
    }

    var mContext: Context = this
    lateinit var imageView: ImageView
    lateinit var bean: VideoBean
    var isPlay: Boolean = false
    var isPause: Boolean = false
    lateinit var orientationUtils: OrientationUtils

    //往主线程里面发送请求，设置播放器封面
    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                MSG_IMAGE_LOADED -> {
                    logE("video: setImage")
                    gsy_player.thumbImageView = imageView
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        bean = intent.getParcelableExtra<VideoBean>("data")
        initView()
        prepareVideo()
    }

    private fun initView() {
        var bgUrl = bean.blurred
        //设置播放器封面
        bgUrl?.let { ImageLoadUtils.displayHigh(this, iv_bottom_bg, bgUrl) }
        //设置组件内容及字体
        tv_video_desc.text = bean.description
        tv_video_desc.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_video_title.text = bean.title
        tv_video_title.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        var category = bean.category
        var duration = bean.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        tv_video_time.text = "$category / $realMinute'$realSecond''"
        tv_video_favor.text = bean.collect.toString()
        tv_video_share.text = bean.share.toString()
        tv_video_reply.text = bean.share.toString()

        //视频点击下载事件
        tv_video_download.setOnClickListener {
            var url = bean.playUrl?.let { it1 -> SharedPreUtils.getInstance(this, "downloads").getString(it1) }
            if (url.equals("")) {
                var count = SharedPreUtils.getInstance(this, "downloads").getInt("count")
                if (count != -1) {
                    count = count.inc()
                } else {
                    count = 1
                }
                SharedPreUtils.getInstance(this, "downloads").put("count", count)
                ObjectSaveUtils.saveObject(this, "download$count", bean)
                addMission(bean.playUrl,count)
            } else {
                showToast("该视频已经缓存过了")
            }
        }
    }

    /**
     * 使用RxDownload中的serviceDownload下载
     */
    private fun addMission(playUrl: String?, count: Int) {
        //RxDownload中的serviceDownload里面的三个参数：下载url、保存的名字、保存的地址（为空时默认设为手机Download目录）
        RxDownload.getInstance(this).serviceDownload(playUrl,"download$count").subscribe({
            showToast("开始下载")
            //为下载的视频设置键值对
            SharedPreUtils.getInstance(this, "downloads").put(bean.playUrl.toString(),bean.playUrl.toString())
            SharedPreUtils.getInstance(this, "download_state").put(playUrl.toString(), true)
        }, {
            showToast("下载失败")
        })
    }

    private fun prepareVideo() {
        var uri = intent.getStringExtra("loaclFile")
        if(uri!=null){
            logE("uri:"+uri)
            gsy_player.setUp(uri, false, null, null)
        }else{
            gsy_player.setUp(bean.playUrl, false, null, null)
        }
        //增加封面
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageViewAsyncTask(mHandler, this, imageView).execute(bean.feed)
        gsy_player.titleTextView.visibility = View.GONE
        gsy_player.backButton.visibility = View.VISIBLE
        orientationUtils = OrientationUtils(this, gsy_player)
        gsy_player.setIsTouchWiget(true);
        //关闭自动旋转，为了直接显示横屏
        gsy_player.isRotateViewAuto = false;
        gsy_player.isLockLand = false;
        gsy_player.isShowFullAnimation = false;
        gsy_player.isNeedLockFull = true;
        gsy_player.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils.resolveByClick();
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            gsy_player.startWindowFullscreen(mContext, true, true);
        }
        gsy_player.setLockClickListener { view, lock ->
            //配合下方的onConfigurationChanged
            orientationUtils.isEnable = !lock
        }
        gsy_player.backButton.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

    }

    //使用AsyncTask进行异步处理数据，获取视频封面的数据，用于展示
    private class ImageViewAsyncTask(handler: Handler, activity: VideoDetailActivity, private val mImageView: ImageView) : AsyncTask<String, Void, String>() {
        private var handler = handler
        private var mPath: String? = null
        private var mIs: FileInputStream? = null
        private var mActivity: VideoDetailActivity = activity
        override fun doInBackground(vararg params: String): String? {
            val future = Glide.with(mActivity)
                    .load(params[0])
                    .downloadOnly(100, 100)
            try {
                val cacheFile = future.get()
                mPath = cacheFile.absolutePath
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            return mPath
        }
        //当我们的异步任务执行完之后，就会将结果返回给这个方法，这个方法也是在UI Thread当中调用的，我们可以将返回的结果显示在UI控件上
        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            try {
                mIs = FileInputStream(s)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(mIs)
            mImageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)
        }
    }

    override fun onBackPressed() {
        orientationUtils.let {
            orientationUtils.backToProtVideo()
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        orientationUtils.let {
            orientationUtils.releaseListener()
        }
    }

    //重写函数：屏幕适配、旋转发生改变时的控制函数
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!gsy_player.isIfCurrentIsFullscreen) {
                    gsy_player.startWindowFullscreen(mContext, true, true)
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (gsy_player.isIfCurrentIsFullscreen) {
                    logI("nothing to do")
                }
                orientationUtils.let { orientationUtils.isEnable = true }
            }
        }
    }
}
