package com.example.final_aplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    private EditText nombreMascotaEditText, sexoEditText, razaEditText, fechaNacimientoEditText;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        dbHelper = new DBHelper(this);

        nombreMascotaEditText = findViewById(R.id.nombre_mascota);
        sexoEditText = findViewById(R.id.Sexo);
        razaEditText = findViewById(R.id.Raza);
        fechaNacimientoEditText = findViewById(R.id.Fecha_nacimiento);

        Button guardarButton = findViewById(R.id.button_guardar);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreMascotaEditText.getText().toString().trim();
                String sexo = sexoEditText.getText().toString().trim();
                String raza = razaEditText.getText().toString().trim();
                String fechaNacimiento = fechaNacimientoEditText.getText().toString().trim();

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(sexo) || TextUtils.isEmpty(raza) || TextUtils.isEmpty(fechaNacimiento)) {
                    mostrarMensaje("Por favor, completa todos los campos", Toast.LENGTH_LONG);
                } else {
                    if (dbHelper.insertarMascota(nombre, sexo, raza, fechaNacimiento)) {
                        mostrarMensaje("Mascota guardada exitosamente", Toast.LENGTH_SHORT);
                        limpiarCampos();
                    } else {
                        mostrarMensaje("Error al guardar la mascota", Toast.LENGTH_LONG);
                    }
                }
            }
        });

        Button botonSalir = findViewById(R.id.salir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        Button verDatosButton = findViewById(R.id.button_VerDatos);
        verDatosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
                startActivity(intent);
            }
        });
    }

    private void mostrarMensaje(String mensaje, int duracion) {
        Toast.makeText(this, mensaje, duracion).show();
    }

    private void limpiarCampos() {
        nombreMascotaEditText.setText("");
        sexoEditText.setText("");
        razaEditText.setText("");
        fechaNacimientoEditText.setText("");
    }
}
