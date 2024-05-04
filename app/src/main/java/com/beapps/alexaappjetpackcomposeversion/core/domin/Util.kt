package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.IOException
import java.io.InputStream

fun Context.shareText(text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Commands With Others !")
    startActivity(shareIntent)
}

fun Context.getDrawableIdFromImageName(imageName: String?): Int? {
    return imageName?.let {
        if (it.isEmpty()) return null
        val id = resources.getIdentifier(
            it,
            "drawable",
            packageName
        )
        if (id <= 0) return null else id
    }
}
