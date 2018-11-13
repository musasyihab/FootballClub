package com.musasyihab.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.musasyihab.footballclub.R
import com.musasyihab.footballclub.model.ClubModel

class ClubItemAdapter(private val mList: ArrayList<ClubModel>,
                        context: Context, adapterListener: ClubItemAdapter.Listener) : RecyclerView.Adapter<ClubItemAdapter.ItemHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private val adapterListener: ClubItemAdapter.Listener = adapterListener
    private val listener: ClubHolderListener

    init {
        listener = ClubHolderListener()
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ClubItemAdapter.ItemHolder, position: Int) {
        val item = mList[position]
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubItemAdapter.ItemHolder {
        val inflater = mInflater.inflate(R.layout.club_item_layout, parent,false)
        return ItemHolder(inflater, listener)
    }

    interface Listener {
        fun onItemClicked(item: ClubModel, position: Int)
    }

    class ItemHolder(v: View, private var listener: Listener) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var item: ClubModel? = null

        var icon: ImageView
        var name: TextView
        var layout: LinearLayout

        init {
            icon = view.findViewById(R.id.club_item_icon) as ImageView
            name = view.findViewById(R.id.club_item_name) as TextView
            layout = view.findViewById(R.id.club_item_layout) as LinearLayout

        }

        fun bindItem(item: ClubModel) {
            this.item = item
            name.text = item.name

            Glide.with(view.context).load(item.icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .error(R.drawable.ic_photo_black)
                    .into(icon)

            layout.setOnClickListener { listener.onItemClicked(item, adapterPosition) }
        }

        interface Listener {
            fun onItemClicked(item: ClubModel, position: Int)
        }

    }

    private inner class ClubHolderListener : ItemHolder.Listener {

        override fun onItemClicked(item: ClubModel, position: Int) {
            adapterListener.onItemClicked(item, position)
        }
    }

}