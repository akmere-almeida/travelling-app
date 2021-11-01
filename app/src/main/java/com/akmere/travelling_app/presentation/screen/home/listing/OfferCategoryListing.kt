package com.akmere.travelling_app.presentation.screen.home.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akmere.travelling_app.presentation.model.OfferCategory
import com.akmere.travelling_app.presentation.screen.home.cards.OfferCategoryButton

@Composable
fun OfferCategoryListing(
    offerCategories: List<OfferCategory>,
    selectedCategory: OfferCategory,
    onCategorySelected: (OfferCategory) -> Unit
) {

    Column {
        Text(
            text = "Categoria",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary
        )
        LazyRow(modifier = Modifier.padding(top = 16.dp)) {
            items(offerCategories) {
                val isSelected = selectedCategory == it
                OfferCategoryButton(
                    Modifier
                        .padding(end = 16.dp)
                        .width(104.dp)
                        .height(48.dp),
                    it,
                    isSelected,
                    onCategorySelected,
                )
            }
        }
    }
}
