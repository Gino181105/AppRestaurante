package com.example.apprestaurante.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apprestaurante.API.PedidoApi;
import com.example.apprestaurante.API.Constantes;
import com.example.apprestaurante.API.RetrofitClient;
import com.example.apprestaurante.Entidades.Pedido;
import com.example.apprestaurante.R;
import com.example.apprestaurante.databinding.ActivityListarPedidoBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarPedidoActivity extends AppCompatActivity {

    private ActivityListarPedidoBinding binding;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

    private void MostrarPedidos() {

        PedidoApi api = RetrofitClient.getClient(Constantes.ruta_pedido)
                .create(PedidoApi.class);

        Call<List<Pedido>> call = api.getPedidos();

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful()) {
                    List<Pedido> lista = response.body();

                    List<String> lista_str = new ArrayList<String>();

                    String cadena = "";
                    for (Pedido item : lista) {
                        String fechaStr = "";
                        if (item.getFecha() != null) {
                            fechaStr = dateFormat.format(item.getFecha());
                        }

                        cadena = "Pedido #" + item.getId() + "\n" +
                                "Cliente ID: " + item.getClienteId() + "\n" +
                                "Comida ID: " + item.getComidaId() + "\n" +
                                "Cantidad: " + item.getCantidad() + "\n" +
                                "Total: S/ " + item.getTotal() + "\n" +
                                "Estado: " + item.getEstado() + "\n" +
                                "Fecha: " + fechaStr;

                        lista_str.add(cadena);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            ListarPedidoActivity.this,
                            android.R.layout.simple_list_item_1,
                            lista_str
                    );
                    binding.lvPedidos.setAdapter(adapter);

                    Toast.makeText(ListarPedidoActivity.this,
                            lista.size() + " pedidos cargados", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ListarPedidoActivity.this,
                            "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(ListarPedidoActivity.this
                        , "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityListarPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnListarPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarPedidos();
            }
        });

        binding.btnIrClientesDesdePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarPedidoActivity.this, ListarClienteActivity.class);
                startActivity(intent);
            }
        });


        binding.btnIrComidasDesdePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarPedidoActivity.this, ListarComidaActivity.class);
                startActivity(intent);
            }
        });
        MostrarPedidos();
    }
}