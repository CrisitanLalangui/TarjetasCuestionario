package com.example.aplicacindesarrollo2;






import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import android.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public  class MainActivity extends AppCompatActivity {

    public  static   ArrayList<EventModel> eventosHIstoricos = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.historic_Events_Recycle0);
        fillTheModels();

        AdaptadorDeEvntosRV adaptador = new AdaptadorDeEvntosRV(this,eventosHIstoricos);
        recyclerView.setAdapter((adaptador));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void fillTheModels() {
        String[] eventNames = getResources().getStringArray(R.array.historic_event_names);
        String[] eventDates = getResources().getStringArray(R.array.historic_event_dates);
        String[] eventLocations = getResources().getStringArray(R.array.historic_event_locations);


        for (int i = 0; i< eventNames.length; i++){

            eventosHIstoricos.add(new EventModel(eventNames[i], eventDates[i],eventLocations[i] ));
        }


    }



    public void mostrarDialogoAlerta() {


        new AlertDialog.Builder(this)
                .setTitle("Primera alerta del mundo")
                .setMessage("Esto es una alerta")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    Toast.makeText(this, "Botón positivo", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    Toast.makeText(this, "Botón negativo", Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}