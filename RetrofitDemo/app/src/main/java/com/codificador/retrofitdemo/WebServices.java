package com.codificador.retrofitdemo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WebServices {
    @GET("listContacts")
    Call<List<Contact>> getContactList();

    @POST("addContact")
    Call<ContactResponse> addContact(@Body Contact contact);

    @FormUrlEncoded
    @POST("postContact")
    Call<ContactResponse> postContact(@Field("name")String name,@Field("phone")String phone, @Field("email")String email);

    @Multipart
    @POST("uploadFile")
    Call<String> uploadFile(@Part MultipartBody.Part file);
}