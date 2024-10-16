package com.example.newkt521.view.activity

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.base.EncryptUtil
import com.example.newkt521.databinding.ActivityMainBinding
import com.example.newkt521.view.fragment.CinemaFragment
import com.example.newkt521.view.fragment.MeFragment
import com.example.newkt521.view.fragment.MoveFragment
import com.example.newkt521.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(R.layout.activity_main) {
    override fun dataabser() {

    }

    override fun initview() {

        dataBinding.bj.setOnClickListener(){
            startActivity(Intent(this, BannerViewPageActivity::class.java))
        }
        dataBinding.search.setOnClickListener(){
            startActivity(Intent(this, SearchMainActivity::class.java))
        }

        val encrypt = EncryptUtil.encrypt("chao1013!@#")
        Log.i("SS",encrypt.toString())

        var title=arrayOf("首页","电影","我的")
        var fragmentId = arrayOf(MoveFragment(),CinemaFragment(),MeFragment())

        dataBinding.vpMain.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getCount(): Int {
                //fragment数量
                return fragmentId.size
            }

            override fun getItem(position: Int): Fragment {
                //fragment下标
                return fragmentId.get(position)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                //底部导航下标
                return title.get(position)
            }

        }
        //设置下标
        dataBinding.tabMain.setupWithViewPager(dataBinding.vpMain)

        //设置底部导航图标
        dataBinding.tabMain.getTabAt(0)?.setIcon(R.drawable.home)
        dataBinding.tabMain.getTabAt(1)?.setIcon(R.drawable.ks5)
        dataBinding.tabMain.getTabAt(2)?.setIcon(R.drawable.me)










    }

}