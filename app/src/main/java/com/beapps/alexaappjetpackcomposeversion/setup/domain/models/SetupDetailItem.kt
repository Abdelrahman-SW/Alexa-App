package com.beapps.alexaappjetpackcomposeversion.setup.domain.models

import androidx.annotation.DrawableRes

data class SetupDetailItem(
    val content: String?,
    @DrawableRes val imageId: Int?,
)
