package com.musasyihab.footballclub.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import com.musasyihab.footballclub.MainActivity
import com.musasyihab.footballclub.R
import com.musasyihab.footballclub.adapter.ClubItemAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityView (private val mAdapter: ClubItemAdapter) : AnkoComponent<MainActivity> {

    companion object {
        const val clubListId = 1
    }

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        verticalLayout {
            lparams(matchParent, matchParent) {
                gravity = Gravity.CENTER
            }
            backgroundColor = ContextCompat.getColor(ctx, R.color.background)

            recyclerView {
                id = clubListId
                lparams(matchParent, matchParent) {
                    topMargin = dip (8)
                }
                layoutManager = LinearLayoutManager(ctx)
                adapter = mAdapter
            }
        }
    }
}