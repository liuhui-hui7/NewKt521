package com.example.newkt521.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkt521.model.bean.ComingSoonBean
import com.example.newkt521.model.bean.HotBean
import com.example.newkt521.model.bean.ReleasBean
import com.example.newkt521.model.bean.XBannerBean
import com.example.newkt521.util.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoveViewModel :ViewModel() {

        //轮播
    var Xbanner = MutableLiveData<XBannerBean>()
    fun  getXbanner(){
        viewModelScope.launch (Dispatchers.IO) {
            Xbanner.postValue(HttpUtils.apiService.getXBanner())
        }
    }
    //正在上映
    var Releas=MutableLiveData<ReleasBean>()
    fun getReleas(page:Int,count:Int){

        viewModelScope.async(Dispatchers.IO){
            Releas.postValue(HttpUtils.apiService.getReleas(page, count))
        }
    }
    //即将上映
    val ComingSoon= MutableLiveData<ComingSoonBean>()
    fun getComingSoon(page: Int,count: Int){
        viewModelScope.launch(Dispatchers.IO){
            ComingSoon.postValue(HttpUtils.apiService.getComingSoon(page, count))
        }
    }

    //热门电影
    var Hot=MutableLiveData<HotBean>()
    fun getHot(page: Int,count: Int){
        viewModelScope.launch(Dispatchers.IO){
            Hot.postValue(HttpUtils.apiService.getHot(page, count))
        }
    }
}