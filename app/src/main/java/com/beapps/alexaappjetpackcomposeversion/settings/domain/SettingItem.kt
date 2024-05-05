package com.beapps.alexaappjetpackcomposeversion.settings.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

enum class SettingsType {
    REVIEW_APP,
    SHARE_APP,
    PRIVACY,
    TERMS_OF_USE
}
data class SettingItem(
    val title: String,
    val icon: ImageVector,
    val type: SettingsType
)

val settings = listOf(
    SettingItem(
        title = "Review App",
        icon = Icons.Default.Reviews,
        type = SettingsType.REVIEW_APP

    ),
    SettingItem(
        title = "Share App",
        icon = Icons.Default.Share,
        type = SettingsType.SHARE_APP
    ),
    SettingItem(
        title = "Privacy",
        icon = Icons.Default.PrivacyTip,
        type = SettingsType.PRIVACY
    ),
    SettingItem(
        title = "Terms of Use",
        icon = Icons.Default.VerifiedUser,
        type = SettingsType.TERMS_OF_USE
    )
)
