package com.mnatsakanyan.android.nearbyvenues.nearbyvenues.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.cardCornerRadius
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingLarge
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingMedium
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingSmall
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingTiny
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Category
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.GeoCode
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Location
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Main
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun VenueListItem(
        modifier: Modifier = Modifier,
        item: Venue
) {
    Card(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingMedium)
                    .clip(RoundedCornerShape(cardCornerRadius))
    ) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingLarge)
        ) {
            Column(
                    modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
            ) {
                Text(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        style = typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                Text(
                        text = item.location.address ?: "",
                        style = typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                Distance(distance = item.distance)
            }
            FlowRow(
                    horizontalArrangement = Arrangement.End,
                    maxItemsInEachRow = 2
            ) {
                item.categories.take(3).forEach { category ->
                    category.name?.let { name ->
                        CategoryTag(category = name)
                    }
                }
            }
        }
    }
}

@Composable
private fun Distance(
        modifier: Modifier = Modifier,
        distance: Int
) {
    Row(
            modifier = modifier,
            verticalAlignment = Alignment.Bottom
    ) {
        Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = colorScheme.primary
        )
        Text(
                modifier = Modifier.padding(horizontal = paddingSmall),
                text = stringResource(R.string.nearby_venues_distance, distance),
                style = typography.bodySmall
        )
    }
}

@Composable
private fun CategoryTag(
        modifier: Modifier = Modifier,
        category: String
) {
    Box(
            modifier = modifier
                    .wrapContentWidth()
                    .padding(paddingTiny)
                    .clip(RoundedCornerShape(cardCornerRadius))
                    .background(colorScheme.tertiaryContainer)
    ) {
        Text(
                modifier = Modifier.padding(paddingMedium),
                text = category,
                style = typography.bodySmall,
                color = colorScheme.tertiary
        )
    }
}

@Preview
@Composable
internal fun VenueListItemPreview() {
    VenueListItem(
            item = Venue(
                    categories = listOf(),
                    distance = 100,
                    geocode = GeoCode(Main(52.380190, 4.902973)),
                    location = Location(address = "Location address", null, null, null),
                    name = "Location name"
            )
    )
}

@Preview
@Composable
internal fun VenueListItemLongNamePreview() {
    VenueListItem(
            item = Venue(
                    categories = listOf(),
                    distance = 100,
                    geocode = GeoCode(Main(52.380190, 4.902973)),
                    location = Location(address = "Location address", null, null, null),
                    name = "Location long name, which goes on and on, should be ellipsized and should fit in maximum of two lines"
            )
    )
}

@Preview
@Composable
internal fun VenueListItemWithCategoryPreview() {
    VenueListItem(
            item = Venue(
                    categories = listOf(Category(name = "Category 1", icon = null, id = null)),
                    distance = 100,
                    geocode = GeoCode(Main(52.380190, 4.902973)),
                    location = Location(address = "Location address", null, null, null),
                    name = "Location name"
            )
    )
}

@Preview
@Composable
internal fun VenueListItemWithMultipleCategoryPreview() {
    VenueListItem(
            item = Venue(
                    categories = listOf(
                            Category(name = "Category 1", icon = null, id = null),
                            Category(name = "Category 2", icon = null, id = null)
                    ),
                    distance = 100,
                    geocode = GeoCode(Main(52.380190, 4.902973)),
                    location = Location(address = "Location address", null, null, null),
                    name = "Location name"
            )
    )
}

@Preview
@Composable
internal fun VenueListItemWithoutAddressPreview() {
    VenueListItem(
            item = Venue(
                    categories = listOf(Category(name = "Category 1", icon = null, id = null)),
                    distance = 100,
                    geocode = GeoCode(Main(52.380190, 4.902973)),
                    location = Location(null, null, null, null),
                    name = "Location name"
            )
    )
}
