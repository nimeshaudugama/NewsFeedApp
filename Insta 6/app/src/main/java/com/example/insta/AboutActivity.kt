// AboutActivity.kt
package com.example.insta

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)


        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {

            finish()
        }
    }


}
