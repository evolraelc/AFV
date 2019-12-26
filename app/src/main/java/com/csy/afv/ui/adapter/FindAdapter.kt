package com.csy.afv.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.csy.afv.R
import com.csy.afv.mvp.model.bean.FindBean
import com.csy.afv.utils.ImageLoadUtils

/**
 * @Author:CSY
 * @Date:
 * @Description: 将“发现”界面的数据和view里面的组件绑定，由于只有初步显示，没有详细信息显示，所以工具较为简单
 */
class FindAdapter(context: Context,list: MutableList<FindBean>?) : BaseAdapter(){
    var mContext : Context? = null
    var mList : MutableList<FindBean>? = null
    var mInflater : LayoutInflater? = null
    init {
        mContext = context
        mList = list
        mInflater = LayoutInflater.from(context)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //配置图片封面和绑定数据
        var view : View
        var holder : FindViewHolder
        if (convertView == null) {
            view = mInflater!!.inflate(R.layout.item_find,parent,false)
            holder = FindViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder = view.tag as FindViewHolder
        }
        ImageLoadUtils.display(mContext!!,holder.iv_photo, mList?.get(position)?.bgPicture!!)
        holder.tv_title?.text = mList?.get(position)!!.name
        return  view
    }

    override fun getItem(position: Int): FindBean? {
        return mList?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        if(mList!=null){
            return mList!!.size
        }else{
            return 0
        }
    }
    class FindViewHolder(itemView : View){
        var iv_photo : ImageView? = null
        var tv_title : TextView? = null
        init {
           tv_title = itemView.findViewById(R.id.tv_title) as TextView?
           iv_photo = itemView.findViewById(R.id.iv_photo) as ImageView?

        }

    }

}