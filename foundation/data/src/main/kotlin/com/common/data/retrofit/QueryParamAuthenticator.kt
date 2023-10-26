package com.common.data.retrofit

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class QueryParamAuthenticator(
    private val key: String,
    private val token: String,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val newUrl = response
            .request
            .url
            .newBuilder()
            .addQueryParameter(key, token)
            .build()

        return response.request.newBuilder().url(newUrl).build()
    }
}
