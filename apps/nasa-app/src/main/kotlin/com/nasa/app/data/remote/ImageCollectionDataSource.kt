package com.nasa.app.data.remote

import com.nasa.app.data.di.NasaHttpClient
import com.nasa.app.data.remote.dto.ImageCollectionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class ImageCollectionDataSource  @Inject constructor(
    @NasaHttpClient private val httpClient: HttpClient,
) {

    suspend fun getImageCollection(page: Int, query: String): ImageCollectionDto = httpClient
        .get(urlString = "https://images-api.nasa.gov/search") {
            url {
                parameters.append("q", query)
                parameters.append("page", page.toString())
            }
        }
        .body()
}
