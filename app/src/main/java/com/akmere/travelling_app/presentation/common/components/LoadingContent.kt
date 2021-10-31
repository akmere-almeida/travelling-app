package com.akmere.travelling_app.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun LoadingContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Carregando...",
            style = TextStyle(
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.SemiBold
            )
        )
        CircularProgressIndicator(
            modifier= Modifier.padding(top = 8.dp),
            color = MaterialTheme.colors.secondaryVariant,
        )
    }

}
