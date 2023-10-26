package com.rijks.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tmdb.app.R

@Composable
fun ArtObject(
    uiState: ArtObjectViewModel.UiState,
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit = {},
) = Column(
    modifier = modifier.verticalScroll(rememberScrollState())
) {
    ArtObjectOverview(
        imageUrl = uiState.imageUrl,
        contentDescription = uiState.title,
        imageLabel = uiState.title
    )

    AnimatedVisibility(visible = uiState.showError) {
        Error(
            label = stringResource(
                R.string.seems_that_we_cannot_retrieve_more_information_about,
                uiState.title
            ),
            actionLabel = stringResource(id = R.string.try_again),
            onAction = { onTryAgain() },
            modifier = Modifier.padding(all = 16.dp)
        )
    }

    AnimatedVisibility(visible = uiState.showFacts) {
        Column {
            FactsCard {
                uiState.date?.let {
                    FactRow(
                        iconPainter = painterResource(id = R.drawable.ic_calendar),
                        iconContentDescription = stringResource(R.string.finishing_date),
                        text = uiState.date
                    )
                }
                uiState.principalMakers?.let {
                    FactRow(
                        iconPainter = painterResource(id = R.drawable.ic_people),
                        iconContentDescription = stringResource(R.string.principal_makers),
                        text = uiState.principalMakers
                    )
                }
                uiState.subtitle?.let {
                    FactRow(
                        iconPainter = painterResource(id = R.drawable.ic_architecture),
                        iconContentDescription = stringResource(R.string.measures),
                        text = uiState.subtitle
                    )
                }
                uiState.materials?.let {
                    FactRow(
                        iconPainter = painterResource(id = R.drawable.ic_palette),
                        iconContentDescription = stringResource(R.string.materials),
                        text = uiState.materials
                    )
                }
            }

            if (!uiState.description.isNullOrBlank())
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = uiState.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(all = 16.dp)
                    )
                }
        }
    }
}

@Composable
fun ArtObjectOverview(
    imageUrl: String,
    contentDescription: String,
    imageLabel: String,
    modifier: Modifier = Modifier,
) = Card(
    modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(vertical = 8.dp)
) {
    Column {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
        )

        Text(
            text = imageLabel,
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier.padding(all = 16.dp)
        )
    }
}

@Composable
private fun FactsCard(
    modifier: Modifier = Modifier,
    facts: @Composable ColumnScope.() -> Unit = {},
) = Card(
    content = { Column(modifier = Modifier.padding(all = 16.dp)) { facts() } },
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(vertical = 8.dp)
)

@Composable
private fun FactRow(
    iconPainter: Painter,
    iconContentDescription: String,
    text: String,
    modifier: Modifier = Modifier
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier.padding(top = 2.dp)
) {
    Icon(painter = iconPainter, contentDescription = iconContentDescription)

    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(start = 4.dp)
    )
}


@Composable
fun Error(
    label: String,
    actionLabel: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) = Card(modifier = modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextButton(onClick = { onAction() }) { Text(text = actionLabel) }
    }
}
