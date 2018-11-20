package com.musasyihab.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.musasyihab.footballclub.adapter.ClubItemAdapter
import com.musasyihab.footballclub.model.ClubModel
import com.musasyihab.footballclub.view.MainActivityView
import org.jetbrains.anko.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ClubItemAdapter
    private var clubItems: ArrayList<ClubModel> = ArrayList(Collections.emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clubItems = generateData()
        adapter = ClubItemAdapter(clubItems, this, ClubListAdapterListener())

        MainActivityView(adapter).setContentView(this)

    }

    private fun generateData(): ArrayList<ClubModel> {
        val data: ArrayList<ClubModel> = ArrayList(Collections.emptyList())

        val clubNames = resources.getStringArray(R.array.club_name)
        val clubIcons = resources.obtainTypedArray(R.array.club_image)
        var i = 0
        while(i < clubNames.size){
            val club = ClubModel(clubNames[i], clubIcons.getResourceId(i, 0))
            data.add(club)
            i++
        }

        return data
    }

    private inner class ClubListAdapterListener : ClubItemAdapter.Listener {

        override fun onItemClicked(item: ClubModel, position: Int) {
            Toast.makeText(this@MainActivity, "Club: "+item.name, Toast.LENGTH_SHORT).show()
        }

    }
}
