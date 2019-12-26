package com.csy.afv.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.csy.afv.R
import com.csy.afv.mvp.model.bean.HomeBean
import com.csy.afv.mvp.model.bean.VideoBean
import com.csy.afv.ui.activity.VideoDetailActivity
import com.csy.afv.utils.ImageLoadUtils
import com.csy.afv.utils.ObjectSaveUtils
import com.csy.afv.utils.SharedPreUtils

/**
 * @Author:CSY
 * @Date:
 * @Description: 将主页的数据和recycleView绑定，并在点击时跳转到VideoDetailAcitivity进行播放
 */
class HomeAdatper(context: Context,list: MutableList<HomeBean.IssueListBean.ItemListBean>?) : RecyclerView.Adapter<HomeAdatper.HomeViewHolder>() {
    var context : Context? = null;
    var list : MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var inflater : LayoutInflater? = null
    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }
    override fun getItemCount(): Int {
       return list?.size ?:0
    }

    //和xml界面绑定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(inflater!!.inflate(R.layout.item_home,parent,false), context!!)
    }

    //和数据进行绑定
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //获取一些数据
        var bean = list?.get(position)
        var title = bean?.data?.title
        var category = bean?.data?.category
        var minute = bean?.data?.duration?.div(60)
        var second = bean?.data?.duration?.minus((minute?.times(60)) as Long )
        var realMinute : String
        var realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        var playUrl = bean?.data?.playUrl
        var photo = bean?.data?.cover?.feed
        var author = bean?.data?.author
        //使用工具类ImageLoadUtils展示封面图片
        ImageLoadUtils.display(context!!, holder.iv_photo, photo as String)

        //给界面组件绑定数据
        holder.tv_title?.text = title
        holder.tv_detail?.text = "类别：$category / 时间：$realMinute:$realSecond"
        if(author!=null){
            ImageLoadUtils.display(context!!, holder.iv_user, author.icon)
        }else{
            holder.iv_user?.visibility = View.GONE
        }

        //点击事件
        holder.itemView.setOnClickListener {
            //跳转视频详情页
            var intent : Intent = Intent(context, VideoDetailActivity::class.java)
            var desc = bean?.data?.description
            var duration = bean?.data?.duration
            var playUrl = bean?.data?.playUrl
            var blurred = bean?.data?.cover?.blurred
            var collect = bean?.data?.consumption?.collectionCount
            var share = bean?.data?.consumption?.shareCount
            var reply = bean?.data?.consumption?.replyCount
            var time = System.currentTimeMillis()
            var videoBean  = VideoBean(photo,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)
            var url = SharedPreUtils.getInstance(context!!,"beans").getString(playUrl!!)
            if(url.equals("")){
                var count = SharedPreUtils.getInstance(context!!,"beans").getInt("count")
                if(count!=-1){
                    count = count.inc()
                }else{
                    count = 1
                }
                SharedPreUtils.getInstance(context!!,"beans").put("count",count)
                SharedPreUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
                ObjectSaveUtils.saveObject(context!!,"bean$count",videoBean)
            }
            //将数据传送到VideoDetailActivity进行视频播放
            intent.putExtra("data",videoBean as Parcelable)
            context?.let { context -> context.startActivity(intent) }
        }
    }


    //初始化一些组件的属性
    class HomeViewHolder(itemView: View,context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_detail : TextView?= null
        var tv_title : TextView ? = null
        var iv_photo : ImageView ? = null
        var iv_user : ImageView ? = null
        init {
            tv_detail = itemView.findViewById(R.id.tv_detail) as TextView?
            tv_title = itemView.findViewById(R.id.tv_title) as TextView?
            iv_photo = itemView.findViewById(R.id.iv_photo) as ImageView?
            iv_user =  itemView.findViewById(R.id.iv_user) as ImageView?
            tv_title?.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")

        }
    }

}
