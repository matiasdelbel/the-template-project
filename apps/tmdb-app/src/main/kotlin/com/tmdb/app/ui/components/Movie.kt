package com.tmdb.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.dbel.design.system.theme.AppTheme
import com.dbel.design.system.theme.shimmerBrush
import com.tmdb.app.R

@Composable
internal fun Movie(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) = AsyncImage(
    modifier = modifier.clip(RoundedCornerShape(size = AppTheme.spacers.xs)),
    model = imageUrl,
    contentScale = ContentScale.Crop,
    contentDescription = contentDescription
)

@Composable
internal fun MovieCollectionTitle(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    style = AppTheme.typography.titleMedium,
    modifier = modifier
)

@Composable
internal fun Error(
    label: String,
    actionLabel: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) = Card(modifier = modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = AppTheme.spacers.md)
    ) {
        Text(text = label, textAlign = TextAlign.Center)
        TextButton(onClick = { onAction() }) { Text(text = actionLabel) }
    }
}

@Composable
internal fun MoviePlaceholder(
    painter: Painter,
    modifier: Modifier = Modifier,
) = Image(
    painter = painter,
    contentDescription = stringResource(R.string.movie_placeholder),
    alpha = 0.3f,
    modifier = modifier
        .clip(RoundedCornerShape(size = AppTheme.spacers.xs))
        .background(brush = shimmerBrush())
)

internal val placeholderPosters
    @Composable get() = listOf(
        painterResource(id = R.drawable.holder_batman),
        painterResource(id = R.drawable.holder_elevation),
        painterResource(id = R.drawable.holder_gladiator_ii),
        painterResource(id = R.drawable.holder_godfather),
        painterResource(id = R.drawable.holder_godfather_ii),
        painterResource(id = R.drawable.holder_gt_max),
        painterResource(id = R.drawable.holder_inside_out_ii),
        painterResource(id = R.drawable.holder_the_substance),
        painterResource(id = R.drawable.holder_robot),
        painterResource(id = R.drawable.holder_schindler),
        painterResource(id = R.drawable.holder_shawshank),
        painterResource(id = R.drawable.holder_watchmen_ii),
    )
