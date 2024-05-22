package com.beapps.alexaappjetpackcomposeversion.setup.presentation.setupItems.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beapps.alexaappjetpackcomposeversion.R
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.core.presentation.randomGradientColors
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100

@Composable
fun SetupItem(modifier: Modifier = Modifier, item: SetupItem, onItemClicked: (SetupItem) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(item)
            }
            .padding(vertical = 6.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SetupIcon(item = item)

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp),
            text = item.title,
            fontSize = 15.sp,
            fontFamily = poppinsFontFamily,
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.click),
            modifier
                .padding(horizontal = 4.dp),
            tint = lightBlue100
        )
    }
}