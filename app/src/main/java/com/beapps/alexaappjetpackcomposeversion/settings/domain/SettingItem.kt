package com.beapps.alexaappjetpackcomposeversion.settings.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.beapps.alexaappjetpackcomposeversion.R

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

@Composable
fun getSettingItemsList(): List<SettingItem> {
    return listOf(
        SettingItem(
            title = stringResource(R.string.review_app),
            icon = Icons.Default.Reviews,
            type = SettingsType.REVIEW_APP

        ),
        SettingItem(
            title = stringResource(R.string.share_app),
            icon = Icons.Default.Share,
            type = SettingsType.SHARE_APP
        ),
        SettingItem(
            title = stringResource(R.string.privacy),
            icon = Icons.Default.PrivacyTip,
            type = SettingsType.PRIVACY
        ),
        SettingItem(
            title = stringResource(R.string.terms_of_use),
            icon = Icons.Default.VerifiedUser,
            type = SettingsType.TERMS_OF_USE
        )
    )
}
