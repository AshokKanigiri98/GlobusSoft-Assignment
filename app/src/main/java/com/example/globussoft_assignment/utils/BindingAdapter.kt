package com.example.globussoft_assignment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.globussoft_assignment.R


@BindingAdapter("shouldShowRating", "ratingNumber", requireAll = true)
fun ImageView.shouldShowRating(userSelectedRating: Int, ratingNumber: Int){
    if(ratingNumber in 1 .. userSelectedRating){
        this.setImageResource(R.drawable.baseline_star_24)
    }else{
        this.setImageResource(R.drawable.baseline_star_border_24)
    }
}