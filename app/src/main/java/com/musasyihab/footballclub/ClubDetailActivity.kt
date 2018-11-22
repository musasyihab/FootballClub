package com.musasyihab.footballclub

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.musasyihab.footballclub.model.ClubModel
import org.jetbrains.anko.longToast
import kotlinx.android.synthetic.main.club_detail_activity.*

class ClubDetailActivity : AppCompatActivity() {

    object EXTRA {
        const val INDEX: String = "INDEX"
    }

    private lateinit var clubItem: ClubModel
    private var index: Int = 0
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.club_detail_activity)

        actionBar = supportActionBar!!

        if(intent.hasExtra(EXTRA.INDEX)) {
            index = intent.getIntExtra(EXTRA.INDEX, 0)
        }

        clubItem = getData(index)
        actionBar.title = clubItem.name

        clubDetailName.text = clubItem.name
        clubDetailInfo.text = clubItem.info

        Glide.with(this).load(clubItem.icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .error(R.drawable.ic_photo_black)
                .into(clubDetailIcon)

        longToast(clubItem.name)

    }

    private fun getData(index: Int): ClubModel {
        val clubNames = resources.getStringArray(R.array.club_name)
        val clubIcons = resources.obtainTypedArray(R.array.club_image)
        val clubInfo = resources.getStringArray(R.array.club_info)

        return ClubModel(clubNames[index], clubIcons.getResourceId(index, 0), clubInfo[index])
    }
}