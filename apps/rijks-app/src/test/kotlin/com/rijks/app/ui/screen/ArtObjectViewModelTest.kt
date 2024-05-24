package com.rijks.app.ui.screen

import com.rijks.app.model.ArtObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.hamcrest.core.Is.`is` as itIs

class ArtObjectViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun before() = Dispatchers.setMain(dispatcher)

    @After
    fun after() = Dispatchers.resetMain()

    @Test
    fun `ui initial state`() = runTest {
        val viewModel ArtObjectViewModel(
            savedStateHandle = mock {
                on { get<String>("art_object_number") } doReturn "SK-C-5"
                on { get<String>("art_object_title") } doReturn "De Nachtwacht"
                on { get<String>("art_object_image_url") } doReturn "http://www.rijksmuseum.nl/nl/collectie/SK-C-5"
            },
            artCollectionRepository = mock()
        )

        val uiState = viewModel.uiState.value

        assertThat(uiState.title, itIs(equalTo("De Nachtwacht")))
        assertThat(uiState.imageUrl, itIs(equalTo("http://www.rijksmuseum.nl/nl/collectie/SK-C-5")))
        assertThat(uiState.showFacts, itIs(false))
        assertThat(uiState.showError, itIs(false))
    }

    @Test
    fun `ui state after fetching data from the repository`() = runTest {
        val artObject = ArtObject(
            subtitle = "h 379,5cm × b 453,5cm × g 337kg",
            description = "Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...",
            date = "2019-07-05",
            materials = listOf("doek", "olieverf"),
            principalMakers = listOf("Rembrandt")
        )
        val artObjectNumber = "SK-C-5"
        val viewModel = ArtObjectViewModel(
            savedStateHandle = mock {
                on { get<String>("art_object_number") } doReturn artObjectNumber
                on { get<String>("art_object_title") } doReturn "De Nachtwacht"
                on { get<String>("art_object_image_url") } doReturn "http://www.rijksmuseum.nl/nl/collectie/SK-C-5"
            },
            artCollectionRepository = mock {
                onBlocking { obtainArtObject(artObjectNumber) } doReturn artObject
            }
        )

        advanceUntilIdle()
        val uiState = viewModel.uiState.value

        assertThat(uiState.title, itIs(equalTo("De Nachtwacht")))
        assertThat(uiState.imageUrl, itIs(equalTo("http://www.rijksmuseum.nl/nl/collectie/SK-C-5")))
        assertThat(uiState.showFacts, itIs(true))
        assertThat(uiState.showError, itIs(false))
        assertThat(uiState.subtitle, itIs(equalTo("h 379,5cm × b 453,5cm × g 337kg")))
        assertThat(uiState.description, itIs(equalTo("Rembrandts beroemdste en grootste schilderij werd gemaakt voor de...")))
        assertThat(uiState.date, itIs(equalTo("2019-07-05")))
        assertThat(uiState.materials, itIs(equalTo("doek, olieverf")))
        assertThat(uiState.principalMakers, itIs(equalTo("Rembrandt")))
    }
}
