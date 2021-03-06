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
import com.csy.afv.mvp.model.bean.HotBean
import com.csy.afv.mvp.model.bean.VideoBean
import com.csy.afv.ui.activity.VideoDetailActivity
import com.csy.afv.utils.ImageLoadUtils
import com.csy.afv.utils.ObjectSaveUtils
import com.csy.afv.utils.SharedPreUtils

/**
 * @Author:CSY
 * @Date:
 * @Description: 这是“热门”界面的实际处理的adapter，展示排行视频，并在点击时将数据传送到VideoDetailActivity进行播放
 */
class RankAdapter(context: Context, list: ArrayList<HotBean.ItemListBean.DataBean>) : RecyclerView.Adapter<RankAdapter.RankViewHolder>() {
    var context: Context? = null;
    var list: ArrayList<HotBean.ItemListBean.DataBean>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        var photoUrl : String? = list?.get(position)?.cover?.feed
        photoUrl?.let { ImageLoadUtils.display(context!!, holder.iv_photo, it) }
        var title : String? = list?.get(position)?.title
        holder.tv_title.text = title
        var category = list?.get(position)?.category
        var duration = list?.get(position)?.duration
        var minute =duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long )
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
        holder.tv_time.text = "$category / $realMinute'$realSecond''"
        holder.itemView.setOnClickListener {
            //跳转视频详情页
            var intent : Intent = Intent(context, VideoDetailActivity::class.java)
            var desc = list?.get(position)?.description
            var playUrl = list?.get(position)?.playUrl
            var blurred = list?.get(position)?.cover?.blurred
            var collect = list?.get(position)?.consumption?.collectionCount
            var share = list?.get(position)?.consumption?.shareCount
            var reply = list?.get(position)?.consumption?.replyCount
            var time = System.currentTimeMillis()
            var videoBean  = VideoBean(photoUrl,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        return RankViewHolder(inflater!!.inflate(R.layout.item_rank, parent, false), context!!)
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class RankViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView = itemView.findViewById(R.id.tv_title) as TextView
        var tv_time: TextView = itemView.findViewById(R.id.tv_time) as TextView
        init {
            tv_title.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")

        }
    }
}