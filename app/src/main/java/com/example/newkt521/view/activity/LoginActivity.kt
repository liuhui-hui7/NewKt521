package com.example.newkt521.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.base.EncryptUtil
import com.example.newkt521.databinding.ActivityLoginBinding
import com.example.newkt521.util.MyApp
import com.example.newkt521.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>(R.layout.activity_login) {
    override fun dataabser() {
        viewModel.loginData.observe(this, Observer {
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            if (it.status == "0000"){
                //说明登录成功
                MyApp.sp.edit()
                    .putString("Head_image",it.result.userInfo.headPic)
                    .putString("People_Name",it.result.userInfo.nickName)
                    .putInt("userId",it.result.userId)
                    .putString("SessionId",it.result.sessionId)
                    .putBoolean("is_login",true)
                    .commit()

                Log.i("AGB","userInfo"+it.result.userId)
                Log.i("AGC","SessionId"+it.result.sessionId)
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }

        })
    }

    override fun initview() {
        dataBinding.LoginButton.setOnClickListener(){
            val email= dataBinding.loginEmail.text.toString()
            val passWords = dataBinding.loginPwd.text.toString()
            Toast.makeText(this,"11",Toast.LENGTH_SHORT).show()
            if (email.isEmpty()){
                Toast.makeText(this,"请输入邮箱账号",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (passWords.isEmpty()){
                Toast.makeText(this,"请输入邮箱密码",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
              val EncryPassword = EncryptUtil.encrypt(passWords)
                Log.i("SS",EncryPassword.toString())
                viewModel.getLogin(email,EncryPassword)






            }
        }
    }

}