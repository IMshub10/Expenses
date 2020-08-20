package com.summer.expenses.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

object MainRepository {
    private var  temp=-1
    private val fruitnames: List<String> =
        listOf("mango", "apple", "orange", "pineapple", "guava", "figs")
    private val _currentrandomfruit = MutableLiveData<String>()
    val currentrandomfruit: LiveData<String>
        get() = _currentrandomfruit
    init {
        _currentrandomfruit.value= fruitnames[0]
    }
    fun getRandomFruitName():String{
        temp=(temp+1)%6
        return fruitnames[temp]
    }

    fun onChange(){
        _currentrandomfruit.value = getRandomFruitName()
    }
}