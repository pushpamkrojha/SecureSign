package com.example.signupapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpBtn = findViewById<Button>(R.id.btnSignUp)
        val nameFiled = findViewById<TextInputEditText>(R.id.name)
        val mailField = findViewById<TextInputEditText>(R.id.mail)
        val uniqueIdField = findViewById<TextInputEditText>(R.id.uniqueId)
        val passwordField = findViewById<TextInputEditText>(R.id.password)

        signUpBtn.setOnClickListener {
            val name = nameFiled.text.toString()
            val mail = mailField.text.toString()
            val uniqueId = uniqueIdField.text.toString()
            val password = passwordField.text.toString()

            val user = User(name, mail, uniqueId, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                nameFiled.text?.clear()
                mailField.text?.clear()
                uniqueIdField.text?.clear()
                passwordField.text?.clear()
                Toast.makeText(this, "User Registered...", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener  {
                Toast.makeText(this, "Failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
