package com.example.globussoft_assignment.feature.feedback.view

import android.app.Dialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.globussoft_assignment.R
import com.example.globussoft_assignment.databinding.ActivityMainBinding
import com.example.globussoft_assignment.feature.feedback.event.MainActivityViewModelEvent
import com.example.globussoft_assignment.feature.feedback.viewmodel.MainActivityViewModel
import com.example.globussoft_assignment.utils.DialogUtils
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        observeViewModel()
    }

    private val onPopupMenuItemListener = PopupMenu.OnMenuItemClickListener { menuItem ->

        when (menuItem.itemId) {
            R.id.record_feedback -> {
                viewModel.showFeedbackDialog()
            }
        }
        false
    }

    private fun observeViewModel() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is MainActivityViewModelEvent.OnMoreButtonClicked -> {
                    showPopup()
                }
                is MainActivityViewModelEvent.ShowFeedbackDialog -> {
                    DialogUtils.shouldShowFeedbackDialog(
                        context = WeakReference(this),
                        viewModel = WeakReference(viewModel),
                        shouldShowDialog = true
                    )
                }
                is MainActivityViewModelEvent.OnSubmitFeedBackClicked -> {
                    closeAndClearDialog()
                    binding.submittedFeedback = it.feedback
                }
            }
        })
    }

    private fun showPopup() {
        val popup = PopupMenu(this, binding.layoutHeader.imageView)
        popup.inflate(R.menu.appbar_more_menu)
        popup.setOnMenuItemClickListener(onPopupMenuItemListener)
        popup.show()
    }


    private fun closeAndClearDialog() {
        DialogUtils.shouldShowFeedbackDialog(
            shouldShowDialog = false,
            viewModel = WeakReference(viewModel)
        )
    }
}