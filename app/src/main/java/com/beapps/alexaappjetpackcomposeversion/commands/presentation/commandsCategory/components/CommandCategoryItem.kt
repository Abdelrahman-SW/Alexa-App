package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsCategory.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandCategory
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.ui.theme.darkGray
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor35Alpha


@Composable
fun CommandCategoryItem(
    modifier: Modifier = Modifier,
    item: CommandCategory,
    onClick: (CommandCategory) -> Unit
) {
    Card(
        modifier = modifier.height(170.dp)
            .clickable { onClick(item) }
            .padding(6.dp) ,
        colors = CardDefaults.cardColors(containerColor = mainComponentColor35Alpha)
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp, horizontal = 16.dp),

        ) {
            CategoryIcon(item = item)
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = item.title,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Start,
                color = Color.White,
                fontWeight = FontWeight.Bold

            )
        }
    }
}
