package com.rijks.app.data.remote

import com.rijks.app.data.di.RijksHttpClient
import com.rijks.app.data.remote.dto.ArtCollectionDto
import com.rijks.app.data.remote.dto.ArtCollectionDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import javax.inject.Inject

internal class ArtCollectionDataSource  @Inject constructor(
    @RijksHttpClient private val httpClient: HttpClient,
) {

    suspend fun getArtCollection(page: Int, pageCount: Int): ArtCollectionDto = httpClient
        .get(urlString = "https://www.rijksmuseum.nl/api/en/collection") {
            url {
                parameters.append("p", page.toString())
                parameters.append("ps", pageCount.toString())
                parameters.append("imgonly", "true")
            }
        }
        .body()

    suspend fun search(query: String, page: Int, pageCount: Int): ArtCollectionDto = httpClient
        .get(urlString = "https://www.rijksmuseum.nl/api/en/collection") {
            url {
                parameters.append("p", page.toString())
                parameters.append("ps", pageCount.toString())
                parameters.append("imgonly", "true")
                parameters.append("q", query)
            }
        }
        .body()

    suspend fun getArtCollectionDetail(objectNumber: String): ArtCollectionDetailDto = httpClient
        .get(urlString = "https://www.rijksmuseum.nl/api/en/collection") {
            url { appendPathSegments(objectNumber) }
        }
        .body()
}
