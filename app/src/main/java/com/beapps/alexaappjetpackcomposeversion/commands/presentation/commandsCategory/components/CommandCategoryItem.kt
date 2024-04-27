package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily

data class CommandCategory(
    val title: String,
    @DrawableRes val iconId: Int
)

@Composable
fun CommandCategoryItem(
    modifier: Modifier = Modifier,
    item: CommandCategory,
    onClick: (CommandCategory) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .clickable { onClick(item) }
    ) {
        Icon(
            painter = painterResource(id = item.iconId),
            contentDescription = item.title,
            modifier = modifier.size(48.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(text = item.title, fontFamily = poppinsFontFamily, textAlign = TextAlign.Center)
    }
}
