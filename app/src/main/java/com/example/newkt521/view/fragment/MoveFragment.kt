package com.example.newkt521.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.ayvytr.adapter.smart
import com.example.newkt521.R
import com.example.newkt521.base.BaseFramgment
import com.example.newkt521.databinding.FragmentMoveBinding
import com.example.newkt521.model.bean.ComingSoonBean
import com.example.newkt521.model.bean.HotBean
import com.example.newkt521.model.bean.ReleasBean
import com.example.newkt521.view.activity.Detail_Activity
import com.example.newkt521.viewmodel.MoveViewModel
import com.stx.xhb.androidx.transformers.Transformer
import java.text.SimpleDateFormat


class MoveFragment : BaseFramgment<MoveViewModel,FragmentMoveBinding>(R.layout.fragment_move) {

    //正在上映
    var releasAdapter = smart(mutableListOf<ReleasBean.Result>(),R.layout.comingsoon,{item, position ->
        findViewById<ImageView>(R.id.Coming_image).load(item.imageUrl)
        findViewById<TextView>(R.id.Coming_score).text = item.score.toString() + "分"
        findViewById<TextView>(R.id.Coming_Name).text = item.name
    }){ }.click { item, position ->
        startActivity(Intent(context, Detail_Activity::class.java).apply {
            putExtra("movieId",item.movieId)
        })
    }

    //即将上映
    var comingsoonAdapter = smart(mutableListOf<ComingSoonBean.Result>(),R.layout.releas,{item, position ->
        findViewById<ImageView>(R.id.Releas_image).load(item.imageUrl)
        findViewById<TextView>(R.id.Releas_Name).text = item.name
        val patten = "yyy-MM-dd日上映"
        val simpleDateFormat = SimpleDateFormat(patten)
        findViewById<TextView>(R.id.DataFrom__Releas).text = simpleDateFormat.format(item.releaseTime)
        findViewById<TextView>(R.id.People_Releas).text = item.movieId.toString()+"人想看"
        val findViewById = findViewById<Button>(R.id.Visibiy_gson)
//        val findViewById1 = findViewById<Button>(R.id.Visibiy_gsons)
//        if (item.whetherReserve == 1){
//            findViewById.visibility =View.GONE
//            findViewById1.visibility = View.VISIBLE
//        }else{
//            findViewById.visibility =View.VISIBLE
//            findViewById1.visibility = View.GONE
//        }
    }){ }.click { item, position ->
        startActivity(Intent(context, Detail_Activity::class.java).apply {
            putExtra("movieId",item.movieId)
        })
    }

    //热门电影
    var hotadapter =smart(mutableListOf<HotBean.Result>(),R.layout.hot,{item, position ->
        findViewById<ImageView>(R.id.Hot_image).load(item.imageUrl)
        findViewById<TextView>(R.id.Hot_score).text = item.score.toString() + "分"
        findViewById<TextView>(R.id.Hot_Name).text = item.name
    }){ }.click { item, position ->
        startActivity(Intent(context,Detail_Activity::class.java).apply {
            putExtra("movieId",item.movieId)
        })
    }
    var hotAdapter1=smart(mutableListOf<HotBean.Result>(),R.layout.hotmovie,{item, position ->
        findViewById<ImageView>(R.id.hotimage).load(item.horizontalImage)
        findViewById<TextView>(R.id.hot_count).text=item.score.toString()+"分"
        findViewById<TextView>(R.id.hot_Name).text=item.name

    }) {  }.click { item, position ->
        startActivity(Intent(context,Detail_Activity::class.java).apply {
            putExtra("movieId",item.movieId)
        })
    }




    override fun datasuber() {
        viewModel.Xbanner.observe(this,{
            dataBinding.bannerImage.setData(it.result,null)
            dataBinding.bannerImage.setPageTransformer(Transformer.Zoom) //转换器为缩放效果。
            dataBinding.bannerImage.setmAdapter { banner, model, view, position ->//适配器
                (view  as ImageView).load(it.result.get(position).imageUrl){
                    placeholder(R.drawable.ic_launcher_background)//占位符
                    error(R.drawable.ic_launcher_background)  //错误图
                        .transformations(RoundedCornersTransformation(25f))//圆角
                }
            }
        })

        //正在热映
        viewModel.Releas.observe(this){
            releasAdapter.update(it.result)
        }
        //即将
        viewModel.ComingSoon.observe(this){
            if (it.status == "0000"){
                comingsoonAdapter.update(it.result)
            }

        }
        viewModel.Hot.observe(this){
            hotadapter.update(it.result)
        }
        viewModel.Hot.observe(this){
            hotAdapter1.update(it.result)
        }
    }

    override fun initview() {
       viewModel.getXbanner()
        //正在热映
        viewModel.getReleas(1,5)
        dataBinding.RecComing.adapter =releasAdapter
        dataBinding.RecComing.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        //即将
        viewModel.getComingSoon(1,10)
        dataBinding.RecReseas.adapter =comingsoonAdapter
        dataBinding.RecReseas.layoutManager = LinearLayoutManager(activity)

        //热门
        viewModel.getHot(1,10)
        dataBinding.RecHot.adapter = hotadapter
        dataBinding.RecHot.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        viewModel.getHot(1,5)
        dataBinding.RecHots.adapter=hotAdapter1
        dataBinding.RecHots.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

    }

}