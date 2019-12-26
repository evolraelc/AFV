package com.csy.afv.ui.fragment

import android.app.DialogFragment
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import android.widget.Toast
import android.text.TextUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import com.csy.afv.R
import com.csy.afv.ui.activity.ResultActivity
import com.csy.afv.ui.adapter.SearchAdapter
import com.csy.afv.utils.CircularRevealAnimUtils
import com.csy.afv.utils.KeyBoardUtils
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @Author:CSY
 * @Date:
 * @Description: “搜索”功能界面，使用dialogFragment实现，配合揭露效果的动画
 */
const val SEARCH_TAG = "SearchFragment"

class SearchFragment : DialogFragment(), CircularRevealAnimUtils.AnimListener,
        ViewTreeObserver.OnPreDrawListener, DialogInterface.OnKeyListener,
        View.OnClickListener {
    var data : MutableList<String> = arrayListOf("冬","摄影日常","创意广告","美食","旅行","健身","汽车","黑科技")
    lateinit var mRootView: View
    lateinit var mCircularRevealAnim: CircularRevealAnimUtils
    lateinit var mAdatper : SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);

    }

    override fun onStart() {
        super.onStart()
        initDialog()
    }

    //初始画dialog
    private fun initDialog() {
        val window = dialog.window
        val metrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.98).toInt() //DialogSearch的宽
        window!!.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.TOP)
        window.setWindowAnimations(R.style.DialogEmptyAnimation)//取消过渡动画 , 使DialogSearch的出现更加平滑
    }

    //绑定界面
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mRootView = inflater?.inflate(R.layout.fragment_search, container, false)!!
        return mRootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setData()
    }

    private fun setData() {
        mAdatper = SearchAdapter(activity, data as ArrayList<String>)
        mAdatper.setOnDialogDismissListener(object :SearchAdapter.onDialogDismiss{
            override fun onDismiss() {
                hideAnim()
            }
        })
        val manager = FlexboxLayoutManager()
        //设置主轴排列方式
        manager.flexDirection = FlexDirection.ROW
        //设置是否换行
        manager.flexWrap = FlexWrap.WRAP
        recyclerView.layoutManager = manager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdatper
    }

    private fun init() {
        //设置界面字体和监听器
        tv_hint.typeface = Typeface.createFromAsset(activity.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        mCircularRevealAnim = CircularRevealAnimUtils()
        mCircularRevealAnim.setAnimListener(this)
        dialog.setOnKeyListener(this)
        iv_search.viewTreeObserver.addOnPreDrawListener(this)
        iv_search.setOnClickListener(this)
        iv_search_back.setOnClickListener(this)
    }

    //重写的动画函数onHideAnimationEnd
    override fun onHideAnimationEnd() {
        et_search_keyword.setText("");
        dismiss();
    }

    //重写的动画函数onShowAnimationEnd
    override fun onShowAnimationEnd() {
        if (isVisible) {
            KeyBoardUtils.openKeyboard(activity, et_search_keyword);
        }
    }

    override fun onPreDraw(): Boolean {
        iv_search.viewTreeObserver.removeOnPreDrawListener(this);
        mCircularRevealAnim.show(iv_search, mRootView);
        return true;
    }

    //点击事件，控制键盘弹出收回
    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_UP) {
            hideAnim()
        } else if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN) {
            search()
        }
        return false
    }

    //关键词搜索功能，将数据传入ResultActivity
    private fun search() {
        val searchKey = et_search_keyword.text.toString()
        if (TextUtils.isEmpty(searchKey.trim({ it <= ' ' }))) {
            Toast.makeText(activity, "请输入关键字", Toast.LENGTH_SHORT).show()
        } else {
            hideAnim()
            var keyWord = et_search_keyword.text.toString().trim()
            var intent : Intent = Intent(activity, ResultActivity::class.java)
            intent.putExtra("keyWord",keyWord)
            activity?.startActivity(intent)
        }
    }

    //收回键盘，并显示动画
    private fun hideAnim() {
        KeyBoardUtils.closeKeyboard(activity, et_search_keyword);
        mCircularRevealAnim.hide(iv_search, mRootView)
    }

    //点击事件
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_search_back -> {
                hideAnim()
            }
            R.id.iv_search ->{
                search()
            }
        }
    }

}