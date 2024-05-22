package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.beapps.alexaappjetpackcomposeversion.R

const val GOOGLE_PLAY_URL_SCHEME = "market://details?id="

fun Context.shareText(text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_commands_title))
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


fun Context.reviewApp(errorMsg: String = getString(R.string.unable_to_find_market_app)) {
    val uri = Uri.parse(GOOGLE_PLAY_URL_SCHEME + applicationContext.packageName)
    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        startActivity(myAppLinkToMarket)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}

fun Context.shareApp(
    type: String = "text/plain", subject: String, chooserTitle: String = getString(
        R.string.choose_one
    )
) {
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


fun Context.isDeviceConnectedToWifi(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network: Network? = connectivityManager.activeNetwork
        return if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            networkCapabilities != null && networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        } else {
            false
        }
    }
    return true
}

fun ComponentActivity.restartApp() {
    val intent = Intent(applicationContext, this::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
    finish()
    Runtime.getRuntime().exit(0) // This ensures the app process is killed and restarted
}
