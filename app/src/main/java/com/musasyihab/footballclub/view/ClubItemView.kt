package com.musasyihab.footballclub.view

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.musasyihab.footballclub.R
import org.jetbrains.anko.*

class ClubItemView : AnkoComponent<ViewGroup> {

    companion object {
        val tvClubName = 1
        val imgClubIcon = 2
        val layoutClub = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        val typedValue = TypedValue()
        ctx.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
        val rippleBg = typedValue.resourceId

        verticalLayout {
            lparams(matchParent, wrapContent) {
                leftMargin = dip (8)
                rightMargin = dip (8)
                bottomMargin = dip (8)
            }
            backgroundColor = ContextCompat.getColor(ctx, R.color.white)
            gravity = Gravity.CENTER

            linearLayout {
                id = layoutClub
                lparams(matchParent, wrapContent) {
                    padding = dip (8)
                    isClickable = true
                    isFocusable = true
                }
                gravity = Gravity.CENTER
                backgroundResource = rippleBg

                imageView {
                    id = imgClubIcon
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = dip(56), height = dip(56)) {
                    rightMargin = dip(16)
                }

                textView {
                    id = tvClubName
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(matchParent, wrapContent)
            }

            view {
                backgroundColor = ContextCompat.getColor(ctx, R.color.border)
            }.lparams(width = matchParent, height = dip(4))

        }
    }
}