package com.example.final_aplicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private EditText editTextNombre, editTextCorreo, editTextContraseña;
    private Button btnRegistrar, btnSalir;

    private RadioGroup radioGroup;
    private RadioButton radioButtonCliente, radioButtonVeterinario;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnSalir = findViewById(R.id.btnSalir);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonCliente = findViewById(R.id.radioButtonCliente);
        radioButtonVeterinario = findViewById(R.id.radioButtonVeterinario);

        dbHelper = new DBHelper(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAtras();
            }
        });
    }

    private void volverAtras() {
        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String contraseña = editTextContraseña.getText().toString();

        String rol = radioButtonCliente.isChecked() ? "Cliente" : "Veterinario";

        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
            mostrarMensaje("Por favor, completa todos los campos");
            return;
        }

        dbHelper.insertarUsuario(nombre, correo, contraseña, rol);

        mostrarMensaje("Usuario registrado con éxito");

        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
