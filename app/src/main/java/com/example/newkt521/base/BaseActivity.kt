package com.example.newkt521.base

import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<V:ViewModel,D:ViewDataBinding>(private var layoutId:Int): AppCompatActivity() {

    lateinit var viewModel: V
    lateinit var dataBinding: D

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化dataBinding
        dataBinding= DataBindingUtil.setContentView(this,layoutId)
        dataBinding.lifecycleOwner = this
        //初始化viewModel
        var type = javaClass.genericSuperclass as ParameterizedType
        var classVM = type.actualTypeArguments[0] as Class<V>
        viewModel = ViewModelProvider(this).get(classVM)

        initview()
        dataabser()
    }

    abstract fun dataabser()
    abstract fun initview()

    //解决内存泄露
    override fun onDestroy() {
        super.onDestroy()
        //解除绑定，避免内存泄露
        if (dataBinding != null){
            dataBinding.unbind()
        }
    }

}


