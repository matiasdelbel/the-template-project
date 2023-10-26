package com.rijks.app.data.remote

import androidx.paging.PagingSource
import com.rijks.app.data.dto.ArtCollectionDto
import com.rijks.app.data.dto.ArtObjectOverviewDto
import com.rijks.app.model.ArtObjectOverview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNull.nullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.hamcrest.core.Is.`is` as itIs

class ArtCollectionPagingSourceTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun before() = Dispatchers.setMain(dispatcher)

    @After
    fun after() = Dispatchers.resetMain()

    @Test
    fun `load a new page`() = runTest {
        val expectedArtObject = mock<ArtObjectOverview>()
        val artOnOverviewDto = mock<ArtObjectOverviewDto> { on { toArtObject() } doReturn expectedArtObject }
        val artCollectionDto = mock<ArtCollectionDto> {
            on { artObjects } doReturn listOf(artOnOverviewDto)
            on { count } doReturn 10
        }
        val pagingSource = ArtCollectionPagingSource(
            artCollectionDataSource = mock {
                onBlocking { getArtCollection(page = 1, pageCount = 2) } doReturn artCollectionDto
            }
        )
        val loadParams = mock<PagingSource.LoadParams<Int>> {
            on { key } doReturn null
            on { loadSize } doReturn 2
        }

        val loadResult = pagingSource.load(loadParams)

        assertThat(loadResult, instanceOf(PagingSource.LoadResult.Page::class.java))
        loadResult as PagingSource.LoadResult.Page
        assertThat(loadResult.prevKey, itIs(nullValue()))
        assertThat(loadResult.nextKey, itIs(equalTo(2)))
        assertThat(loadResult.data, itIs(equalTo(listOf(expectedArtObject))))
    }
}
