package com.example.newkt521.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ayvytr.adapter.smart
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.databinding.ActivityDetailBinding
import com.example.newkt521.model.bean.DetailBean
import com.example.newkt521.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import java.text.SimpleDateFormat

class Detail_Activity : BaseActivity<DetailViewModel,ActivityDetailBinding>(R.layout.activity_detail) {

    //导演
   var detailAdapter = smart(mutableListOf<DetailBean.Result.MovieDirector>(),R.layout.detail_director,{item, position ->
       findViewById<ImageView>(R.id.Detail_image).load(item.photo)
       findViewById<TextView>(R.id.Detail_name).text = item.name
   }){ }
    //演员
    var movieAdapter = smart(mutableListOf<DetailBean.Result.MovieActor>(),R.layout.detail_actor,{item, position ->
        findViewById<ImageView>(R.id.Actor_Image).load(item.photo)
        findViewById<TextView>(R.id.Actor_Name).text = item.name
    }){}
    //预告
    var videoAdpater=smart(mutableListOf<DetailBean.Result.ShortFilm>(),R.layout.stangsyvideo,{item, position ->
        findViewById<StandardGSYVideoPlayer>(R.id.Video).setUp(item.videoUrl,true,"")
    }){}

    //剧照
    var postViewAdapter = smart(mutableListOf<String>(),R.layout.detail_actor,{item, position ->
        findViewById<ImageView>(R.id.Actor_Image).load(item){
            placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        }
    }){}




    override fun dataabser() {
        viewModel.Detail.observe(this){
            findViewById<ImageView>(R.id.Detail_Image).load(it.result.imageUrl)
            findViewById<TextView>(R.id.Detail_Score).text = "评分："+it.result.score.toString()+"分"
            findViewById<TextView>(R.id.Detail_commentNum).text = "评论："+it.result.commentNum+"万条"
            findViewById<TextView>(R.id.Detail_Name).text = it.result.name
            findViewById<TextView>(R.id.Detail_movieType).text = it.result.movieType
            findViewById<TextView>(R.id.Detail_duration).text = it.result.duration
            val patten = "yyy-MM-dd"
            val simpleDateFormat =SimpleDateFormat(patten)
            findViewById<TextView>(R.id.Detail_releaseTime).text = simpleDateFormat.format(it.result.releaseTime)
            findViewById<TextView>(R.id.Detail_place).text = it.result.placeOrigin

            //剧情
            findViewById<TextView>(R.id.Detail_jq).text = it.result.summary
            findViewById<TextView>(R.id.Detail_director).text = "导演"+(it.result.commentNum.toString())+""
            //导演
            detailAdapter.update(it.result.movieDirector)

            //演员
            movieAdapter.update((it.result.movieActor))
            //预告
            videoAdpater.update(it.result.shortFilmList)
            //剧照
            postViewAdapter.update((it.result.posterList))
        }
    }

    override fun initview() {

        //选座位
        dataBinding.xuan.setOnClickListener(){
            startActivity(Intent(this,MoviesSeatMainActivity::class.java))
        }
        val movieId = intent.getIntExtra("movieId", 0)

        //导演
        viewModel.getDetail(movieId)
        dataBinding.RecDirector.adapter = detailAdapter
        dataBinding.RecDirector.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        //演员
        dataBinding.RecActor.adapter = movieAdapter
        dataBinding.RecActor.layoutManager = GridLayoutManager(this,4)

        //预告
        dataBinding.RecVideo.adapter=videoAdpater
        dataBinding.RecVideo.layoutManager = LinearLayoutManager(this)

        //剧照
        dataBinding.RecPostlist.adapter = postViewAdapter
        dataBinding.RecPostlist.layoutManager = GridLayoutManager(this,3)

        //tab
        dataBinding.DetailTab.addTab(dataBinding.DetailTab.newTab().setText("介绍"))
        dataBinding.DetailTab.addTab(dataBinding.DetailTab.newTab().setText("预告"))
        dataBinding.DetailTab.addTab(dataBinding.DetailTab.newTab().setText("剧照"))
        dataBinding.DetailTab.addTab(dataBinding.DetailTab.newTab().setText("影评"))

        dataBinding.DetailTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
               when(p0?.position){
                   0->{
                       dataBinding.js.visibility = View.VISIBLE
                       dataBinding.RecVideo.visibility = View.GONE
                       dataBinding.RecPostlist.visibility = View.VISIBLE
                   }
                   1->{
                       dataBinding.js.visibility=View.GONE
                       dataBinding.RecVideo.visibility=View.VISIBLE
                       dataBinding.RecPostlist.visibility=View.GONE
                   }
                   2->{
                       dataBinding.js.visibility=View.GONE
                       dataBinding.RecVideo.visibility=View.GONE
                       dataBinding.RecPostlist.visibility=View.VISIBLE
                   }
                   3->{

                   }
               }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }


}