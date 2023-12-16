package com.example.final_aplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextUsuario, editTextContraseña;
    private Button btnIngresar;
    private RadioGroup radioGroup;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextUsuario = findViewById(R.id.editTextText);
        editTextContraseña = findViewById(R.id.editTextTextPassword);
        btnIngresar = findViewById(R.id.ingresar);
        radioGroup = findViewById(R.id.radioGroup);

        dbHelper = new DBHelper(this);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        TextView textViewButton = findViewById(R.id.textViewButton);
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarSesion() {
        String usuario = editTextUsuario.getText().toString().trim();
        String contraseña = editTextContraseña.getText().toString().trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            mostrarMensaje("Por favor, completa todos los campos");
        } else {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                mostrarMensaje("Selecciona un rol");
                return;
            }

            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            String rol = radioButton.getText().toString();

            Class<?> targetActivity;

            if (autenticarUsuario(usuario, contraseña, rol)) {
                mostrarMensaje("Inicio de sesión exitoso");

                if (rol.equals("Cliente")) {
                    targetActivity = MainActivity4.class;
                } else if (rol.equals("Veterinario")) {
                    targetActivity = MainActivity5.class;
                } else {
                    mostrarMensaje("Rol no reconocido");
                    return;
                }
                Intent intent = new Intent(this, targetActivity);
                startActivity(intent);
            } else {
                mostrarMensaje("Credenciales incorrectas. Intenta de nuevo.");
            }
        }
    }


    private boolean autenticarUsuario(String usuario, String contraseña, String rol) {
        return dbHelper.verificarCredenciales(usuario, contraseña, rol);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void onClickTextViewButton(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}
