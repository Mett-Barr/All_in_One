package com.example.allinone.page2.testAndHilt

class NewNumber {
    fun new(): Int = (0..10).random()

    fun check(num: Int): Boolean {
        return num in 0..10
    }

    fun qwe() { val i = (0..10).random()}
}