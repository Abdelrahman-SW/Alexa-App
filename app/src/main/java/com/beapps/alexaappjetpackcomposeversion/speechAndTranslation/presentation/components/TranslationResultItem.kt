package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslateResult
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor35Alpha

@Composable
fun TranslationResultItem(modifier: Modifier = Modifier , translateResult: String , onItemClicked: (String) -> Unit , onShareClick: (String) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(mainComponentColor35Alpha)
            .clickable {
                onItemClicked(translateResult)
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            text = translateResult,
            fontFamily = poppinsFontFamily,
        )

        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(id = R.string.share),
            modifier
                .clickable {
                    onShareClick(translateResult)
                },
            tint = lightBlue100
        )
    }
}