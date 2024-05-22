package com.beapps.alexaappjetpackcomposeversion.core.data

import android.content.Context
import java.io.IOException
import java.io.InputStream


fun loadJSONData(context : Context , path : String): String? {
    val json: String = try {
        val inputStream: InputStream = context.assets.open(path)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
    return json
}