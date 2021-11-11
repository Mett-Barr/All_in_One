package com.example.allinone.page2.workManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkManagerViewModel : ViewModel() {

    private val _valueA = MutableLiveData(0)
    val valueA: LiveData<Int>
        get() = _valueA

    fun plusA() {
        _valueA.value = _valueA.value?.plus(1)
    }

    fun zeroA() {
        _valueA.value = 0
    }



    private val _valueB = MutableLiveData(0)
    val valueB: LiveData<Int>
        get() = _valueB

    fun plusB() {
        _valueB.value = _valueB.value?.plus(1)
    }

    fun zeroB() {
        _valueB.value = 0
    }

    private val _chain = MutableLiveData(false)
    val chain: LiveData<Boolean>
        get() = _chain

    fun chainClick() {
        _chain.value = !_chain.value!!
    }
}