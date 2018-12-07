package com.vue.mv.microvideo.ui.activity

import android.app.Service
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.BaseActivity
import com.weblib.webview.AccountWebFragment
import com.weblib.webview.BaseWebviewFragment
import com.weblib.webview.CommonWebFragment
import com.weblib.webview.WebConstants

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui.activity
 * @ClassName:      WebViewActivity
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 19:15
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 20:36
 * @UpdateRemark:
 * @Version:        1.0
 */
class WebViewActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_common_web

    private var title: String? = null
    private var url: String? = null

    internal var webviewFragment: BaseWebviewFragment? = null

    override fun initData() {
    }

    override fun initView() {
        title = intent.getStringExtra(WebConstants.INTENT_TAG_TITLE)
        url = intent.getStringExtra(WebConstants.INTENT_TAG_URL)
        setTitle(title)
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        val level = intent.getIntExtra("level", WebConstants.LEVEL_BASE)
        webviewFragment = null
        if (level == WebConstants.LEVEL_BASE) {
            webviewFragment = CommonWebFragment.newInstance(url)
        } else {
            webviewFragment = AccountWebFragment.newInstance(url)
        }
        transaction.replace(R.id.web_view_fragment, webviewFragment).commit()
    }

    override fun start() {

    }


    fun open(context: Context, title: String, url: String, testLevel: Int) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WebConstants.INTENT_TAG_TITLE, title)
        intent.putExtra(WebConstants.INTENT_TAG_URL, url)
        intent.putExtra("level", testLevel)
        if (context is Service) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (webviewFragment != null && webviewFragment is BaseWebviewFragment) {
            val flag = webviewFragment!!.onKeyDown(keyCode, event)
            if (flag) {
                return flag
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
