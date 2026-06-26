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

import com.example.apprestaurante.API.ComidaApi;
import com.example.apprestaurante.API.Constantes;
import com.example.apprestaurante.API.RetrofitClient;
import com.example.apprestaurante.Entidades.Comida;
import com.example.apprestaurante.R;
import com.example.apprestaurante.databinding.ActivityListarComidaBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarComidaActivity extends AppCompatActivity {

    private ActivityListarComidaBinding binding;

    private void MostrarComidas() {

        ComidaApi api = RetrofitClient.getClient(Constantes.ruta_comida)
                .create(ComidaApi.class);

        Call<List<Comida>> call = api.getComidas();

        call.enqueue(new Callback<List<Comida>>() {
            @Override
            public void onResponse(Call<List<Comida>> call, Response<List<Comida>> response) {
                if (response.isSuccessful()) {
                    List<Comida> lista = response.body();

                    List<String> lista_str = new ArrayList<String>();

                    String cadena = "";
                    for (Comida item : lista) {
                        cadena = "Código: " + item.getId() + "\n" +
                                "Nombre: " + item.getNombre() + "\n" +
                                "Descripción: " + item.getDescripcion() + "\n" +
                                "Precio: S/ " + item.getPrecio() + "\n" +
                                "Categoría: " + item.getCategoria();

                        lista_str.add(cadena);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            ListarComidaActivity.this,
                            android.R.layout.simple_list_item_1,
                            lista_str
                    );
                    binding.lvComidas.setAdapter(adapter);

                    Toast.makeText(ListarComidaActivity.this,
                            lista.size() + " comidas cargadas", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ListarComidaActivity.this,
                            "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Comida>> call, Throwable t) {
                Toast.makeText(ListarComidaActivity.this
                        , "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityListarComidaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnListarComidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarComidas();
            }
        });

        binding.btnIrClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarComidaActivity.this, ListarClienteActivity.class);
                startActivity(intent);
            }
        });


        binding.btnIrPedidosDesdeComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarComidaActivity.this, ListarPedidoActivity.class);
                startActivity(intent);
            }
        });


        MostrarComidas();
    }
}