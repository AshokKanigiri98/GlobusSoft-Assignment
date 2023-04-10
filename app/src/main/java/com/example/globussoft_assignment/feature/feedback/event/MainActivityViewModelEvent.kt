package com.example.globussoft_assignment.feature.feedback.event

import com.example.globussoft_assignment.feature.feedback.model.Feedback

sealed class MainActivityViewModelEvent{
    object OnMoreButtonClicked: MainActivityViewModelEvent()
    object ShowFeedbackDialog: MainActivityViewModelEvent()
    data class OnSubmitFeedBackClicked(val feedback: Feedback): MainActivityViewModelEvent()
}
