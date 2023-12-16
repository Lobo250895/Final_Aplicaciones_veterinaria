package com.example.final_aplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity7 extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listView;
    private Button btnEliminar;
    private long idMascotaSeleccionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        dbHelper = new DBHelper(this);

        listView = findViewById(R.id.list_mascotas);
        btnEliminar = findViewById(R.id.Eliminar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Mascota mascotaSeleccionada = (Mascota) adapterView.getItemAtPosition(position);

                idMascotaSeleccionada = mascotaSeleccionada.getId();
                actualizarInterfaz();
            }
        });

        Button botonSalir = findViewById(R.id.salir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idMascotaSeleccionada != -1) {
                    Log.d("MainActivity7", "Eliminando mascota con ID: " + idMascotaSeleccionada);

                    boolean eliminacionExitosa = dbHelper.eliminarMascota(idMascotaSeleccionada);

                    if (eliminacionExitosa) {
                        Toast.makeText(MainActivity7.this, "Mascota eliminada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity7.this, "Error al eliminar la mascota", Toast.LENGTH_SHORT).show();
                    }

                    idMascotaSeleccionada = -1;
                    actualizarInterfaz();

                    cargarListaMascotas();
                } else {
                    Log.d("MainActivity7", "No hay mascota seleccionada para eliminar");
                }
            }
        });


        cargarListaMascotas();
        actualizarInterfaz();
    }

    private void cargarListaMascotas() {
        List<Mascota> mascotas = dbHelper.obtenerTodosLosDatosMascotas();

        ArrayAdapter<Mascota> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mascotas);

        listView.setAdapter(adapter);
    }

    private void actualizarInterfaz() {
        if (idMascotaSeleccionada != -1) {
            btnEliminar.setVisibility(View.VISIBLE);
        } else {
            btnEliminar.setVisibility(View.GONE);
        }
    }

}
