package com.beapps.alexaappjetpackcomposeversion.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.core.domin.openUrl
import com.beapps.alexaappjetpackcomposeversion.core.domin.reviewApp
import com.beapps.alexaappjetpackcomposeversion.core.domin.shareApp
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.settings.domain.SettingsConstant
import com.beapps.alexaappjetpackcomposeversion.settings.domain.SettingsType
import com.beapps.alexaappjetpackcomposeversion.settings.domain.settings
import com.beapps.alexaappjetpackcomposeversion.settings.presentation.components.SettingItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        color = Color.White,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mainComponentColor),
                scrollBehavior = scrollBehavior
            )
        }
    )
    { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (setting in settings) {
                SettingItem(settingItem = setting) {
                    when (it.type) {
                        SettingsType.REVIEW_APP -> context.reviewApp()
                        SettingsType.SHARE_APP -> context.shareApp(subject = SettingsConstant.SHARE_SUBJECT_MESSAGE)
                        SettingsType.PRIVACY -> context.openUrl(SettingsConstant.PRIVACY_URL)
                        SettingsType.TERMS_OF_USE -> context.openUrl(SettingsConstant.TERMS_OF_USE_URL)
                    }
                }
            }
        }
    }
}