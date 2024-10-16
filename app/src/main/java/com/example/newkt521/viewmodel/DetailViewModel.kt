package com.example.newkt521.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkt521.model.bean.DetailBean
import com.example.newkt521.util.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {
    var Detail = MutableLiveData<DetailBean>()
        fun getDetail(movieId:Int){
            viewModelScope.launch(Dispatchers.IO) {
                Detail.postValue(HttpUtils.apiService.getDetail(movieId))
            }
        }
}