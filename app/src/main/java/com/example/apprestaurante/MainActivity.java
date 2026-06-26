package com.example.apprestaurante;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apprestaurante.UI.ListarClienteActivity;
import com.example.apprestaurante.UI.ListarComidaActivity;
import com.example.apprestaurante.UI.ListarPedidoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botón Clientes
        Button btnClientes = findViewById(R.id.btnClientes);
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarClienteActivity.class);
                startActivity(intent);
            }
        });

        // Botón Comidas
        Button btnComidas = findViewById(R.id.btnComidas);
        btnComidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarComidaActivity.class);
                startActivity(intent);
            }
        });

        // Botón Pedidos
        Button btnPedidos = findViewById(R.id.btnPedidos);
        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarPedidoActivity.class);
                startActivity(intent);
            }
        });
    }
}