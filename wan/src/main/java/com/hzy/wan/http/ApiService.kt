package com.hzy.wan.http


import com.hzy.wan.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    //首页文章列表
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): HomeArticleBean

    //首页banner
    @GET("/banner/json")
    suspend fun getHomeBanner(): BannerBean

    //公众号列表
    @GET("/wxarticle/chapters/json")
    suspend fun getWxarticle(): OfficialAccountBean

    //查看某个公众号历史数据
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWxarticleList(@Path("id") id: Int, @Path("page") page: Int): OfficialArticleBean

    //项目分类
    @GET("/project/tree/json")
    suspend fun getProjectType(): ProjectTypeBean

    //项目列表数据
    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): ProjectBean

    //体系
    @GET("/tree/json")
    suspend fun getSysType(): SystemBean

    //体系下的文章
    @GET("/article/list/{page}/json")
    suspend fun getSysArticle(@Path("page") page: Int, @Query("cid") cid: Int): SystemArticleBean
}
