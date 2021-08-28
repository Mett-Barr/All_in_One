package com.example.allinone.page2.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComponentsViewModel : ViewModel() {
    private val _num = MutableLiveData(0)
    val num: LiveData<Int>
        get() = _num

    fun plusOne() {
        _num.value = _num.value?.plus(1)
    }

    fun minusOne() {
        _num.value = _num.value?.minus(1)
    }

    var initSliderNum = 50.00f
    private val _sliderNum = MutableLiveData(initSliderNum)
    val sliderNum: LiveData<Float>
        get() = _sliderNum

    fun setSliderNum(float: Float) {
        _sliderNum.value = float
    }

}