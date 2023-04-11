package com.example.globussoft_assignment.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.globussoft_assignment.databinding.LayoutDialogFeedbackBinding
import com.example.globussoft_assignment.feature.feedback.model.Feedback
import com.example.globussoft_assignment.feature.feedback.viewmodel.MainActivityViewModel
import java.lang.ref.WeakReference

object DialogUtils {

    private lateinit var feedbackDialog: Dialog

    fun shouldShowFeedbackDialog(
        context: WeakReference<Context>? = null,
        viewModel: WeakReference<MainActivityViewModel>? = null,
        shouldShowDialog: Boolean
    ) {
        context?.get()?.let {context->
            val binding = LayoutDialogFeedbackBinding.inflate(LayoutInflater.from(context))
            feedbackDialog = Dialog(context)
            feedbackDialog.setContentView(binding.root)
            feedbackDialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            viewModel?.get()?.let {
                binding.viewModel = it
            }
            binding.feedback = Feedback()
            feedbackDialog.setCancelable(true)
        }
        if(shouldShowDialog){
            feedbackDialog.show()
        }else{
            if(feedbackDialog.isShowing){
                viewModel?.get()?.onDialogClosed()
                feedbackDialog.dismiss()
            }
        }
    }
}