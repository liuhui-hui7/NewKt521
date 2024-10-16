package com.example.newkt521.util

import com.example.newkt521.model.bean.CinemaByRegionBean
import com.example.newkt521.model.bean.ComingSoonBean
import com.example.newkt521.model.bean.DetailBean
import com.example.newkt521.model.bean.HotBean
import com.example.newkt521.model.bean.LoginBean
import com.example.newkt521.model.bean.NearbyCinemasBean
import com.example.newkt521.model.bean.RecommendBean
import com.example.newkt521.model.bean.RegionListBean
import com.example.newkt521.model.bean.ReleasBean
import com.example.newkt521.model.bean.SearchBean
import com.example.newkt521.model.bean.XBannerBean
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    //搜索
    @GET("movieApi/movie/v2/findMovieByKeyword")
    suspend fun getSearch(@Query("keyword") keyword:String,@Query("page") page:Int,@Query("count") count:Int): SearchBean

    //轮播
    @GET("movieApi/tool/v2/banner")
    suspend fun getXBanner(): XBannerBean

    //正在上映
    @GET("movieApi/movie/v2/findReleaseMovieList")
    suspend fun getReleas(@Query("page") page:Int,@Query("count")count: Int):ReleasBean

    //电影详情页
    @GET("movieApi/movie/v2/findMoviesDetail")
    suspend fun getDetail(@Query("movieId") movieId:Int):DetailBean

    //即将上映
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    suspend fun getComingSoon(@Query("page") page:Int, @Query("count") count:Int): ComingSoonBean

    //热门电影
    @GET("movieApi/movie/v2/findHotMovieList")
    suspend fun getHot(@Query("page") page:Int, @Query("count") count:Int): HotBean

    //推荐影院
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    suspend fun getRecommend(@Query("page") page: Int,@Query("count") count: Int):RecommendBean

    //附近影院
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    suspend fun getNearbCinemas(@Query("longitude") longitude:Double,@Query("latitude") latitude:Double,@Query("page") page:Int,@Query("count") count:Int): NearbyCinemasBean
    //区域影院
    @GET("movieApi/tool/v2/findRegionList")
    suspend fun getReginList(): RegionListBean

    //区域查询影院
    @GET("movieApi/cinema/v2/findCinemaByRegion")
    suspend fun getCinemaByRegion(@Query("regionId") regionId:Int): CinemaByRegionBean
    //登录接口
    @POST("movieApi/user/v2/login")
    suspend fun getLogin(@Query("email") email:String, @Query("pwd") pwd:String): LoginBean


















}