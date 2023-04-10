package com.example.globussoft_assignment

sealed class MainActivityViewModelEvent{
    object OnMoreButtonClicked: MainActivityViewModelEvent()
    object ShowFeedbackDialog: MainActivityViewModelEvent()
    data class OnSubmitFeedBackClicked(val feedback: Feedback): MainActivityViewModelEvent()
}
