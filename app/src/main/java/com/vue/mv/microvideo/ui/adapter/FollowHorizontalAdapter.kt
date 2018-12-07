package com.vue.mv.microvideo.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter
import com.orhanobut.logger.Logger
import com.vue.mv.microvideo.R
import com.vue.mv.microvideo.base.Constants
import com.vue.mv.microvideo.base.durationFormat
import com.vue.mv.microvideo.glide.GlideApp
import com.vue.mv.microvideo.mvp.model.bean.HomeBean
import com.vue.mv.microvideo.ui.activity.VideoDetailActivity

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.ui.activity
 * @ClassName:      FollowHorizontalAdapter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/09 12:29
 * @UpdateUser:
 * @UpdateDate:     2018/11/11 18:43
 * @UpdateRemark:
 * @Version:        1.0
 */
class FollowHorizontalAdapter(mContext: Context, categoryList: ArrayList<HomeBean.Issue.Item>, layoutId: Int) :
    CommonAdapter<HomeBean.Issue.Item>(mContext, categoryList, layoutId) {

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        val horizontalItemData = data.data
        holder.setImagePath(R.id.iv_cover_feed, object : ViewHolder.HolderImageLoader(data.data?.cover?.feed!!) {
            override fun loadImage(iv: ImageView, path: String) {
                // 加载封页图
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.drawable.placeholder_banner)
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_cover_feed))
            }

        })

        //横向 RecyclerView 封页图下面标题
        holder.setText(R.id.tv_title, horizontalItemData?.title ?: "")

        // 格式化时间
        val timeFormat = durationFormat(horizontalItemData?.duration)
        //标签
        with(holder) {
            Logger.d("horizontalItemData===title:${horizontalItemData?.title}tag:${horizontalItemData?.tags?.size}")

            if (horizontalItemData?.tags != null && horizontalItemData.tags.size > 0) {
                setText(R.id.tv_tag, "#${horizontalItemData.tags[0].name} / $timeFormat")
            } else {
                setText(R.id.tv_tag, "#$timeFormat")
            }

            setOnItemClickListener(listener = View.OnClickListener {
                goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), data)
            })
        }


    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra(VideoDetailActivity.Companion.TRANSITION, true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, pair
            )
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

}
