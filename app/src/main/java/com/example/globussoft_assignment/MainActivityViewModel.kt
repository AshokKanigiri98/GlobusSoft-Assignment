package com.example.globussoft_assignment

import android.util.Log
import android.util.Patterns
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.globussoft_assignment.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
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

    fun onDialogClosed(){
        shouldShowRating.set(0)
    }

    fun String.isValidEmail() : Boolean{
       return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}