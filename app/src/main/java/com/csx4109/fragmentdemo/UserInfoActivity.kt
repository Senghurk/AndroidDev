package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.csx4109.fragmentdemo.data.cache.UserCache
import com.csx4109.fragmentdemo.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    private val view: ActivityUserInfoBinding by lazy { ActivityUserInfoBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val cache = UserCache(this)
        val userInfo = cache.getUserInfo()

        if (userInfo != null) {
            view.tvUserName.text = userInfo.username
            view.tvPassword.text = userInfo.password
        } else {
            Toast.makeText(this, "User is not log in", Toast.LENGTH_SHORT).show()
        }
    }
}