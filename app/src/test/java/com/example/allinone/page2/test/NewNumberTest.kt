package com.example.allinone.page2.test

import org.junit.Assert.*

import org.junit.Test

class NewNumberTest {

    init {}

    @Test
    fun test01() {
        assertEquals(NewNumber().check(NewNumber().new()), true)
    }

    @Test
    fun test02() {
        assertEquals(NewNumber().new() in 0..10, true)
    }
}