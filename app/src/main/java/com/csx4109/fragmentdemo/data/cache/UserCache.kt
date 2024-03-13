package com.csx4109.fragmentdemo.data.cache

import android.content.Context
import com.csx4109.fragmentdemo.models.UserInfo
import com.google.gson.Gson

class UserCache(private val context: Context) {
    private val cache = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun isLogin(): Boolean {
        return cache.contains("userInfo")
    }

    fun getUserInfo(): UserInfo? {
        val jsonValue = cache.getString("userInfo", null)

        return jsonValue?.let {
            gson.fromJson(it, UserInfo::class.java)
        }
    }

    fun setUserInfo(userInfo: UserInfo) {
        cache.edit().apply {
            putString("userInfo", gson.toJson(userInfo))
            apply()
        }
    }
}