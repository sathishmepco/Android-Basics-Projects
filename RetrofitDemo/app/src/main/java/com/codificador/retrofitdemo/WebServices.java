package com.codificador.retrofitdemo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {
    @GET("listContacts")
    Call<List<Contact>> getContactList();
    @POST("addContact")
    Call<ContactResponse> addContact(@Body Contact contact);
}