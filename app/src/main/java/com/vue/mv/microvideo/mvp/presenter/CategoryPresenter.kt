package com.vue.mv.microvideo.mvp.presenter

import com.vue.mv.microvideo.base.BasePresenter
import com.vue.mv.microvideo.mvp.contract.CategoryContract
import com.vue.mv.microvideo.mvp.model.CategoryModel
import com.vue.mv.microvideo.net.exception.ExceptionHandle

/**
 *
 * @ProjectName:    MicroVideo
 * @Package:        com.vue.mv.microvideo.mvp
 * @ClassName:      CategoryPresenter
 * @Description:
 * @Author:         PingChen
 * @CreateDate:     2018/11/13 12:33
 * @UpdateUser:
 * @UpdateDate:     2018/11/13 12:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryData()
            .subscribe({ categoryList ->
                mRootView?.apply {
                    dismissLoading()
                    showCategory(categoryList)
                }
            }, { t ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                }

            })

        addSubscription(disposable)
    }
}