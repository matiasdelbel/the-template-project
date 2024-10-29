package com.holidays.app.ui.trip.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.dbel.design.system.theme.AppTheme

@Composable
fun Trip(
    viewModel: TripViewModel,
    modifier: Modifier = Modifier,
) {
    val screenState = viewModel.screenState

    Trip(
        tripName = screenState.name,
        links = screenState.links,
        modifier = modifier,
    )

    if (screenState.loading) { /*TODO */ }
}

@Composable
fun Trip(
    tripName: String,
    links: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = tripName,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = AppTheme.spacers.md, vertical = AppTheme.spacers.sm)
        )

        Column {
            Text(
                text = "Useful links",
                style = AppTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = AppTheme.spacers.md, vertical = AppTheme.spacers.sm)
            )

            links.forEach {
                ClickableText(
                    text = buildAnnotatedString {
                        append(it)
                        addStyle(
                            style = SpanStyle(
                                color = AppTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            ),
                            start = 0,
                            end = it.length - 1
                        )

                        addStringAnnotation(
                            tag = "URL",
                            annotation = it,
                            start = 0,
                            end = it.length - 1
                        )
                    },
                    onClick = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = AppTheme.spacers.md, vertical = AppTheme.spacers.xs)
                )
            }
        }
    }
}

@Composable
@Preview
fun TripPreview() = AppTheme {
    Surface(color = AppTheme.colorScheme.surface) {
        Trip(
            tripName = "Chipre 2024",
            links = listOf(
                "https://guidetoeurope.com/es/chipre/mejores-paquetes-viajes/viajes-por-carretera/14-dias-empezando-en-larnaca",
                "https://miaventuraviajando.com/top-15-que-ver-hacer-chipre/",
                "https://www.coordenadasdedestino.com/que-ver-en-chipre-guia-completa-para-visitar-chipre/"
            )
        )
    }
}