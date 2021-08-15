package com.mredrock.cyxbs.mine.page.stamp.utils

import androidx.lifecycle.LiveData

/**
 * @Date : 2021/8/15   14:43
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
class GoodsPageState private constructor():LiveData<Int>() {

    public override fun postValue(value: Int?) {
        super.postValue(value)
    }

    companion object{
        val instance by lazy {
            GoodsPageState()
        }
    }
}