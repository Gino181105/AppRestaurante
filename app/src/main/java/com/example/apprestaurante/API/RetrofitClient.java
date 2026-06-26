package com.example.apprestaurante.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrlServicio)
    {

        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrlServicio)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}