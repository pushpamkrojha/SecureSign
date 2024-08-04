package com.example.signupapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val name = intent.getStringExtra(SignInActivity.KEY2)
        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val uniqueId = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tv1)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val uniqueIdText = findViewById<TextView>(R.id.tvId)

        welcomeText.text = "Welcome $name"
        mailText.text = "Mail: $mail"
        uniqueIdText.text = "UniqueId: $uniqueId"
    }
}
