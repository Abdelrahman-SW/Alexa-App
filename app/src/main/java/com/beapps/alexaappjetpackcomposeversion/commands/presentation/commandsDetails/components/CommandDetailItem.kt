package com.beapps.alexaappjetpackcomposeversion.commands.presentation.commandsDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beapps.alexaappjetpackcomposeversion.commands.domain.models.CommandDetails
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.ui.theme.lightBlue100


@Composable
fun CommandDetailItem(
    modifier: Modifier = Modifier,
    item: CommandDetails,
    onItemClick: (CommandDetails) -> Unit,
    onFavouriteClick: (CommandDetails) -> Unit,
    onShareClick: (CommandDetails) -> Unit,
) {

    var isFavourite by rememberSaveable(item) {
        mutableStateOf(item.isFavourite)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(item)
            }
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            text = item.title,
            fontFamily = poppinsFontFamily,
        )

        Icon(
            imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favourite",
            modifier
                .clickable {
                    isFavourite = !isFavourite
                    onFavouriteClick(item)
                }
                .padding(end = 4.dp),
            tint = Color.Red
        )

        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share",
            modifier
                .clickable {
                    onShareClick(item)
                }
                .padding(4.dp),
            tint = lightBlue100
        )
    }
}
