package com.example.globussoft_assignment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.globussoft_assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        observeViewModel()
    }

    private val onPopupMenuItemListener = PopupMenu.OnMenuItemClickListener { menuItem->

        when(menuItem.itemId){
            R.id.record_feedback ->{

            }
        }
        false
    }

    private fun observeViewModel() {
        viewModel.event.observe(this, Observer {
            when(it){
                is MainActivityViewModelEvent.OnMoreButtonClicked ->{
                    showPopup()
                }
            }
        })
    }

    private fun showPopup(){
        val popup = PopupMenu(this, binding.layoutHeader.imageView)
        popup.inflate(R.menu.appbar_more_menu)
        popup.setOnMenuItemClickListener(onPopupMenuItemListener)
        popup.show()
    }


}