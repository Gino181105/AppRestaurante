package com.example.apprestaurante.API;

import com.example.apprestaurante.Entidades.Comida;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComidaApi {

    @GET("GetComidas")
    Call<List<Comida>> getComidas();

    @GET("GetComida/{id}")
    Call<Comida> getComida(@Path("id") int id);

    @GET("GetComidasPorCategoria/{categoria}")
    Call<List<Comida>> getComidasPorCategoria(@Path("categoria") String categoria);

    @POST("CreateComida")
    Call<Comida> createComida(@Body Comida comida);

    @PUT("UpdateComida/{id}")
    Call<Void> updateComida(@Path("id") int id, @Body Comida comida);

    @DELETE("DeleteComida/{id}")
    Call<Void> deleteComida(@Path("id") int id);
}