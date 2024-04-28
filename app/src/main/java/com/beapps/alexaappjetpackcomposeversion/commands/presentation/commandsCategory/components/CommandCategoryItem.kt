package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.ui.theme.darkGray80
import com.beapps.alexaappjetpackcomposeversion.ui.theme.darkGray90
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100


@Composable
fun CommandCategoryItem(
    modifier: Modifier = Modifier,
    item: CommandCategory,
    onClick: (CommandCategory) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick(item) }
            .padding(6.dp) ,
        border = BorderStroke(1.dp , darkGray80)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp, horizontal = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                modifier = modifier.size(36.dp)
            )
            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = item.title,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
            )
        }
    }
}
