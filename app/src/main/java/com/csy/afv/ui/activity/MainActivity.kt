
package com.csy.afv.ui.activity

import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.csy.afv.R
import com.csy.afv.base.BaseActivity
import com.csy.afv.base.BaseFragment
import com.csy.afv.base.curFragment
import com.csy.afv.base.tabsId
import com.csy.afv.ui.fragment.CategoryFragment
import com.csy.afv.ui.fragment.HomeFragment
import com.csy.afv.ui.fragment.HotFragment
import com.csy.afv.data.playbackop.OPStates
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadio()
    }

    private fun setRadio() {
        rb_home.isChecked = true
        chooseFragment(R.id.rb_home)
        rg_root.setOnCheckedChangeListener({ _, checkedId -> chooseFragment(checkedId) })
    }

    private fun chooseFragment(checkedId: Int) {
        curFragment = checkedId

        //创建Fragment管理器
        val beginTransaction = supportFragmentManager.beginTransaction()

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(checkedId.toString())
        if (fragment == null) {
            when (checkedId) {
                R.id.rb_home -> beginTransaction.add(R.id.fl_content, HomeFragment(), checkedId.toString())
                R.id.rb_hot -> beginTransaction.add(R.id.fl_content, HotFragment(), checkedId.toString())
                R.id.rb_category -> beginTransaction.add(R.id.fl_content, CategoryFragment(), checkedId.toString())
                R.id.rb_mine -> beginTransaction.add(R.id.fl_content, HomeFragment(), checkedId.toString())
            }
        }
        tabsId.forEach { tab ->

            val aFragment = supportFragmentManager.findFragmentByTag(tab.toString()) as BaseFragment?

            if (tab == checkedId) {
                aFragment?.let {
                    aFragment.setToolBar()
                    beginTransaction.show(it)
                }
            } else {
                aFragment?.let {
                    beginTransaction.hide(it)
                }
            }
        }
        beginTransaction.commit()
    }

    fun record(view: View) {
        OPStates.isRecord = true
        Toast.makeText(this, "开始录制", Toast.LENGTH_SHORT).show()
        record()
    }

    fun play(view: View) {
        OPStates.play = true
        Toast.makeText(this, "开始播放", Toast.LENGTH_SHORT).show()
        play()
    }

}
