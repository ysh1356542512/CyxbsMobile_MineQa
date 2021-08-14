 package com.mredrock.cyxbs.mine.page.stamp.center.model

/**
 *@author ZhiQiang Tu
 *@time 2021/8/9  12:39
 *@signature 我们不明前路，却已在路上
 */

data class ShopPageData(
    val title1: ShopTitle,
    val decorator: List<ShopProductOne>,
    val title2: ShopTitle,
    val entity: List<ShopProductOne>
)

data class ShopTitle(
    val mainContext: String,
    val description: String
)

data class ShopProductOne(
    val image: String,
    val price: Int,
    val restCount: Int,
    val name: String,
    val id:String = "1"
)

data class ShopProductTwo(
    val image: String,
    val price: Int,
    val restCount: Int,
    val name: String,
    val image2: String,
    val price2: Int,
    val restCount2: Int,
    val name2: String,
    val id1: String = "1",
    val id2: String = "2"
)

 data class ItemClickInfo(
     val currentProgress:Int,
     val maxProgress:Int,
     val name: String
 )