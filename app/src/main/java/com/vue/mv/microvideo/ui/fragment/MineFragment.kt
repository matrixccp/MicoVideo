package com.vue.mv.microvideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseFragment
import com.vue.mv.microvideo.base.Constants
import com.vue.mv.microvideo.ui.activity.VideoDetailActivity
import com.vue.mv.microvideo.ui.activity.WebViewActivity
import com.weblib.webview.WebConstants
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui
 * @ClassName:      MineFragment
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:29
 * @UpdateRemark:
 * @Version:        1.0
 */
class MineFragment : BaseFragment() {

    companion object {
        fun getInstance(): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        mIv.setOnClickListener {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(WebConstants.INTENT_TAG_TITLE, "音乐")
            intent.putExtra(WebConstants.INTENT_TAG_URL, "http://ustbhuangyi.com/music/")
            startActivity(intent)
        }
    }

    override fun lazyLoad() {

    }
}