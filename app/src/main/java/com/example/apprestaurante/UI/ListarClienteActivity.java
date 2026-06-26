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

import com.example.apprestaurante.API.ClienteApi;
import com.example.apprestaurante.API.Constantes;
import com.example.apprestaurante.API.RetrofitClient;
import com.example.apprestaurante.Entidades.Cliente;
import com.example.apprestaurante.R;
import com.example.apprestaurante.databinding.ActivityListarClienteBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarClienteActivity extends AppCompatActivity {

    private ActivityListarClienteBinding binding;

    private void MostrarCliente()
    {
        ClienteApi api = RetrofitClient.getClient(Constantes.ruta_cliente)
                .create(ClienteApi.class);

        Call<List<Cliente>> call = api.GetClientes();

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()){
                    List<Cliente> lista = response.body();

                    List<String> lista_str = new ArrayList<String>();

                    String cadena ="";
                    for (Cliente item :lista){
                        cadena = "codigo" + item.getId() + "\n" +
                                 "nombre" + item.getNombre() + "\n" +
                                 "telefono"+ item.getTelefono() + "\n" +
                                 "email" + item.getEmail();

                        lista_str.add(cadena);
                    }

                    ArrayAdapter<String> adapter  =new ArrayAdapter<String>(
                            ListarClienteActivity.this,
                            android.R.layout.simple_list_item_1,
                            lista_str
                    );
                    binding.lvCliente.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(ListarClienteActivity.this
                        , "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //
        binding = ActivityListarClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.btnListarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarCliente();
            }
        });

        binding.btnIrComidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarClienteActivity.this, ListarComidaActivity.class);
                startActivity(intent);
            }
        });

        binding.btnIrPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarClienteActivity.this, ListarPedidoActivity.class);
                startActivity(intent);
            }
        });

        binding.btnVolverMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });

    }
}