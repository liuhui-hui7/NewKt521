package com.example.newkt521.view.activity

import android.content.Intent
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.databinding.ActivityWelcomeBinding
import com.example.newkt521.util.MyApp
import com.example.newkt521.viewmodel.MainViewModel

class WelcomeActivity :BaseActivity<MainViewModel,ActivityWelcomeBinding>(R.layout.activity_welcome) {

    override fun dataabser() {

    }

    override fun initview() {

        val is_login = MyApp.sp.getBoolean("is_login",false)
        dataBinding.main.setOnClickListener(){
            if (is_login){
                startActivity(Intent(this, MainActivity::class.java))
            }else{

                startActivity(Intent(this,LoginActivity::class.java))
            }
            finish()
        }
    }
}