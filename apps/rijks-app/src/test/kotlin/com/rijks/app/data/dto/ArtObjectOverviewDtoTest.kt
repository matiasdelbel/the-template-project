package com.rijks.app.data.dto

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test
import org.hamcrest.core.Is.`is` as itIs

class ArtObjectOverviewDtoTest {

    @Test
    fun `toArtObject creates a valid ArtObjectOverview`() {
        val artObjectDto = ArtObjectOverviewDto(
            objectNumber = "SK-C-5",
            title = "De Nachtwacht",
            longTitle = "De Nachtwacht, Rembrandt van Rijn, 1642",
            principalOrFirstMaker = "Rembrandt",
            webImage = WebImageDto(url = "http://www.rijksmuseum.nl/nl/collectie/SK-C-5")
        )

        val artObject = artObjectDto.toArtObject()

        assertThat(artObject.objectNumber, itIs(equalTo("SK-C-5")))
        assertThat(artObject.title, itIs(equalTo("De Nachtwacht")))
        assertThat(artObject.longTitle, itIs(equalTo("De Nachtwacht, Rembrandt van Rijn, 1642")))
        assertThat(artObject.principalOrFirstMaker, itIs(equalTo("Rembrandt")))
        assertThat(artObject.imageUrl, itIs(equalTo("http://www.rijksmuseum.nl/nl/collectie/SK-C-5")))
    }
}
