package com.mredrock.cyxbs.main.network

import com.mredrock.cyxbs.common.bean.RedrockApiStatus
import com.mredrock.cyxbs.common.bean.RedrockApiWrapper
import com.mredrock.cyxbs.main.bean.BindingResponse
import com.mredrock.cyxbs.main.bean.StartPage
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created By jay68 on 2018/8/10.
 */
interface ApiService {
    @GET("/magipoke-text/start/photo")
    fun getStartPage(): Observable<RedrockApiWrapper<List<StartPage>>>

    /**
     * 检查是否绑定信息
     */
    @FormUrlEncoded
    @POST("/user-secret/user/bind/is")
    fun checkBinding(@Field("stu_num") stu_num: String): Observable<RedrockApiWrapper<BindingResponse>>

    @GET("http://172.22.161.49:8080/redirect")
    fun test302(): Observable<ResponseBody>
}