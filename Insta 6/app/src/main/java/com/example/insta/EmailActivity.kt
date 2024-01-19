// EmailActivity.kt
package com.example.insta

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()


            val postDetails = intent.getStringExtra("postDetails")


            sendEmail(email, postDetails)
        }


        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {

            finish()
        }
    }

    private fun sendEmail(email: String, postDetails: String?) {

        val intent = Intent(Intent.ACTION_SEND)


        intent.type = "text/plain"


        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))


        intent.putExtra(Intent.EXTRA_SUBJECT, "Post Details")


        intent.putExtra(Intent.EXTRA_TEXT, postDetails)


        if (intent.resolveActivity(packageManager) != null) {

            startActivity(intent)
        } else {

        }
    }
}
