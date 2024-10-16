package com.example.newkt521.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkt521.model.bean.SearchBean
import com.example.newkt521.util.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {
    val  Search=MutableLiveData<SearchBean>()
       fun getSearch(keyword:String,page:Int,count:Int) {
           viewModelScope.launch (Dispatchers.IO){
               Search.postValue(HttpUtils.apiService.getSearch(keyword,page,count))
           }
       }
}