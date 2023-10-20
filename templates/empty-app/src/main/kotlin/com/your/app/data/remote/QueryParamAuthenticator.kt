package com.your.app.data.remote

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class QueryParamAuthenticator(
    private val keyName: String,
    private val token: String,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request = response
        .request
        .newBuilder()
        .url(
            response
                .request
                .url
                .newBuilder()
                .addQueryParameter(keyName, token)
                .build()
        )
        .build()
}
