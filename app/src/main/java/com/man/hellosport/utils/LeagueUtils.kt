package com.man.hellosport.utils

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

object LeagueUtils {
    fun getJson(context: Context) : String?{
        var json: String? = null
        try {
            val inputStream = context.assets.open("response.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

}