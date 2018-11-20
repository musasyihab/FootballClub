package com.musasyihab.footballclub

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.musasyihab.footballclub.model.ClubModel
import com.musasyihab.footballclub.view.ClubDetailView
import org.jetbrains.anko.setContentView

class ClubDetailActivity : AppCompatActivity() {

    object EXTRA {
        const val INDEX: String = "INDEX"
    }

    private lateinit var clubItem: ClubModel
    private var index: Int = 0
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar = supportActionBar!!

        if(intent.hasExtra(EXTRA.INDEX)) {
            index = intent.getIntExtra(EXTRA.INDEX, 0)
        }

        clubItem = getData(index)
        actionBar.title = clubItem.name

        ClubDetailView(clubItem).setContentView(this)

    }

    private fun getData(index: Int): ClubModel {
        val clubNames = resources.getStringArray(R.array.club_name)
        val clubIcons = resources.obtainTypedArray(R.array.club_image)
        val clubInfo = resources.getStringArray(R.array.club_info)

        return ClubModel(clubNames[index], clubIcons.getResourceId(index, 0), clubInfo[index])
    }
}