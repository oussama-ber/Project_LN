package com.example.project_ln;

import com.example.project_ln.Model.Book;
import com.example.project_ln.Model.Products;
import com.example.project_ln.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("books")
    Call<List<Book>> getPosts();

    @GET("books")
    Call<Products> getpostList();

    @POST("books")
    Call<Book> createPost(@Body Book post);


    // get USer
    @GET("user/{id}")
    Call<User> getUser(@Path("id") String userId);


}
