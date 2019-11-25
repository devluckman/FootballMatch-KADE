package com.man.hellosport.utils

import com.man.hellosport.utils.FormatDate.formatDateMatch
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class FormatDateTest {

    @Test
    fun testFormatDateMatch() {
        val dateFormat = "EEE, dd MMM yyyy"
        val date = "2019-11-24"
        assertEquals("Sun, 24 Nov 2019", formatDateMatch(date, dateFormat))
    }
}