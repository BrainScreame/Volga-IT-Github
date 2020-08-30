package com.osenov.mygithub.util

import android.content.Context
import android.graphics.Color
import androidx.annotation.NonNull
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

object LanguageColorHelper {
    private var colorMap: Map<String, Int>? = null
    private var defaultColor = 0

    fun getColor(@NonNull context: Context, @NonNull languageName: String): Int? {
        if (colorMap == null) {
            initColorMap(context)
            defaultColor = Color.parseColor("#000000")
        }
        return if (colorMap?.containsKey(languageName)!!) colorMap!![languageName] else defaultColor
    }

    private fun initColorMap(@NonNull context: Context) {
        colorMap = HashMap()
        try {
            val inputStream = context.assets.open("colors.json")
            val content: String = convertStreamToString(inputStream)
            val jsonObject = JSONObject(content)
            val iterator = jsonObject.keys()
            while (iterator.hasNext()) {
                val name = iterator.next()
                val language = jsonObject.getJSONObject(name)
                val colorStr = language.getString("color")
                if (colorStr == "null") {
                    continue
                }
                val color = Color.parseColor(colorStr)
                (colorMap as HashMap<String, Int>)[name] = color
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }


}