package com.example.demoretrofit

import android.os.Bundle
import android.util.Log // For Log.d and Log.e
import android.widget.Button // For Button
import android.widget.TextView // For TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope // For lifecycleScope
import com.example.demoretrofit.api.RetrofitClient // For RetrofitClient
import com.example.demoretrofit.model.Post // For Post data class
import com.example.demoretrofit.R
import kotlinx.coroutines.launch // For launch in coroutines


class MainActivity : AppCompatActivity() {

    // Declare UI elements as lateinit properties to initialize them in onCreate
    private lateinit var resultTextView: TextView
    private lateinit var fetchAllPostsButton: Button
    private lateinit var fetchSinglePostButton: Button
    private lateinit var fetchPostsByUserIdButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Link the activity to its layout XML

        // Initialize UI elements by finding their IDs from the layout
        resultTextView = findViewById(R.id.resultTextView)
        fetchAllPostsButton = findViewById(R.id.fetchAllPostsButton)
        fetchSinglePostButton = findViewById(R.id.fetchSinglePostButton)
        fetchPostsByUserIdButton = findViewById(R.id.fetchPostsByUserIdButton)

        // Set up click listeners for each button
        fetchAllPostsButton.setOnClickListener {
            fetchAllPosts() // Call the function to fetch all posts
        }

        fetchSinglePostButton.setOnClickListener {
            fetchSinglePost(1) // Call the function to fetch a specific post (ID 1)
        }

        fetchPostsByUserIdButton.setOnClickListener {
            fetchPostsByUserId(1) // Call the function to fetch posts by a specific user (User ID 1)
        }
    }

    /**
     * Fetches all posts from the API.
     * Uses a Coroutine launched within lifecycleScope to handle the network request asynchronously.
     */
    private fun fetchAllPosts() {
        // lifecycleScope.launch creates a coroutine that is automatically cancelled when the Activity is destroyed.
        lifecycleScope.launch {
            try {
                // Make the API call using the instance from RetrofitClient.
                // The 'suspend' function in ApiService allows us to call it directly from a coroutine.
                val posts: List<Post> = RetrofitClient.instance.getPosts()

                // Update UI on the main thread. Accessing UI elements directly from a background thread
                // (which a coroutine can be) is not allowed. runOnUiThread ensures this is safe.
                runOnUiThread {
                    if (posts.isNotEmpty()) {
                        resultTextView.text = "Fetched ${posts.size} posts.\n\nFirst Post:\nTitle: ${posts[0].title}\nBody: ${posts[0].text}"
                    } else {
                        resultTextView.text = "No posts found."
                    }
                    Log.d("MainActivity", "Successfully fetched all posts. Count: ${posts.size}")
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during the network request (e.g., no internet, server error)
                runOnUiThread {
                    resultTextView.text = "Error: ${e.message}" // Display error message to the user
                    Log.e("MainActivity", "Error fetching all posts: ${e.message}", e) // Log the error for debugging
                }
            }
        }
    }

    /**
     * Fetches a single post by its ID from the API.
     * @param postId The ID of the post to fetch.
     */
    private fun fetchSinglePost(postId: Int) {
        lifecycleScope.launch {
            try {
                val post: Post = RetrofitClient.instance.getPostById(postId)
                runOnUiThread {
                    resultTextView.text = "Fetched Post (ID: $postId):\nTitle: ${post.title}\nBody: ${post.text}"
                    Log.d("MainActivity", "Successfully fetched post ID: $postId")
                }
            } catch (e: Exception) {
                runOnUiThread {
                    resultTextView.text = "Error fetching post ID $postId: ${e.message}"
                    Log.e("MainActivity", "Error fetching post ID $postId: ${e.message}", e)
                }
            }
        }
    }

    /**
     * Fetches posts associated with a specific User ID from the API.
     * @param userId The ID of the user whose posts are to be fetched.
     */
    private fun fetchPostsByUserId(userId: Int) {
        lifecycleScope.launch {
            try {
                val posts: List<Post> = RetrofitClient.instance.getPostsByUserId(userId)
                runOnUiThread {
                    if (posts.isNotEmpty()) {
                        resultTextView.text = "Fetched ${posts.size} posts for User ID $userId.\n\nFirst Post:\nTitle: ${posts[0].title}\nBody: ${posts[0].text}"
                    } else {
                        resultTextView.text = "No posts found for User ID $userId."
                    }
                    Log.d("MainActivity", "Successfully fetched posts for user ID: $userId. Count: ${posts.size}")
                }
            } catch (e: Exception) {
                runOnUiThread {
                    resultTextView.text = "Error fetching posts for User ID $userId: ${e.message}"
                    Log.e("MainActivity", "Error fetching posts for user ID $userId: ${e.message}", e)
                }
            }
        }
    }
}
