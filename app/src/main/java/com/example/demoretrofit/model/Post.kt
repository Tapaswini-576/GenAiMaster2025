package com.example.demoretrofit.model

import com.google.gson.annotations.SerializedName

// Data class to represent a single 'Post' object from JSONPlaceholder API
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    @SerializedName("body") // Use @SerializedName if the JSON key is different from your variable name (e.g., 'body' in JSON to 'text' in Kotlin)
    val text: String
)
