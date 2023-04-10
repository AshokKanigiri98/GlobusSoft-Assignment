package com.example.globussoft_assignment.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.globussoft_assignment.feature.feedback.model.Feedback
import com.example.globussoft_assignment.R


@BindingAdapter("shouldShowRating", "ratingNumber", requireAll = true)
fun ImageView.shouldShowRating(userSelectedRating: Int, ratingNumber: Int){
    if(ratingNumber in 1 .. userSelectedRating){
        this.setImageResource(R.drawable.baseline_star_24)
    }else{
        this.setImageResource(R.drawable.baseline_star_border_24)
    }
}

@BindingAdapter("showSubmittedFeedbackTextContent")
fun TextView.showSubmittedFeedbackTextContent(feedback: Feedback?){
    if(feedback == null){
        this.text = resources.getString(R.string.home_info_text)
    }else{
        this.text = "Feedback Recorded successfully \n\n Name : ${feedback.name} \n Email: ${feedback.email} \n Submitted Ratings: ${feedback.feedbackRating} \n Feedback : ${feedback.description}"
    }
}