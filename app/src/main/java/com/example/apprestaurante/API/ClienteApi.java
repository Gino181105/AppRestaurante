package com.example.apprestaurante.API;

import com.example.apprestaurante.Entidades.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteApi {

    @GET("GetClientes")
    Call<List<Cliente>> GetClientes();

    @GET("GetCliente/{id}")
    Call<Cliente> GetCliente(@Path("id")int id);

    @POST("CreateCliente")
    Call<Cliente> createCliente(@Body Cliente cliente);

    @PUT("UpdateCliente/{id}")
    Call<Void> updateCliente(@Path("id") int id, @Body Cliente cliente);

    @DELETE("DeleteCliente/{id}")
    Call<Void> deleteCliente(@Path("id") int id);

}
