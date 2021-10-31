package com.akmere.travelling_app.presentation.screen.offer_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.akmere.travelling_app.presentation.common.AppColorCodes.Red

@Composable
fun ActionButtons(modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FavoriteButton(Modifier.weight(1f), isFavorite, onFavorite)

        OrderOfferButton(modifier = Modifier.weight(3f))
    }
}

@Composable
private fun OrderOfferButton(modifier: Modifier){
    OutlinedButton(
        modifier = modifier,
        onClick = {
            //TODO()
        },
        shape = RoundedCornerShape(15),
        colors = buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                "Reservar agora",
            )

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "Reservar",
            )
        }
    }
}

@Composable
private fun FavoriteButton(modifier: Modifier, isFavorite: Boolean, onFavorite: () -> Unit) {

    var favoriteIconColor = Color.LightGray
    var favoriteIcon = Icons.Outlined.FavoriteBorder

    if (isFavorite) {
        favoriteIconColor = Color(Red)
        favoriteIcon = Icons.Outlined.Favorite
    }

    IconToggleButton(
        modifier = modifier,
        checked = isFavorite,
        onCheckedChange = {
            onFavorite()
        },
    ) {
        Icon(
            imageVector = favoriteIcon,
            contentDescription = "Salvar como favorito",
            tint = favoriteIconColor
        )
    }
}