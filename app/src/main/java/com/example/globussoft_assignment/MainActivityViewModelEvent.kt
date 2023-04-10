package com.example.globussoft_assignment

sealed class MainActivityViewModelEvent{
    object OnMoreButtonClicked: MainActivityViewModelEvent()
    object ShowFeedbackDialog: MainActivityViewModelEvent()
}
