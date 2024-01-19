// MainActivity.kt
package com.example.insta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostViewModel
    private lateinit var postsTextView: TextView
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, PostViewModelFactory(application)).get(PostViewModel::class.java)
        postsTextView = findViewById(R.id.postsTextView)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            showNextPost()
        }

        val shareButton = findViewById<Button>(R.id.shareButton)
        shareButton.setOnClickListener {

            sharePostDetails()
        }

        val aboutButton = findViewById<Button>(R.id.aboutButton)
        aboutButton.setOnClickListener {

            startActivity(Intent(this, AboutActivity::class.java))
        }

        viewModel.postsData.observe(this, { posts ->
            if (posts != null && posts.isNotEmpty()) {
                updatePostDetails(posts[currentIndex])
            } else {
                postsTextView.text = "Failed to fetch posts."
            }
        })

        viewModel.fetchPosts()
    }

    private fun showNextPost() {
        currentIndex++
        // Handle looping back to the first post if currentIndex exceeds the number of posts
        if (currentIndex >= viewModel.postsData.value?.size ?: 0) {
            currentIndex = 0
        }
        viewModel.postsData.value?.get(currentIndex)?.let { updatePostDetails(it) }
    }

    private fun updatePostDetails(post: Post) {
        val postText = "Title: ${post.title}\n\nBody: ${post.body}"
        postsTextView.text = postText
    }

    private fun sharePostDetails() {
        // Get the post details from the text view
        val postDetails = postsTextView.text.toString()

        // Start the EmailActivity and pass the post details as an extra
        val intent = Intent(this@MainActivity, EmailActivity::class.java)
        intent.putExtra("postDetails", postDetails)
        startActivity(intent)
    }
}
