package com.beapps.alexaappjetpackcomposeversion.commands.data

import com.beapps.alexaappjetpackcomposeversion.R

fun String.getDrawableIdFromCategoryName () : Int {
    return when  {
        equals("Basic" , ignoreCase = true) -> R.drawable.basic
        else -> R.drawable.notifications
    }
}