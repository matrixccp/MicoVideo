package com.vue.mv.microvideo.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter
import com.vue.mv.microvideo.MyApplication
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.Constants
import com.vue.mv.microvideo.glide.GlideApp
import com.vue.mv.microvideo.mvp.model.bean.CategoryBean
import com.vue.mv.microvideo.ui.activity.CategoryDetailActivity

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui.activity
 * @ClassName:      CategoryAdapter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/09 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/11 18:43
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
    CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {


    private var textTypeface: Typeface? = null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>) {
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")
        //设置方正兰亭细黑简体
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category, object : ViewHolder.HolderImageLoader(data.bgPicture) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.color.color_darker_gray)
                    .transition(DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .into(iv)
            }
        })

        holder.setOnItemClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(mContext as Activity, CategoryDetailActivity::class.java)
                intent.putExtra(Constants.BUNDLE_CATEGORY_DATA, data)
                mContext.startActivity(intent)
            }
        })
    }
}
