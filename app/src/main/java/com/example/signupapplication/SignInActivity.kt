package com.example.signupapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    companion object {
        const val KEY1 = "com.example.signupapplication.SignInActivity.mail"
        const val KEY2 = "com.example.signupapplication.SignInActivity.name"
        const val KEY3 = "com.example.signupapplication.SignInActivity.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val uniId = findViewById<TextInputEditText>(R.id.uniqueId)

        btnSignIn.setOnClickListener {
            val uniqueId = uniId.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
                uniId.text?.clear()
            } else {
                Toast.makeText(this, "Please enter Unique Id.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if (it.exists()) {
                val email = it.child("email").value.toString()
                val name = it.child("name").value.toString()
                val uniId = it.child("uniqueId").value.toString()

                val intentHome = Intent(this, HomeActivity::class.java)
                intentHome.putExtra(KEY1, email)
                intentHome.putExtra(KEY2, name)
                intentHome.putExtra(KEY3, uniId)
                startActivity(intentHome)
            } else {
                Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed, Error in DB", Toast.LENGTH_SHORT).show()
        }
    }
}
