package com.rijks.app.data.remote

import com.rijks.app.data.dto.ArtCollectionDto
import com.rijks.app.data.dto.ArtCollectionDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ArtCollectionDataSource {

    @GET("collection")
    suspend fun getArtCollection(
        @Query("p") page: Int,
        @Query("ps") pageCount: Int,
        @Query("imgonly") onlyArtObjectsWithImage: Boolean = true,
        @Query("s") sortedBy: String = "artist"
    ): ArtCollectionDto


    @GET("collection/{objectNumber}")
    suspend fun getArtCollectionDetail(
        @Path("objectNumber") objectNumber: String,
    ): ArtCollectionDetailDto
}
