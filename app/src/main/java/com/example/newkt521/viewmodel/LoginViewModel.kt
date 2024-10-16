package com.example.newkt521.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newkt521.model.bean.LoginBean
import com.example.newkt521.util.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    val loginData= MutableLiveData<LoginBean>()
    fun getLogin(email:String,pwd:String){
        viewModelScope.launch { Dispatchers.IO
        loginData.postValue(HttpUtils.apiService.getLogin(email, pwd))

        }
    }
}