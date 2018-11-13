package com.musasyihab.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.musasyihab.footballclub.adapter.ClubItemAdapter
import com.musasyihab.footballclub.model.ClubModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var list: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ClubItemAdapter
    private var clubItems: ArrayList<ClubModel> = ArrayList(Collections.emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        list = findViewById(R.id.club_list)

        linearLayoutManager = LinearLayoutManager(this)
        list.layoutManager = linearLayoutManager

        clubItems = generateData()

        adapter = ClubItemAdapter(clubItems, this, ClubListAdapterListener())
        list.adapter = adapter
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
