package com.example.newkt521.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFramgment <V:ViewModel,D:ViewDataBinding>(private  var layoutId:Int): Fragment(){
    lateinit var viewModel: V
    lateinit var dataBinding: D

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //初始化DataBinding
       dataBinding = DataBindingUtil.inflate(inflater,layoutId,container,false)
        dataBinding.lifecycleOwner =this
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化viewmodel
        var type=javaClass.genericSuperclass as ParameterizedType
        var classVM=type.actualTypeArguments[0]as Class<V>
        viewModel=ViewModelProvider(this).get(classVM)

        //封装两个方法
        initview()
        datasuber()
    }

    abstract fun datasuber()

    abstract fun initview()


    //解决内存泄露
    override fun onDestroy() {
        super.onDestroy()
        //解除绑定，避免内存泄露
        if(dataBinding != null){
            dataBinding.unbind()
        }
    }
}