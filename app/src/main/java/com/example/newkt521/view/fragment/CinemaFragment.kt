package com.example.newkt521.view.fragment

import android.adservices.topics.Topic
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ayvytr.adapter.smart
import com.example.newkt521.R
import com.example.newkt521.base.BaseFramgment
import com.example.newkt521.databinding.FragmentCinemaBinding
import com.example.newkt521.model.bean.CinemaByRegionBean
import com.example.newkt521.model.bean.NearbyCinemasBean
import com.example.newkt521.model.bean.RecommendBean
import com.example.newkt521.model.bean.RegionListBean
import com.example.newkt521.viewmodel.CinemaViewModel
import com.google.android.material.tabs.TabLayout
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader


class CinemaFragment : BaseFramgment<CinemaViewModel, FragmentCinemaBinding>(R.layout.fragment_cinema) {

    var page= 1;
    //推荐影院

    var CommendAdapter = smart(listOf<RecommendBean.Result>(),R.layout.movies,{item, position ->
        findViewById<ImageView>(R.id.Recommend_image).load(item.logo)
        findViewById<TextView>(R.id.Recommend_Name).text = item.name
        findViewById<TextView>(R.id.Recommend_Releas).text = item.address
    }){ }.click { item, position ->
        Toast.makeText(context,"点击了${item.name}",Toast.LENGTH_SHORT).show()
    }
    //附近影院
    var NearbAdapter= smart(listOf<NearbyCinemasBean.Result>(),R.layout.nearb,{item, position ->
        findViewById<ImageView>(R.id.Nearb_image).load(item.logo)
        findViewById<TextView>(R.id.Nearb_Name).text = item.name
        findViewById<TextView>(R.id.Nearb_Releas).text = item.address
        findViewById<TextView>(R.id.Nearb_km).text = "${item.distance/1000000}km"
    }){}.click { item, position ->
        Toast.makeText(context,"点击了${item.name}",Toast.LENGTH_SHORT).show()
    }
    //区域影院
    var RegionListadapter = smart(listOf<RegionListBean.Result>(),R.layout.region_cinema,{item, position ->
       findViewById<TextView>(R.id.region_text).text = item.regionName
    }){}.click { item, position ->
        CinemaAdapter.clear()
        viewModel.getCinnemaByRegion(item.regionId)
        Toast.makeText(context,"点击了${item.regionName}",Toast.LENGTH_SHORT).show()
    }

    //区域搜索
    val CinemaAdapter = smart(listOf<CinemaByRegionBean.Result>(),R.layout.region_cinema,{item, position ->
        findViewById<TextView>(R.id.region_text).text=item.name
    }){ }.click { item, position ->
        Toast.makeText(context,"点击了${item.name}",Toast.LENGTH_SHORT).show()
    }


    override fun datasuber() {
        viewModel.Recommend.observe(this){
            if (it.result !=null){
                CommendAdapter.update(it.result,true)
            }
        }
        viewModel.NearbCinemas.observe(this){
            NearbAdapter.update(it.result)
        }

        viewModel.ReginList.observe(this){
            RegionListadapter.update(it.result)
        }
        viewModel.CinnemaByRegion.observe(this){
            CinemaAdapter.update(it.result)
        }









    }

    override fun initview() {
        viewModel.getRecommend(1,5)
        dataBinding.recCommend.adapter = CommendAdapter
        dataBinding.recCommend.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        //刷新头
        dataBinding.smartRefres.setRefreshHeader(ClassicsHeader(context))
        //尾加载
        dataBinding.smartRefres.setRefreshFooter(ClassicsFooter(context))

        dataBinding.smartRefres.setOnRefreshListener {
            CommendAdapter.clear()
            page =1
            viewModel.getRecommend(page,5)
            dataBinding.smartRefres.finishRefresh()
        }
        dataBinding.smartRefres.setOnLoadMoreListener {
            page++
            viewModel.getRecommend(page,5)
            dataBinding.smartRefres.finishLoadMore()
        }

        //附近影院
        viewModel.getNearbCinames(31.389406286267196,121.5033553411102,1,5)
        dataBinding.recNearb.adapter =NearbAdapter
        dataBinding.recNearb.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        viewModel.getReginList()
        dataBinding.recRegion.adapter = RegionListadapter
        dataBinding.recRegion.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        viewModel.getCinnemaByRegion(1)
        dataBinding.recCinema.adapter = CinemaAdapter
        dataBinding.recCinema.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)














        dataBinding.cinemaTab.addTab(dataBinding.cinemaTab.newTab().setText("推荐影院"))
        dataBinding.cinemaTab.addTab(dataBinding.cinemaTab.newTab().setText("附近影院"))
        dataBinding.cinemaTab.addTab(dataBinding.cinemaTab.newTab().setText("宝安区"))

        dataBinding.cinemaTab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when(p0?.position){
                    0->{
                        dataBinding.smartRefres.visibility = View.VISIBLE
                        dataBinding.recNearb.visibility = View.GONE
                        dataBinding.recCinema.visibility = View.GONE
                        dataBinding.recRegion.visibility = View.GONE

                    }1->{
                        dataBinding.smartRefres.visibility = View.GONE
                        dataBinding.recNearb.visibility=View.VISIBLE
                        dataBinding.recCinema.visibility=View.GONE
                        dataBinding.recRegion.visibility=View.GONE
                    }2->{
                        dataBinding.smartRefres.visibility=View.GONE
                        dataBinding.recNearb.visibility=View.GONE
                        dataBinding.recCinema.visibility=View.VISIBLE
                        dataBinding.recRegion.visibility=View.VISIBLE
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