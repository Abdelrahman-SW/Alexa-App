package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import com.beapps.alexaappjetpackcomposeversion.core.presentation.components.poppinsFontFamily

@Composable
fun CommandDetailItem(
    modifier: Modifier = Modifier,
    item: CommandDetails,
    onClick: (CommandDetails) -> Unit
) {
    Text(modifier= modifier.padding(4.dp) , text = item.title, fontFamily = poppinsFontFamily, textAlign = TextAlign.Center)
}
