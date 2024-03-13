package com.csx4109.fragmentdemo.data.cache

import android.content.Context
import com.csx4109.fragmentdemo.models.University
import com.google.gson.Gson

class UniversityCache(private val context: Context) {
    private val cache = context.getSharedPreferences("university", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun hasUniversities(): Boolean {
        return cache.contains("universityList")
    }

    fun getUniversities(): List<University> {
        val jsonValue = cache.getString("universityList", null)

        return jsonValue?.let {
            gson.fromJson(it, Array<University>::class.java).toList()
        } ?: listOf()
    }

    fun setUniversities(universities: List<University>) {
        cache.edit().apply {
            putString("universityList", gson.toJson(universities))
            apply()
        }
    }
}