package com.common.data.retrofit

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class BearerTokenAuthenticator(private val token: String) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val headers = response
            .request
            .headers
            .newBuilder()
            .add(AuthorizationHeader, value = "Bearer $token")
            .build()

        return response.request.newBuilder().headers(headers).build()
    }
}

private const val AuthorizationHeader = "Authorization"
