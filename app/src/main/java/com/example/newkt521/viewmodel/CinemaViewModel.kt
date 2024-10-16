package com.example.newkt521.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkt521.model.bean.CinemaByRegionBean
import com.example.newkt521.model.bean.NearbyCinemasBean
import com.example.newkt521.model.bean.RecommendBean
import com.example.newkt521.model.bean.RegionListBean
import com.example.newkt521.util.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CinemaViewModel :ViewModel(){

    //推荐影院
    val Recommend = MutableLiveData<RecommendBean>()
    fun getRecommend(page:Int,count:Int){
        viewModelScope.launch (Dispatchers.IO){
            Recommend.postValue(HttpUtils.apiService.getRecommend(page, count))
        }
    }
    //附近影院
    val NearbCinemas = MutableLiveData<NearbyCinemasBean>()
    fun getNearbCinames(longitude: Double, latitude: Double, page:Int, count:Int){
        viewModelScope.launch(Dispatchers.IO){
            NearbCinemas.postValue(HttpUtils.apiService.getNearbCinemas(longitude, latitude, page, count))
        }
    }
    //区域影院
    val ReginList = MutableLiveData<RegionListBean>()
    fun getReginList(){
        viewModelScope.launch(Dispatchers.IO){
            ReginList.postValue(HttpUtils.apiService.getReginList())
        }
    }

    //区域查询影院
    val CinnemaByRegion = MutableLiveData<CinemaByRegionBean>()
    fun getCinnemaByRegion(region:Int){
        viewModelScope.launch(Dispatchers.IO){
            CinnemaByRegion.postValue(HttpUtils.apiService.getCinemaByRegion(region))
        }
    }



}