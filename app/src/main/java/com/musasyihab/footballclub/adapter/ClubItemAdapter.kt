package com.musasyihab.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.musasyihab.footballclub.R
import com.musasyihab.footballclub.model.ClubModel
import com.musasyihab.footballclub.view.ClubItemView
import org.jetbrains.anko.AnkoContext

class ClubItemAdapter(private val mList: ArrayList<ClubModel>,
                        context: Context, adapterListener: ClubItemAdapter.Listener) : RecyclerView.Adapter<ClubItemAdapter.ItemHolder>() {

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
        return ItemHolder(ClubItemView().createView(AnkoContext.create(parent.context, parent)), listener)
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

            icon = view.findViewById(ClubItemView.imgClubIcon)
            name = view.findViewById(ClubItemView.tvClubName)
            layout = view.findViewById(ClubItemView.layoutClub)

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