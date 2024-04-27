package com.beapps.alexaappjetpackcomposeversion.core.presentation

import android.content.Context
import android.util.Log
import java.io.IOException
import java.io.InputStream

fun loadJSONFromAsset(context: Context , filePath : String): String? {
    val json: String = try {
        val inputStream: InputStream = context.assets.open(filePath)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer)
    }
    catch (ex: IOException) {
        ex.printStackTrace()
        Log.d("ab_do", "loadJSONFromAsset: -->" + ex.message)
        return null
    }
    return json
}