package com.rijks.app.data.dto

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test
import org.hamcrest.core.Is.`is` as itIs

class ArtObjectDtoTest {

    @Test
    fun `toArtObject creates a valid ArtObject`() {
        val artObjectDto = ArtObjectDto(
            subTitle = "h 379,5cm × b 453,5cm × g 337kg",
            description = null,
            plaqueDescriptionEnglish = "Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...",
            materials = listOf("doek", "olieverf"),
            principalMakers = listOf(MakerDto(name = "Rembrandt")),
            dating = DatingDto(presentingDate = "2019-07-05")
        )

        val artObject = artObjectDto.toArtObject()

        assertThat(artObject.subtitle, itIs(equalTo("h 379,5cm × b 453,5cm × g 337kg")))
        assertThat(artObject.description, itIs(equalTo("Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...")))
        assertThat(artObject.date, itIs(equalTo("2019-07-05")))
        assertThat(artObject.materials, itIs(equalTo(listOf("doek", "olieverf"))))
        assertThat(artObject.principalMakers, itIs(equalTo(listOf("Rembrandt"))))
    }

    @Test
    fun `toArtObject with no plaqueDescriptionEnglish should use description`() {
        val artObjectDto = ArtObjectDto(
            subTitle = "h 379,5cm × b 453,5cm × g 337kg",
            description = "Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...",
            plaqueDescriptionEnglish = null,
            materials = listOf("doek", "olieverf"),
            principalMakers = listOf(MakerDto(name = "Rembrandt")),
            dating = DatingDto(presentingDate = "2019-07-05")
        )

        val artObject = artObjectDto.toArtObject()

        assertThat(artObject.subtitle, itIs(equalTo("h 379,5cm × b 453,5cm × g 337kg")))
        assertThat(artObject.description, itIs(equalTo("Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...")))
        assertThat(artObject.date, itIs(equalTo("2019-07-05")))
        assertThat(artObject.materials, itIs(equalTo(listOf("doek", "olieverf"))))
        assertThat(artObject.principalMakers, itIs(equalTo(listOf("Rembrandt"))))
    }

    @Test
    fun `toArtObject with no plaqueDescriptionEnglish or description then description should be empty`() {
        val artObjectDto = ArtObjectDto(
            subTitle = "h 379,5cm × b 453,5cm × g 337kg",
            description = null,
            plaqueDescriptionEnglish = null,
            materials = listOf("doek", "olieverf"),
            principalMakers = listOf(MakerDto(name = "Rembrandt")),
            dating = DatingDto(presentingDate = "2019-07-05")
        )

        val artObject = artObjectDto.toArtObject()

        assertThat(artObject.subtitle, itIs(equalTo("h 379,5cm × b 453,5cm × g 337kg")))
        assertThat(artObject.description, itIs(equalTo("")))
        assertThat(artObject.date, itIs(equalTo("2019-07-05")))
        assertThat(artObject.materials, itIs(equalTo(listOf("doek", "olieverf"))))
        assertThat(artObject.principalMakers, itIs(equalTo(listOf("Rembrandt"))))
    }
}