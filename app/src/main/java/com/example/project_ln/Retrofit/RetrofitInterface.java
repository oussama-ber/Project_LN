package com.example.project_ln.Retrofit;

import com.example.project_ln.Model.Book;
import com.example.project_ln.Model.Products;
import com.example.project_ln.Model.User;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    //auth
    @POST("posts")
    public Call<JsonObject> signUp(@Body JsonObject object);

    @POST("posts")
    public Call<JsonObject> login(@Body JsonObject object);

    // Posts
    @GET("books")
    Call<List<Book>> getPosts();

    @GET("books")
    Call<Products> getpostList();

    @POST("books")
    Call<Book> createPost(@Body Book book);



    //// for login and register
    @POST("user/signup")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,

                                    @Field("password") String password);

    @POST("user/login")
    @FormUrlEncoded
    Observable<String> loginUser (@Field("email") String email,
                                  @Field("password") String password);
    // get USer
    @GET("user/{id}")
    Call<User> getUser(@Path("id") String userId);





}
