package com.example.admin.retrofitpost.Network;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Admin on 3/6/2018.
 */

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/Retrofit/insert.php")
    public void insertdatas(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            Callback<Response> callback
    );
}
