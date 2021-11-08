package com.thevarungupta.kotlin_coroutines_retrofit_demo.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thevarungupta.kotlin_coroutines_retrofit_demo.R
import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.model.Post
import kotlinx.android.synthetic.main.row_post_adapter.view.*

class MainAdapter(var mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var mList: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_post_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post = mList[position]
        holder.bind(post)
    }

    fun setData(list: ArrayList<Post>){
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post){
            itemView.text_view_title.text = post.title
            itemView.text_view_body.text = post.body
        }
    }
}