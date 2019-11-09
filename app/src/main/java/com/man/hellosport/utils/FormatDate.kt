package com.man.hellosport.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

object FormatDate {
    @SuppressLint("SimpleDateFormat")
    private fun formatDateMatch(date: String, format: String) : String
    {
        var result = ""
        val old = SimpleDateFormat("yyyy-MM-dd")

        try {
            val oldDate = old.parse(date)
            val newFormat = SimpleDateFormat(format)

            result = newFormat.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun getLongDate(date: String?): String {
        return formatDateMatch(date.toString(), "EEE, dd MMM yyyy")
    }
}