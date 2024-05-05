package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.beapps.alexaappjetpackcomposeversion.R

const val GOOGLE_PLAY_URL_SCHEME = "market://details?id="

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

fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}


fun Context.reviewApp(errorMsg: String = "unable to find market app") {
    val uri = Uri.parse(GOOGLE_PLAY_URL_SCHEME + applicationContext.packageName)
    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        startActivity(myAppLinkToMarket)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, errorMsg , Toast.LENGTH_LONG).show()
    }
}

fun Context.shareApp(type: String = "text/plain", subject: String , chooserTitle: String = "choose one") {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = type
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val shareMessage = subject + applicationContext.packageName
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, chooserTitle))
    } catch (e: Exception) {
        e.printStackTrace();
    }
}
