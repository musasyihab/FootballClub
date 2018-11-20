package com.musasyihab.footballclub.view

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.musasyihab.footballclub.ClubDetailActivity
import com.musasyihab.footballclub.R
import com.musasyihab.footballclub.model.ClubModel
import org.jetbrains.anko.*

class ClubDetailView (private val mClub: ClubModel) : AnkoComponent<ClubDetailActivity> {

    companion object {
        const val imgClubIcon = 1
        const val tvClubName = 2
        const val tvClubInfo = 3
    }

    override fun createView(ui: AnkoContext<ClubDetailActivity>): View = with(ui){
        verticalLayout {
            lparams(matchParent, matchParent) {
                gravity = Gravity.CENTER
            }
            gravity = Gravity.CENTER
            padding = dip (16)
            backgroundColor = ContextCompat.getColor(ctx, R.color.background)

            verticalLayout {
                lparams(matchParent, matchParent) {

                }
                padding = dip (16)
                gravity = Gravity.CENTER
                backgroundColor = ContextCompat.getColor(ctx, R.color.white)

                imageView(mClub.icon) {
                    id = imgClubIcon
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = dip(96), height = dip(96)) {
                    bottomMargin = dip(8)
                }

                textView {
                    id = tvClubName
                    text = mClub.name
                    textSize = 24f
                    typeface = Typeface.DEFAULT_BOLD
                    gravity = Gravity.CENTER
                }.lparams(wrapContent, wrapContent) {
                    bottomMargin = dip(16)
                }

                textView {
                    id = tvClubInfo
                    text = mClub.info
                    textSize = 13f
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(16)
                }
            }

        }
    }
}