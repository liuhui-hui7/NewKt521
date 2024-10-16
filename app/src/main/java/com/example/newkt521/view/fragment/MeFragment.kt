package com.example.newkt521.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import coil.transform.CircleCropTransformation
import com.example.newkt521.R
import com.example.newkt521.base.BaseFramgment
import com.example.newkt521.databinding.FragmentMeBinding
import com.example.newkt521.util.MyApp
import com.example.newkt521.view.activity.LoginActivity
import com.example.newkt521.viewmodel.MainViewModel
import java.util.jar.Attributes.Name


class MeFragment : BaseFramgment<MainViewModel,FragmentMeBinding>(R.layout.fragment_me) {
    override fun datasuber() {

    }

    override fun initview() {

        dataBinding.HeadImage.setOnClickListener{
            Toast.makeText(context,"22",Toast.LENGTH_SHORT).show()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        dataBinding.RegisterTui.setOnClickListener(){
            MyApp.sp.edit().clear().commit()
            val  image_head=MyApp.sp.getString("Head_image","")
            val Name= MyApp.sp.getString("People_Name","")
            if (!image_head!!.isEmpty()){
                dataBinding.HeadImage.load(image_head){
                    error(R.drawable.sibai)
                    placeholder(R.drawable.ic_launcher_background)
                        .transformations(CircleCropTransformation())
                }
            }
            else {
                dataBinding.HeadImage.load(R.drawable.sibai)
            }
            if (!Name!!.isEmpty()){
                dataBinding.LoginText.text = Name
            }else {
                dataBinding.LoginText.text =""
                dataBinding.RegisterTui.text = ""
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val image_head = MyApp.sp.getString("Head_image","")
        val Name = MyApp.sp.getString("People_Name","")
        if (!image_head!!.isEmpty()){
            dataBinding.HeadImage.load(image_head){
                error(R.drawable.sibai)
                placeholder(R.drawable.ic_launcher_background)
                    .transformations(CircleCropTransformation())
            }
        }
        else{
            dataBinding.HeadImage.load(R.drawable.sibai)
        }
        if (!Name!!.isEmpty()){
            dataBinding.LoginText.text = Name
            dataBinding.RegisterTui.text ="退出 "
        }
    }

}