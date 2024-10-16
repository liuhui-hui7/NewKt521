package com.example.newkt521.view.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.PagerAdapter
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.databinding.ActivityBannerViewPageBinding
import com.example.newkt521.viewmodel.MainViewModel
import java.util.Timer
import java.util.TimerTask


class BannerViewPageActivity : BaseActivity<MainViewModel,ActivityBannerViewPageBinding>(R.layout.activity_banner_view_page) {

    private val imageResIds = listOf(R.drawable.kt_me1,R.drawable.kt_me2,R.drawable.kt_me3)
    val textList_theme = listOf("第一个主题","第二个主题","第三个主题")
    val textLists = listOf(
        "今天学会一个单词要theme，意思是主题，咬舌的zi en啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
        "刚开始写的时候就遇到一个爆红，没有写登录就直接用她的状态爆红了哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
        "还有遇到一个布局不能世界使用要先使用layout，再写一个data，才能直接使用布局的框架，比如线性布局、相对布局哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈")

    private lateinit var timer :Timer
    override fun dataabser() {

    }

    override fun initview() {

        dataBinding.pageBanner.adapter =ImageAdater()
        startAutoScroll()

        dataBinding.viewpage.adapter = TextPagerAdapter()
        getTextData()
    }

    private fun getTextData() {
      timer = Timer()
      timer.schedule(object : TimerTask(){
          override fun run() {
             runOnUiThread {
                 val currentItem = dataBinding.viewpage.currentItem
                 val nextItem = if (currentItem == textList_theme.size -1)0 else currentItem+1
                 dataBinding.viewpage.setCurrentItem(nextItem,true)
             }
          }

      },3000,3000)
    }

    inner class TextPagerAdapter : PagerAdapter() {
       override fun getCount(): Int = imageResIds.size

       override fun isViewFromObject(view: View, `object`: Any): Boolean =view==`object`
       override fun instantiateItem(container: ViewGroup, position: Int): Any {
           val  layoutInflater =layoutInflater
           val view=layoutInflater.inflate(R.layout.fragment_text,container,false)
           val largeTextView = view.findViewById<TextView>(R.id.largeTextView)
           val smallTextView = view.findViewById<TextView>(R.id.smallTextView)
           largeTextView.text = textList_theme[position]
           smallTextView.text = textLists[position]
           container.addView(view)
           return view
       }

       override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
           container.removeView(`object` as View)
       }

   }

    private fun startAutoScroll() {
       timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                runOnUiThread {
                    //当前图片
                    val currentItem = dataBinding.pageBanner.currentItem
                    //下一个图片
                    val nextItem = if (currentItem == imageResIds.size - 1) 0 else currentItem + 1
                    dataBinding.pageBanner.setCurrentItem(nextItem,true)
                }
            }

        },3000,3000)
    }

    inner  class ImageAdater : PagerAdapter() {
        override fun getCount(): Int = imageResIds.size

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

      override fun instantiateItem(container: ViewGroup, position: Int): Any {
          //创建了一个新的试图
         val imageView = android.widget.ImageView(this@BannerViewPageActivity)
          //图片添加进去
          imageView.setImageResource(imageResIds[position])
          //设置图片的缩放类型
          imageView.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
          container.addView(imageView)
          return imageView
      }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}




