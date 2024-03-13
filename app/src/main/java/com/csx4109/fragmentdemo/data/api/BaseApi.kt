package com.csx4109.fragmentdemo.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

abstract class BaseApi {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }
}