package com.summer.expenses.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.expenses.repositories.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        private val mainRepository =MainRepository
    }
    val currentrandomfruit: LiveData<String>
        get() = mainRepository.currentrandomfruit


    fun onChange() = mainRepository.onChange()

    val edittext = MutableLiveData<String>()


    private val _displayEdit = MutableLiveData<String>()
    val displayEdit: LiveData<String>
        get() = _displayEdit

    fun displayEditTextContentClick(){
       randomfruit()
    }
    fun randomfruit(){
        edittext.value =mainRepository.getRandomFruitName()
    }

}