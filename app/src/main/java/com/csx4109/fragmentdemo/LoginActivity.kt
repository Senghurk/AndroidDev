package com.csx4109.fragmentdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.csx4109.fragmentdemo.data.cache.UserCache
import com.csx4109.fragmentdemo.databinding.ActivityLoginBinding
import com.csx4109.fragmentdemo.models.UserInfo

class LoginActivity : AppCompatActivity() {
    private val view: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        val cache = UserCache(this)

        if (cache.isLogin()) {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }

        view.btnLogin.setOnClickListener {
            val userName = view.tvUserName.text.toString()
            val password = view.tvPassword.text.toString()

            if (userName == "admin" && password == "admin") {
                cache.setUserInfo(
                    UserInfo(
                        username = userName,
                        password = password
                    )
                )
                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}