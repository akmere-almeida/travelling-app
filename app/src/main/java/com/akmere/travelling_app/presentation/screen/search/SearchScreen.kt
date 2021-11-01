package com.akmere.travelling_app.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.AppDimensions.searchScreenDividerAlpha
import com.akmere.travelling_app.presentation.common.AppDimensions.searchScreenTextInputWeight
import com.akmere.travelling_app.presentation.common.Constants.Navigation.SEARCH_SUGGESTION_FILTER_KEY
import com.akmere.travelling_app.presentation.common.Constants.Navigation.SEARCH_TERM_KEY
import com.akmere.travelling_app.presentation.common.Constants.minLengthSearchInput
import com.akmere.travelling_app.presentation.model.Suggestion
import com.akmere.travelling_app.presentation.state.UiState
import com.akmere.travelling_app.presentation.viewmodel.SearchViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.SearchViewModelFactory

@Composable
fun SearchScreen(navHostController: NavHostController) {
    val viewModel: SearchViewModel = viewModel(
        factory = SearchViewModelFactory(AppDependencies.providesLoadFilterSuggestions())
    )

    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        val input = remember {
            mutableStateOf("")
        }
        val focusRequester = remember { FocusRequester() }

        Column(
            Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Fechar")
            }

            SearchInputRow(
                input = input.value,
                focusRequester = focusRequester,
                onValueChange = {
                    input.value = it

                    if (input.value.length >= minLengthSearchInput)
                        viewModel.loadSuggestions(input.value)
                }, onSearch = {
                sendSearchResult(navHostController, it)
            }, onClearInput = {
                input.value = ""
                viewModel.loadSuggestions("")
            }
            )

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .shadow(1.dp)
                    .alpha(searchScreenDividerAlpha)
            )
            when (uiState) {
                is UiState.Error -> {
                    // TODO()
                }
                UiState.Loading -> {
                    // TODO()
                }
                is UiState.Success -> SuggestionList(uiState.data.suggestions) {
                    sendSearchResult(navHostController, it.label, it.filter)
                }
            }
        }
        SideEffect {
            focusRequester.requestFocus()
        }
    }
}

private fun sendSearchResult(
    navHostController: NavHostController,
    searchTerm: String? = "",
    suggestionFilter: String? = ""
) {
    navHostController.previousBackStackEntry?.savedStateHandle?.apply {
        set(SEARCH_TERM_KEY, searchTerm)
        set(SEARCH_SUGGESTION_FILTER_KEY, suggestionFilter)
    }

    navHostController.popBackStack()
}

@Composable
private fun SuggestionList(
    suggestions: List<Suggestion>,
    onSuggestionSelected: (Suggestion) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "Sugestões",
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(suggestions) { suggestion ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSuggestionSelected(suggestion)
                        }
                ) {
                    Text(
                        text = suggestion.label, color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchInputRow(
    input: String,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClearInput: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 4.dp)
    ) {
        TextField(
            value = input,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusRequester.freeFocus()
                    onSearch(input)
                }
            ),
            onValueChange = {
                onValueChange(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .focusable()
                .focusRequester(focusRequester)
                .weight(searchScreenTextInputWeight),
            placeholder = {
                Text(
                    text = "Quero ir para...",
                    style = MaterialTheme.typography.h5.copy(
                        color = Color.LightGray
                    )
                )
            }
        )

        IconButton(
            onClick = { onClearInput() },
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .padding(4.dp)
                .background(Color.LightGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                tint = MaterialTheme.colors.primary,
                contentDescription = "Limpar campo de pesquisa"
            )
        }
    }
}
