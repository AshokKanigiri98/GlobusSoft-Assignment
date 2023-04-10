package com.example.globussoft_assignment.feature.feedback.viewmodel

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.globussoft_assignment.feature.feedback.model.Feedback
import com.example.globussoft_assignment.feature.feedback.event.MainActivityViewModelEvent
import com.example.globussoft_assignment.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _event = SingleLiveEvent<MainActivityViewModelEvent>()
    val event: SingleLiveEvent<MainActivityViewModelEvent> = _event
    val shouldShowRating = ObservableField<Int>()
    val validationErrorText = ObservableField<String?>()

    /**
     * This will be Triggered when more button clicks in appbar
     */
    fun onMenuClicked() {
        _event.postValue(MainActivityViewModelEvent.OnMoreButtonClicked)
    }

    fun showFeedbackDialog() {
        _event.postValue(MainActivityViewModelEvent.ShowFeedbackDialog)
    }

    fun onRatingsClicked(ratingCount: Int) {
       shouldShowRating.set(ratingCount)
    }

    fun onSubmitFeedbackClicked(feedback: Feedback){
        if (validateInputs(feedback)){
            validationErrorText.set(null)
            feedback.apply { feedbackRating = shouldShowRating.get()?:0 }
            _event.postValue(MainActivityViewModelEvent.OnSubmitFeedBackClicked(feedback))
        }
    }

    fun onDialogClosed(){
        shouldShowRating.set(0)
    }

    private fun validateInputs(feedback: Feedback): Boolean{
        return when{
            !feedback.email.isValidEmail() && feedback.name.trim().isEmpty() ->{
                validationErrorText.set("** Please make sure you entered valid and name & email before submitting")
                false
            }
            feedback.name.trim().isEmpty() ->{
                validationErrorText.set("** Please make sure you entered valid and name before submitting")
                false
            }
            !feedback.email.isValidEmail() ->{
                validationErrorText.set("** Please make sure you entered valid email before submitting")
                false
            }
            else -> {
                validationErrorText.set(null)
                true
            }
        }
    }

    private fun String.isValidEmail() : Boolean{
       return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}