package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.Context
import android.os.Build

class DeviceLocalProviderAndroidImpl(val context: Context) : DeviceLocalProvider {
    override fun getCurrentDeviceLocalTag(): String {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }
        val tag = locale.toLanguageTag()
        return tag
    }
}