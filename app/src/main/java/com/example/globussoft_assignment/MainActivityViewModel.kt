package com.example.globussoft_assignment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.globussoft_assignment.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject  constructor() : ViewModel() {

    private val _event = SingleLiveEvent<MainActivityViewModelEvent>()
    val event : SingleLiveEvent<MainActivityViewModelEvent> = _event

    /**
     * This will be Triggered when more button clicks in appbar
     */
    fun onMenuClicked(){
        _event.postValue(MainActivityViewModelEvent.OnMoreButtonClicked)
    }

    fun showFeedbackDialog(){
        _event.postValue(MainActivityViewModelEvent.ShowFeedbackDialog)
    }
}