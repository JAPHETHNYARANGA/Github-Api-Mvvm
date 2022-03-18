package com.fuzupay.reposproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fuzupay.reposproject.R
import com.fuzupay.reposproject.models.Item
import kotlinx.android.synthetic.main.individual_item_view.view.*

class GitHubAdapter : RecyclerView.Adapter<GitHubAdapter.ViewHolder>() {

    var items = ArrayList<Item>()

    fun setListData(data:ArrayList<Item>){
        this.items = data
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title = itemView.tvTitle
        val desc = itemView.text1
        val item1 = itemView.text2
        val item2 = itemView.text3
        val image = itemView.image

        fun bind(data:Item){
            title.text = data.name
            desc.text = data.description
            item1.text = data.language.toString()
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