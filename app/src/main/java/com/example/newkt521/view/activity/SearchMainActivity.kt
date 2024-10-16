package com.example.newkt521.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ayvytr.adapter.smart
import com.example.newkt521.R
import com.example.newkt521.base.BaseActivity
import com.example.newkt521.model.bean.SearchBean
import com.example.newkt521.databinding.ActivitySearchMainBinding
import com.example.newkt521.viewmodel.SearchViewModel

class SearchMainActivity : BaseActivity<SearchViewModel,ActivitySearchMainBinding>(R.layout.activity_search_main){
    var keyword = " "

    var SearchASdapter=smart(listOf<SearchBean.Result>(),R.layout.search,{ item, position ->
        findViewById<ImageView>(R.id.Search_image).load(item.imageUrl)
        findViewById<TextView>(R.id.Search_Name).text=item.name
        findViewById<TextView>(R.id.Search_director).text=item.director
        findViewById<TextView>(R.id.Search_starring).text=item.starring
        findViewById<TextView>(R.id.Search_Score).text=item.score.toString()+"分"
    }) {  }



    override fun dataabser() {

        viewModel.Search.observe(this){
            if (it.result != null){
                SearchASdapter.update(it.result,true)
                dataBinding.RecSearch.visibility = View.VISIBLE
                dataBinding.RelaGone.visibility = View.GONE
            }else{
                dataBinding.RecSearch.visibility = View.GONE
                dataBinding.RelaGone.visibility = View.VISIBLE
            }
        }
    }

    override fun initview() {

        dataBinding.back.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
        }
        viewModel.getSearch("不",1,5)
        dataBinding.RecSearch.adapter=SearchASdapter
        dataBinding.RecSearch.layoutManager= LinearLayoutManager(this)


        //监听事件实时搜索
        dataBinding.searchEditText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //改变前
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //改变中
            }

            override fun afterTextChanged(s: Editable?) {
                //改变后
                SearchASdapter.clear()
                keyword = s.toString()
                viewModel.getSearch(keyword,1,5)
            }

        })
    }

}