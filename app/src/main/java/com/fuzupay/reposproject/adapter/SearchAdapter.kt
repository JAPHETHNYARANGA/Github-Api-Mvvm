package com.fuzupay.reposproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.models.Item
import com.fuzupay.reposproject.models.myGithubModel
import kotlinx.android.synthetic.main.individual_item_view.view.*

class SearchAdapter  : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    var searchItems = ArrayList<Item>()

    fun searchSetListData(data : ArrayList<Item>){
        this.searchItems = data
    }

    class ViewHolder(searchView:View):RecyclerView.ViewHolder(searchView){
        private var searchtitle: TextView = itemView.tvTitle
        private val searchdesc: TextView = itemView.text1
        private val searchitem1: TextView = itemView.text2
        private val searchitem2: TextView = itemView.text3
        private val searchimage: ImageView = itemView.image

        fun searchBind(data: Item){
            searchtitle.text = data.name
            searchdesc.text = data.description
            searchitem1.text = data.created_at
            searchitem2.text = data.full_name


            val Searchurl = data.owner.avatar_url
            Glide.with(searchimage)
                .load(Searchurl)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(searchimage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val searchInflater = LayoutInflater.from(parent.context).inflate(R.layout.individual_item_view, parent, false)
        return  ViewHolder(searchInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchBind(searchItems[position])
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }
}