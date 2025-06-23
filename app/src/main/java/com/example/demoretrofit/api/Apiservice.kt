package com.example.demoretrofit.api

import com.example.demoretrofit.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("posts")
    suspend fun getPosts(): List<Post>


    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Post


    @GET("posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): List<Post>

    // You can also define other HTTP methods like POST, PUT, DELETE:
    /*
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post // @Body sends the 'post' object as the request body

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit> // Response<Unit> for API calls that return no content
    */
}
