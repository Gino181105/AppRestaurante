package com.example.apprestaurante.API;

import com.example.apprestaurante.Entidades.Pedido;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoApi {

    @GET("GetPedidos")
    Call<List<Pedido>> getPedidos();

    @GET("GetPedido/{id}")
    Call<Pedido> getPedido(@Path("id") int id);

    @GET("GetPedidosPorCliente/{clienteId}")
    Call<List<Pedido>> getPedidosPorCliente(@Path("clienteId") int clienteId);

    @GET("GetPedidosPorEstado/{estado}")
    Call<List<Pedido>> getPedidosPorEstado(@Path("estado") String estado);

    @POST("CreatePedido")
    Call<Pedido> createPedido(@Body Pedido pedido);

    @PUT("UpdatePedido/{id}")
    Call<Void> updatePedido(@Path("id") int id, @Body Pedido pedido);

    @PUT("UpdateEstadoPedido/{id}")
    Call<Void> updateEstadoPedido(@Path("id") int id, @Body String nuevoEstado);

    @DELETE("DeletePedido/{id}")
    Call<Void> deletePedido(@Path("id") int id);
}