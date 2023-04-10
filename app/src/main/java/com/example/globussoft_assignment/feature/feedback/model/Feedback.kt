package com.example.globussoft_assignment.feature.feedback.model

data class Feedback(
    var id: String ="",
    var feedbackRating: Int = 0,
    var name: String ="",
    var email: String ="",
    var description: String =""
)
