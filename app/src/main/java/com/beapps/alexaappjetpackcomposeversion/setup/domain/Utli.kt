package com.beapps.alexaappjetpackcomposeversion.setup.domain

import com.beapps.alexaappjetpackcomposeversion.R

fun String.getDrawableIdFromSetupTitle () : Int {
    return when  {
        equals("Echo" , ignoreCase = true) -> R.drawable.basic
        else -> R.drawable.notifications
    }
}