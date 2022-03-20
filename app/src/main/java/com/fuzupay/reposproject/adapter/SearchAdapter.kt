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
import kotlinx.android.synthetic.main.individual_item_view.view.*

class SearchAdapter  : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    private var items = ArrayList<Item>()

    fun setListData(data:ArrayList<Item>){
        this.items = data
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var title: TextView = itemView.tvTitle
        private val desc: TextView = itemView.text1
        private val item1: TextView = itemView.text2
        private val item2: TextView = itemView.text3
        private val image: ImageView = itemView.image


        fun bind(data: Item){
            title.text = data.name
            desc.text = data.description
            item1.text = data.created_at
            item2.text = data.comments_url


            val url = data.owner.avatar_url
            Glide.with(image)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.individual_item_view,parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }
}