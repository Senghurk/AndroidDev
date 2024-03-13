package com.csx4109.fragmentdemo.data.api

import com.csx4109.fragmentdemo.models.University
import io.ktor.client.call.body
import io.ktor.client.request.get

class UniversityApi: BaseApi() {

    suspend fun getUniversities(): List<University> =
        client
            .get("http://universities.hipolabs.com/search?country=Thailand")
            .body<List<University>>()
}