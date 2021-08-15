package com.mredrock.cyxbs.mine.page.stamp.exchange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
//import com.mredrock.cyxbs.mine.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.network.bean.GoodsInfo
import com.mredrock.cyxbs.mine.page.stamp.exchange.presenter.GoodsContract

/**
 * @Date : 2021/8/11
 * @By : ysh
 * @Usage :
 * @Request : God bless my code
 */
class GoodsViewModel : BaseViewModel(), GoodsContract.GoodsVM {
    private val _goodsAmount :MutableLiveData<Int> = MutableLiveData()

    val goodsAmount :LiveData<Int>
    get() = _goodsAmount

    private val _goodsInfo: MutableLiveData<GoodsInfo.Data> = MutableLiveData()

    val goodsInfo: LiveData<GoodsInfo.Data>
        get() = _goodsInfo

    private val _goodsDate: MutableLiveData<String> = MutableLiveData()

    val goodsDate: LiveData<String>
        get() = _goodsDate

    private val _goodsType: MutableLiveData<String> = MutableLiveData()

    val goodsType: LiveData<String>
        get() = _goodsType

    private val _goodsDescription1: MutableLiveData<String> = MutableLiveData()

    val goodsDescription1: LiveData<String>
        get() = _goodsDescription1

    private val _goodsDescription2: MutableLiveData<String> = MutableLiveData()

    val goodsDescription2: LiveData<String>
        get() = _goodsDescription2

    private val _goodsUrls: MutableLiveData<List<String>> = MutableLiveData()

    val goodsUrls :LiveData<List<String>>
    get() = _goodsUrls

    private val _userAccount:MutableLiveData<Int> = MutableLiveData()
    val userAccount = _userAccount
    fun setUserAccount(value:Int){
        _userAccount.postValue(value)
    }


    override fun setGoodsValue(value: GoodsInfo.Data) {
        _goodsInfo.value = value
    }

    override fun setGoodsType(value: String) {
        _goodsType.value = value
    }

    override fun setDescription(value: String, value2: String) {
        _goodsDescription1.value = value
        _goodsDescription2.value = value2
    }

    override fun setGoodsDate(value: String) {
        _goodsDate.value = value
    }

    override fun setGoodsUrls(value: List<String>) {
        _goodsUrls.value = value
    }

    override fun setGoodsAmount(value: Int) {
        _goodsAmount.value = value
    }


}