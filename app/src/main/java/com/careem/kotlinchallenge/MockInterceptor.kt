package com.careem.kotlinchallenge

import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("KoltinChallenge.json") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString as String)
                .body(
                    responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}

val getListOfReposBeingStarredJson: String = Gson().toJson(MockResponse.createMockResponse())